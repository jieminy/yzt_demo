package com.yzt.jm.io.multiplexing.rpc.client;

import com.yzt.jm.io.multiplexing.rpc.pipline.MyDecoder;
import com.yzt.jm.io.multiplexing.rpc.pipline.MyResponseHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 21:21
 */
public class ClientFactory {
    private static final ClientFactory factory = new ClientFactory();
    private Random random = new Random();
    private NioEventLoopGroup group;

    public ConcurrentHashMap<InetSocketAddress, ClientPool> pools = new ConcurrentHashMap<>();

    private ClientFactory() {
    }

    public static ClientFactory getFactory() {
        return factory;
    }

    public NioSocketChannel getClient(InetSocketAddress address) {
        int size = 10;
        ClientPool clientPool = pools.get(address);
        if (clientPool == null) {
            pools.putIfAbsent(address, new ClientPool(size));
            clientPool = pools.get(address);
        }
        int i = random.nextInt(size - 1);

        synchronized (clientPool.getLocks()[i]) {
            if (clientPool.getClients()[i] == null) {
                return creatClient(address);
            } else {
                return clientPool.getClients()[i];
            }
        }
    }

    public NioSocketChannel creatClient(InetSocketAddress address) {
        group = new NioEventLoopGroup(10);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nc) throws Exception {
                        nc.pipeline()
                                .addLast(new MyDecoder())
                                .addLast(new MyResponseHandler());
                    }
                })
                .connect(address);
        try {
            return (NioSocketChannel) connect.sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取client失败");
    }
}
