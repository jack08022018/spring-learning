package com.springaop.multithread;

import com.springaop.multithread.threads.CompletableFutureHandler;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("ConcurrentExecution: " + CompletableFutureHandler.calculateAsync().get());
        System.out.println("futureCompose: " + CompletableFutureHandler.futureCompose().get());
        System.out.println("futureCombine: " + CompletableFutureHandler.futureCombine().get());
//        System.out.println("multipleFutures: " + CompletableFutureHandler.multipleFuturesExample());
        System.out.println("handlingErrors: " + CompletableFutureHandler.handlingErrors().get());
    }

}
