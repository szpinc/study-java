package me.szp.study.basic.bio;

import java.util.concurrent.CountDownLatch;

/**
 * @author 孙志鹏
 * @since 2022/1/4 4:53 PM
 */
public class SocketClientDaemon {
    public static void main(String[] args) throws InterruptedException {
        int clientCount = 20;

        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        for (int i = 0; i < clientCount; i++, countDownLatch.countDown()) {
            SocketClient socketClient = new SocketClient(countDownLatch, i);
            new Thread(socketClient).start();
        }
        synchronized (SocketClientDaemon.class) {
            SocketClientDaemon.class.wait();
        }
    }
}
