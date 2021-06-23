package com.yzt.jm.io.multiplexing.reactor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author jiemin
 */
@Slf4j
public class SelectorThread extends ThreadLocal<LinkedBlockingQueue<Channel>> implements Runnable{

    public Selector selector;
    private SelectorThreadGroup stg;
    @Getter
    private LinkedBlockingQueue<Channel> lbq = get();

    public SelectorThread(SelectorThreadGroup stg) {
        try {
            log.info("【初始化selector线程】");
            selector = Selector.open();
            this.stg = stg;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected LinkedBlockingQueue<Channel> initialValue() {
        return new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int num = selector.select();
                log.info("select keys {}", selector.keys());
                if (num > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        }
                    }
                }
                //3.other task
//                LinkedBlockingQueue<Channel> channels = get();
                if (!lbq.isEmpty()) {
                    Channel channel = lbq.take();
                    if (channel instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        log.info("register accept 线程：{}", Thread.currentThread().getName());
                    } else if (channel instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        log.info("register read 线程：{}", Thread.currentThread().getName());
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel)key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();

        while(true){
            try {
                int read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        client.write(buffer);
                    }
                } else if (read == 0) {
                    break;
                }else{
                    key.cancel();
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            log.info("client 连接进来了");
            stg.nextWorker(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
