package me.szp.study.basic.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author 孙志鹏
 * @since 2022/1/4 3:03 PM
 */
public class SocketClient implements Runnable {

    private final Logger log = LoggerFactory.getLogger(SocketClient.class);

    private final CountDownLatch countDownLatch;

    private final int clientIndex;


    public SocketClient(CountDownLatch countDownLatch, int clientIndex) {
        this.countDownLatch = countDownLatch;
        this.clientIndex = clientIndex;
    }

    @Override
    public void run() {

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), 9000);
                InputStream response = socket.getInputStream();
                OutputStream request = socket.getOutputStream();
        ) {
            // 等待，知道所有客户端线程初始化完成一起启动
            countDownLatch.await();
            // 请求内容
            String requestContent = String.format("这是第%d个客户端", clientIndex);
            log.info("请求内容:{}", requestContent);
            request.write(requestContent.getBytes(StandardCharsets.UTF_8));
            request.flush();

            log.info("第{}个客户端请求发送完成，等待服务响应...", clientIndex);

            int maxLength = 1024;
            byte[] buf = new byte[maxLength];
            int readLength;

            StringBuilder sb = new StringBuilder();

            while ((readLength = response.read(buf, 0, maxLength)) != -1) {
                sb.append(new String(buf, 0, readLength, StandardCharsets.UTF_8));
            }
            log.info("接收到来自服务端的信息:{}", sb);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
