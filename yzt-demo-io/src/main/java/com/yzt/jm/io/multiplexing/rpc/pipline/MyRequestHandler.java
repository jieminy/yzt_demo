package com.yzt.jm.io.multiplexing.rpc.pipline;

import com.yzt.jm.io.multiplexing.rpc.SeqUtil;
import com.yzt.jm.io.multiplexing.rpc.protocol.MsgPack;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 20:03
 */
@Slf4j
public class MyRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgPack pack = (MsgPack) msg;
        String ioThreadName = Thread.currentThread().getName();
        ctx.executor().parent().next().execute(() -> {
            String bizThreadName = Thread.currentThread().getName();
            String res = "ioThread: {" + ioThreadName + "} bizThread: {" + bizThreadName + "}, req: {" + pack.getBody().getArgs()[0] + "}";
            pack.getBody().setRes(res);
            pack.getHeader().setFlag(0x14141424);
            byte[] bodyBytes = SeqUtil.objToBytes(pack.getBody());
            pack.getHeader().setDataLen(bodyBytes.length);

            byte[] headerBytes = SeqUtil.objToBytes(pack.getHeader());
            ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(headerBytes.length + bodyBytes.length);
            buf.writeBytes(headerBytes);
            buf.writeBytes(bodyBytes);
            ctx.writeAndFlush(buf);
//            log.info("ioThread: {}, bizThread: {}, res: {}", ioThreadName, bizThreadName, pack.getBody().getRes());
        });

    }
}
