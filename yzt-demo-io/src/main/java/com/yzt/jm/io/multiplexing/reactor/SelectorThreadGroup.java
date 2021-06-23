package com.yzt.jm.io.multiplexing.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiemin
 */
@Slf4j
public class SelectorThreadGroup {  //天生都是boss
    SelectorThread[] stgs;
    AtomicInteger act = new AtomicInteger(0);
    SelectorThreadGroup worker = this;

    public SelectorThreadGroup(int num) {
        stgs = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            stgs[i] = new SelectorThread(this);
            new Thread(stgs[i]).start();
        }
    }

    public void setWorker(SelectorThreadGroup worker) {
        this.worker = worker;
    }

    public void bind(int port) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            log.info("【server 启动 port:{}】", port);
            nextBoss(server);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SelectorThread next() {
        int num = act.getAndIncrement();
        return stgs[num % (stgs.length)];
    }

    public void nextBoss(Channel server) {
        SelectorThread selectorThread = next();
        try {
            selectorThread.getLbq().put(server);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectorThread.selector.wakeup();
    }

    public void nextWorker(Channel client) {
        int num = act.getAndIncrement();
        SelectorThread worker = this.worker.stgs[num % (stgs.length)];
        try {
            worker.getLbq().put(client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.selector.wakeup();
    }

}
