package com.yzt.jm.io.multiplexing.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author jiemin
 */
public class SelectorThread extends ThreadLocal<LinkedBlockingQueue<Channel>> implements Runnable{
    // 每线程对应一个selector，
    // 多线程情况下，该主机，该程序的并发客户端被分配到多个selector上
    //注意，每个客户端，只绑定到其中一个selector
    //其实不会有交互问题

    private SelectorThreadGroup stg;
    LinkedBlockingQueue<Channel> lbq = get();
    Selector selector = null;

    @Override
    protected LinkedBlockingQueue<Channel> initialValue() {
        return new LinkedBlockingQueue<>();
    }

    SelectorThread(SelectorThreadGroup stg){
        this.stg = stg;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                //1,select()
                int num = selector.select();
                //2,处理selectkeys
                if (num > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if(key.isAcceptable()){
                            acceptHandler(key);
                        }else if(key.isReadable()){
                            readHandler(key);
                        }else if(key.isWritable()){
                            System.out.print("writeHandler");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //3,处理一些task :  listen  client
        if(!lbq.isEmpty()){
            try {
                Channel channel = lbq.take();
                if(channel instanceof ServerSocketChannel){
                    ServerSocketChannel server = (ServerSocketChannel) channel;
                    //listen
                    server.register(selector, SelectionKey.OP_ACCEPT);
                    System.out.println(Thread.currentThread().getName()+" register listen");
                }else if(channel instanceof SocketChannel){
                    SocketChannel client = (SocketChannel)channel;
                    ByteBuffer buffer = ByteBuffer.allocate(4096);
                    client.register(selector, SelectionKey.OP_READ, buffer);
                }
            } catch (InterruptedException | ClosedChannelException e) {
                e.printStackTrace();
            }
        }
    }

    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel)key.channel();

        ByteBuffer buffer = (ByteBuffer) key.attachment();

        while(true){
            try {
                int num = client.read(buffer);
                if(num > 0){
                    buffer.flip();  //将读到的内容翻转，然后直接写出
                    while(buffer.hasRemaining()){
                        client.write(buffer);
                    }
                    buffer.clear();
                }else if(num == 0){
                    break;
                }else{
                    client.close();
                    System.out.println("client: " + client.getRemoteAddress()+"closed......");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            //1.accept
            SocketChannel client = server.accept();
            //2.nonblocking
            client.configureBlocking(false);
            //3.register
            stg.nextSelector(client);
            System.out.println("client is accept" + client.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setWorker(SelectorThreadGroup stgWorker) {
        this.stg =  stgWorker;
    }
}
