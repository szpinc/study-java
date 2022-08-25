package me.szp.study.basic.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 孙志鹏
 * @since 2022/1/4 11:48 AM
 */
public class NIOServer extends Thread {


    @Override
    public void run() {
        try (Selector selector = Selector.open();) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 9000));
            serverSocketChannel.configureBlocking(false);
            // 注册到Selector，并说明关注点
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // 阻塞就绪的selector
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    try (SocketChannel client = channel.accept()) {
                        client.write(Charset.defaultCharset().encode("Hello World!"));
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer();
        server.start();

        try (Socket client = new Socket(InetAddress.getLocalHost(), 9000)) {
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
