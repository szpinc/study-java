package me.szp.study.basic.juc;

/**
 * @author sunzp
 * @since 2022/8/23 17:46
 */
public class SyncronizedDemo {

    public static void main(String[] args) {

        Object obj = new Object();
        Thread t = new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName());
            }
        }, "thread-1");

        t.start();
    }

}
