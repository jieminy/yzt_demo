package com.yzt.jm.io.multiplexing.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 09:49
 */
@Slf4j
public class NettyTest {

    @Test
    public void client() throws InterruptedException {
        EventLoopGroup worker = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture future = bs.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nsc) throws Exception {
                        ChannelPipeline pipeline = nsc.pipeline();
                        pipeline.addLast(new MyInHandler());
                    }
                })
                .connect(new InetSocketAddress("127.0.0.1", 9000));
        Channel client = future.sync().channel();
        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture channelFuture = client.writeAndFlush(buf);
        channelFuture.sync();

        client.closeFuture().sync();
    }

    @Test
    public void server() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture future = bs.group(boss)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nc) throws Exception {
                        ChannelPipeline pipeline = nc.pipeline();
                        pipeline.addLast(new MyInHandler());
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1", 9000));
        future.sync().channel().closeFuture().sync();
    }

    private class MyInHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            CharSequence charSequence = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
            log.info(charSequence.toString());
            ctx.writeAndFlush(buf);
        }
    }
}
