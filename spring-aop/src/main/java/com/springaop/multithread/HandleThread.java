package com.springaop.multithread;

import com.springaop.multithread.threads.*;

public class HandleThread {
    public static void exampleThread() {
        Thread t1 = new Thread(new HeavyWorkRunnable(), "t1");
        Thread t2 = new MyThread("t2");
        Runnable t3 = () -> System.out.println("Start t3");
        t1.start();
        t2.start();
        t3.run();
    }

    public static void threadJoin() {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        t1.start();
        //start second thread after waiting for 2 seconds or if it's dead
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

    public static void waitNotify() {
//        waiter and waiter1 will wait until msg notify

        Message msg = new Message("process it");
        WaiterRunnable waiter = new WaiterRunnable(msg);
        new Thread(waiter,"waiter").start();

        WaiterRunnable waiter1 = new WaiterRunnable(msg);
        new Thread(waiter1, "waiter1").start();

        NotifierRunnable notifier = new NotifierRunnable(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are started");
    }

}
