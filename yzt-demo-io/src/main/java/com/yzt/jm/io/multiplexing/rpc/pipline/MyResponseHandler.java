package com.yzt.jm.io.multiplexing.rpc.pipline;

import com.yzt.jm.io.multiplexing.rpc.CallbackFutureMap;
import com.yzt.jm.io.multiplexing.rpc.protocol.MsgPack;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-13 14:10
 */
@Slf4j
public class MyResponseHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgPack pack = (MsgPack) msg;
        CallbackFutureMap.runCallBack(pack);
    }
}
