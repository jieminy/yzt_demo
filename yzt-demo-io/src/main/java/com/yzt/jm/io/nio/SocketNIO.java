package com.yzt.jm.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-05 19:56
 */
@Slf4j
public class SocketNIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<SocketChannel> clients = new ArrayList<>();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9000));
        server.configureBlocking(false);

        while (true) {
            SocketChannel client = server.accept();
            Thread.sleep(2000);
            if (client != null) {
                client.configureBlocking(false);
                clients.add(client);
                log.info("client in");
            } else {
                log.info("server is listening client in");
            }
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            clients.forEach(item -> {
                try {
                    int num = item.read(buffer);
                    if (num > 0) {
                        buffer.flip();
                        byte[] a = new byte[buffer.limit()];
                        buffer.get(a);
                        log.info("" + new String(a));
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
