package com.springaop.service.impl;

import com.springaop.service.ApiService;
import com.springaop.service.RestService;
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

    @Override
    public ModelMap getDataAsyncWithThreadPool() throws Exception {
        final AtomicLong count = new AtomicLong(0);
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("gp-" + count.getAndIncrement());
                    return thread;
                });
        ModelMap result = new ModelMap();
        CompletableFuture<Void> completableFuture = CompletableFuture
                .runAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    result.put("user", restService.getUser());
                }, pool)
                .runAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    result.put("client", restService.getClient());
                }, pool)
                .handle((res, ex) -> {
                    if (ex != null) {
                        System.out.println("Oops! We have an exception - " + ex.getMessage());
                    }
                    return res;
                });
        completableFuture.get();
        return result;
    }

    @Override
    public ModelMap getDataAsyncNoThreadPool() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture<Void> completableFuture = CompletableFuture
                .runAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    result.put("user", restService.getUser());
                })
                .runAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    result.put("client", restService.getClient());
                });
        completableFuture.get();
        return result;
    }
}
