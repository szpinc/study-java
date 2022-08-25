package me.szp.study.basic.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class Thread01 {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            return "ok";
        });

        Thread t1 = new Thread(futureTask);

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadList.add(thread);
        }

        threadList.forEach(Thread::start);

        Thread daemon = new Thread(() -> {
            while (true) {
                threadList.forEach(t -> System.out.printf("thread name: %s,status:%s \n", t.getName(), t.getState().name()));
            }
        });
        daemon.setDaemon(true);
        daemon.start();
    }

}
