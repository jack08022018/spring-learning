package com.springaop.multithread.threads;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureHandler {
    public static List<String> multipleFuturesExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

//        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
//        return combinedFuture;

        return Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    public static CompletableFuture<String> handlingErrors() {
        String name = null;
//        String name = "world";
        CompletableFuture<String> completableFuture =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, exception) -> {
            if(s != null) {
                return s;
            }
            return exception.getMessage();
        });
        return completableFuture;
    }
    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
//            return Thread.currentThread().getName();
        });
        return completableFuture;
    }
    public static CompletableFuture<String> futureCompose() {
        CompletableFuture<String> futureCompose = CompletableFuture
            .supplyAsync(() -> "Hello")
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        return futureCompose;
    }
    public static CompletableFuture<String> futureCombine() {
        CompletableFuture<String> futureCombine = CompletableFuture
            .supplyAsync(() -> "Hello")
            .thenCombine(
                    CompletableFuture.supplyAsync(() -> " World"),
                    (s1, s2) -> s1 + s2
            );
        return futureCombine;
    }
}
