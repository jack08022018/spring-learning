package com.multithread.service.impl;

import com.multithread.service.ApiService;
import com.multithread.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private RestService restService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public ModelMap getDataAsyncWithThreadPool() throws Exception {
        final AtomicLong count = new AtomicLong(0);
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("gp-" + count.getAndIncrement());
                    return thread;
                });
        ModelMap result = new ModelMap();
        CompletableFuture
                .runAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    result.put("user", restService.getUser());
                }, threadPool)
                .exceptionally(e -> {
                    LOGGER.error(e.getMessage(), e);
                    result.put("user", e.getMessage());
                    return null;
                })
                .get(6, TimeUnit.SECONDS);
        CompletableFuture
                .runAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    result.put("client", restService.getClient());
                }, threadPool)
                .exceptionally(e -> {
                    LOGGER.error(e.getMessage(), e);
                    result.put("user", e.getMessage());
                    return null;
                })
                .get(6, TimeUnit.SECONDS);
        threadPool.shutdown();
        return result;
    }

    @Override
    public ModelMap getDataAsyncNoThreadPool() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture
                .runAsync(() -> result.put("user", restService.getUser()))
                .exceptionally(e -> {
                    LOGGER.error(e.getMessage(), e);
                    result.put("user", e.getMessage());
                    return null;
                })
                .get(6, TimeUnit.SECONDS);
        CompletableFuture
                .runAsync(() -> result.put("client", restService.getClient()))
//                .completeExceptionally(new RuntimeException("Oh noes!"));
                .handle((res, ex) -> {
                    if (null != ex) {
                        LOGGER.error(ex.getMessage(), ex);
                        result.put("error", ex.getMessage());
                    }
                    return res;
                })
                .get(6, TimeUnit.SECONDS);
        return result;
    }

    @Override
    public ModelMap getDataAsyncSeparateThread() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture<Void> futureGetUser = CompletableFuture
                .runAsync(() -> result.put("user", restService.getUser()));
        CompletableFuture<Void> futureGetClient = CompletableFuture
                .runAsync(() -> result.put("client", restService.getClient()));
        CompletableFuture
            .allOf(futureGetUser, futureGetClient)
            .exceptionally(e -> {
                LOGGER.error(e.getMessage(), e);
                result.put("error", e.getMessage());
                return null;
            })
            .get(6, TimeUnit.SECONDS);
        return result;
    }
}
