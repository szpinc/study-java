package me.szp.study.basic.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author 孙志鹏
 * @since 2021/12/29 5:56 PM
 */
public class CompletableFutureDemo {

    static CountDownLatch countDownLatch;

    public static void main(String[] args) throws InterruptedException {

        countDownLatch = new CountDownLatch(1);

        CompletableFuture.supplyAsync(CompletableFutureDemo::fetchPrice)
                .thenAccept(result -> {
                    System.out.printf("执行成功,返回结果:%s\n", result);
                    countDownLatch.countDown();
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    countDownLatch.countDown();
                    return null;
                });

        countDownLatch.await();
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5 + Math.random() * 20;
    }
}
