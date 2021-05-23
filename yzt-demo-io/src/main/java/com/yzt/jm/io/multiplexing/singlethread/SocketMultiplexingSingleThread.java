package com.yzt.jm.io.multiplexing.singlethread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 多路复用单线程版本
 * @Author: min
 * @Date: 2021-04-05 17:00
 */
public class SocketMultiplexingSingleThread {
    private ServerSocketChannel server = null;
    private Selector selector = null;
    private int port = 9000;

    private void initServer(){
        try {
            //1.socket
            server = ServerSocketChannel.open();
            //2.bind
            server.bind(new InetSocketAddress(port));
            //3.nonblocking
            server.configureBlocking(false);

            //如果在epoll模型下，open--》  epoll_create -> fd3
            selector = Selector.open();

            //4.listen
             /*
                register
                如果：
                select，poll：jvm里开辟一个数组 fd4 放进去
                epoll：  epoll_ctl(fd3,ADD,fd4,EPOLLIN
             */
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        initServer();

        System.out.print("客户端已启动...");
        try{
            while (true){
                Set<SelectionKey> keys = selector.keys();
                System.out.println(keys.size()+"   size");

                //1,调用多路复用器(select,poll  or  epoll  (epoll_wait))
            /*
            select()是啥意思：
            1，select，poll  其实  内核的select（fd4）  poll(fd4)
            2，epoll：  其实 内核的 epoll_wait()
            *, 参数可以带时间：没有时间，0  ：  阻塞，有时间设置一个超时
            selector.wakeup()  结果返回0

            懒加载：
            其实再触碰到selector.select()调用的时候触发了epoll_ctl的调用
             */
                while(selector.select()>0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if(key.isAcceptable()){
                            acceptHandler(key);
                        }else if(key.isReadable()){
//                            key.interestOps(key.interestOps() | ~SelectionKey.OP_READ);
                            readHandler(key);
                        }else if(key.isWritable()){
                            //写事件<--  send-queue  只要是空的，就一定会给你返回可以写的事件，就会回调我们的写方法
                            //你真的要明白：你想什么时候写？不是依赖send-queue是不是有空间（多路复用器能不能写是参考send-queue有没有空间）
                            //1，你准备好要写什么了，这是第一步
                            //2，第二步你才关心send-queue是否有空间
                            //3，so，读 read 一开始就要注册，但是write依赖以上关系，什么时候用什么时候注册
                            //4，如果一开始就注册了write的事件，进入死循环，一直调起！！！
//                            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                            writeHandler(key);
                        }

                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void writeHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel)key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        System.out.println("writeHandler");
        try {
            buffer.flip();
            while (buffer.hasRemaining()){
                client.write(buffer);
            }
            buffer.clear();
            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        key.cancel();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read;
        try{
            while(true){
                read = client.read(buffer);
                if(read > 0){
                    client.register(selector, SelectionKey.OP_WRITE, buffer);
                }else if(read == 0){
                    break;
                }else { //小于-1 异常关闭
                    client.close();
                    System.out.print("client 关闭连接" + client.getRemoteAddress());
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void acceptHandler(SelectionKey key) {
        try {
            //1.accept
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();

            //2.nonblocking
            client.configureBlocking(false);

            //register
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            client.register(selector, SelectionKey.OP_READ, byteBuffer);

            System.out.print("client 连接成功：{}" + client.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
