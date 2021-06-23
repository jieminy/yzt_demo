package com.yzt.jm.io.multiplexing.rpc.pipline;

import com.yzt.jm.io.multiplexing.rpc.protocol.MsgPack;
import com.yzt.jm.io.multiplexing.rpc.protocol.MyBody;
import com.yzt.jm.io.multiplexing.rpc.protocol.MyHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-13 11:40
 */
@Slf4j
public class MyDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int headSize = 118;
        while (byteBuf.readableBytes() >= headSize) {
            byte[] bytes = new byte[headSize];
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            MyHeader myHeader = (MyHeader) ois.readObject();

            if (byteBuf.readableBytes() >= myHeader.getDataLen()) {
                byteBuf.readBytes(headSize);

                byte[] data = new byte[(int) myHeader.getDataLen()];
                byteBuf.readBytes(data);
                ByteArrayInputStream din = new ByteArrayInputStream(data);
                ObjectInputStream dis = new ObjectInputStream(din);

                if (myHeader.getFlag() == 0x14141414) {
                    MyBody myBody = (MyBody) dis.readObject();
                    list.add(new MsgPack(myHeader, myBody));
                } else if (myHeader.getFlag() == 0x14141424) {
                    MyBody myBody = (MyBody) dis.readObject();
                    list.add(new MsgPack(myHeader, myBody));
                }

            } else {
                break;
            }
        }

    }
}
