package com.springaop.algorithm.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception, ExecutionException {
//        System.out.println("ConcurrentExecution: " + CompletableFutureHandler.calculateAsync().get());
//        System.out.println("ApplyComposeCombine: " + CompletableFutureHandler.futureCompose().get());
//        System.out.println("multipleFutures: " + CompletableFutureHandler.multipleFuturesExample());
//        System.out.println("handlingErrors: " + CompletableFutureHandler.handlingErrors());

        ExecutorService executor = Executors.newSingleThreadExecutor(r -> new Thread(r, "dead-pool"));
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    return "supplyAsync";
                }, executor)
                .thenApplyAsync(i -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    return i + ", thenApplyAsync";
                }, executor);
//                .thenApplyAsync(i -> {
//                    System.out.println(Thread.currentThread().getName());
//                    return i + ", thenApply";
//                });
        System.out.println("Hello!");
        System.out.println(completableFuture.get());
        executor.shutdown();
    }

}
