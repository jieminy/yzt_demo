package com.yzt.jm.io.multiplexing.singlethread;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 多路复用单线程版本
 * @Author: min
 * @Date: 2021-04-05 17:00
 */
@Slf4j
public class SocketMultiplexingSingleThread {
    private ServerSocketChannel server;
    private Selector selector;
    private AtomicInteger cnt = new AtomicInteger(0);
    private void initServer(){
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(9000));
            server.configureBlocking(false);
            log.info("服务启动咯 port 9000");

            selector = Selector.open();

            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        initServer();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.info("selector注册了{}个key", selector.keys().size());
                if (selector.select() == 0) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    acceptHandler(key);
                } else if (key.isReadable()) {
                    readHandler(key);
                } else if (key.isWritable()) {
                    writeHandler(key);
                }

            }
        }
    }

    private void writeHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();

        while (buffer.hasRemaining()) {
            try {
                client.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buffer.clear();
        key.cancel();
        try {
            client.close();
        } catch (Exception e) {
            log.info("服务完成回写数据并且断开连接");
        }
    }

    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int sum = 0;
        try {
            while(true){
                Thread.sleep(1000);
                sum = client.read(buffer);
                if (sum > 0) {
                    client.register(selector, SelectionKey.OP_WRITE, buffer);
                } else if (sum == 0) {
                    break;
                } else {
                    client.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            log.info("服务{}连接进来咯", cnt.incrementAndGet());

            ByteBuffer buffer = ByteBuffer.allocate(4096);
            client.register(selector, SelectionKey.OP_READ, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SocketMultiplexingSingleThread singleThread = new SocketMultiplexingSingleThread();
        singleThread.start();
    }


}
