package me.szp.study.basic.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 孙志鹏
 * @since 2022/1/4 3:41 PM
 */
public class SocketServer {

    private static final Logger LOG = LoggerFactory.getLogger(SocketServer.class);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            LOG.info("服务启动成功,监听端口:{}", 9000);
            while (true) {
                executorService.execute(() -> {
                    try (
                            Socket socket = serverSocket.accept();
                            InputStream request = socket.getInputStream();
                            OutputStream response = socket.getOutputStream();
                    ) {

                        String body = getBody(request);
                        LOG.info("接收到客户端消息:{}", body);
                        response.write("成功".getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getBody(InputStream in) throws IOException {
        int bufLength = 1024;
        byte[] buf = new byte[bufLength];
        int length;

        StringBuilder message = new StringBuilder();

        while ((length = in.read(buf, 0, bufLength)) != -1) {
            message.append(new String(buf, 0, length, StandardCharsets.UTF_8));
        }
        return message.toString();
    }
}
