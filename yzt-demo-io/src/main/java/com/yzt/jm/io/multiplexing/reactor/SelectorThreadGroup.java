package com.yzt.jm.io.multiplexing.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author jiemin
 */
public class SelectorThreadGroup {  //天生都是boss

    private SelectorThread[] sts;
    private ServerSocketChannel server = null;
    private AtomicInteger xid = new AtomicInteger(0);

    private SelectorThreadGroup stgWorker = this;

    public void setWorker(SelectorThreadGroup  stgWorker){
        this.stgWorker =  stgWorker;
    }

    SelectorThreadGroup(int num){
        //num  线程数
        sts = new SelectorThread[num];
        for(int i=0; i<=num-1; i++){
            sts[i] = new SelectorThread(this);
            new Thread(sts[i]).start();
        }
    }



    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            nextSelector(server);
            System.out.print("Server is started, port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void nextSelector(Channel c) {
        SelectorThread st;
        if(c instanceof ServerSocketChannel){
            st = nextBoss();
            st.setWorker(stgWorker);
        }else{
            st = next();
        }
        try {
            //1.通过消息队列传递数据
            st.lbq.put(c);
            //2.通过打断阻塞，让对应的线程去自己在打断后完成注册selector
            st.selector.wakeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 无论 serversocket  socket  都复用这个方法
     */
    private SelectorThread nextBoss() {
        //轮询就会很尴尬，倾斜
        int index = xid.incrementAndGet() % this.sts.length;
        return sts[index];
    }

    /**
     * 无论 serversocket  socket  都复用这个方法
     */
    private SelectorThread next() {
        //轮询就会很尴尬，倾斜
        int index = xid.incrementAndGet() % this.stgWorker.sts.length;
        return this.stgWorker.sts[index];
    }

}
