package com.yzt.jm.io.multiplexing.rpc;

import com.yzt.jm.io.multiplexing.rpc.pipline.MyDecoder;
import com.yzt.jm.io.multiplexing.rpc.pipline.MyRequestHandler;
import com.yzt.jm.io.multiplexing.rpc.proxy.MyProxy;
import com.yzt.jm.io.multiplexing.rpc.service.Car;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 20:00
 */
@Slf4j
public class RpcTest {

    public void startServer() {

        NioEventLoopGroup boss = new NioEventLoopGroup(10);

        ServerBootstrap sb = new ServerBootstrap();
        ChannelFuture future = sb.group(boss, boss)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nc) throws Exception {
//                        System.out.println("server accept cliet port: "+ nc.remoteAddress().getPort());
                        nc.pipeline()
                                .addLast(new MyDecoder())
                                .addLast(new MyRequestHandler());
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1", 9000));
        try {
            future.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //模拟provider and consumer
    @Test
    public void get() {
        new Thread(() -> {
            startServer();
        }).start();


        AtomicInteger act = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //代理发送
                Car proxy = MyProxy.getProxy(Car.class);
                String req = "di di di no: " + act.getAndIncrement();
                String res = proxy.go(req);
                log.info("request:{} , response:{}", req, res);
            }).start();
        }

        try {
            System.in.read();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}

