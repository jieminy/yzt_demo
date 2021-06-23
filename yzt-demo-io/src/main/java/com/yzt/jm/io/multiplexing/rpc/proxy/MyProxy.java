package com.yzt.jm.io.multiplexing.rpc.proxy;

import com.yzt.jm.io.multiplexing.rpc.CallbackFutureMap;
import com.yzt.jm.io.multiplexing.rpc.client.ClientFactory;
import com.yzt.jm.io.multiplexing.rpc.protocol.MyBody;
import com.yzt.jm.io.multiplexing.rpc.protocol.MyHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 20:43
 */
@Slf4j
public class MyProxy {

    public static <T> T getProxy(Class<T> interfaceInfo) {
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};
        InetSocketAddress address = new InetSocketAddress("localhost", 9000);
        //封装远程调用
        return (T) Proxy.newProxyInstance(classLoader, methodInfo, (proxy, method, args) -> {

            MyBody myBody = new MyBody();
            myBody.setArgs(args);
            myBody.setMethodName(method.getName());
            myBody.setName(interfaceInfo.getName());
            myBody.setParameterTypes(method.getParameterTypes());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(myBody);
            byte[] msgBody = out.toByteArray();
            log.info("msgBody length:{}", msgBody.length);

            MyHeader myHeader = createMsHeader(msgBody);
            out.reset();
            oout = new ObjectOutputStream(out);
            oout.writeObject(myHeader);
            byte[] msgHeader = out.toByteArray();
            log.info("msgHeader length:{}", msgHeader.length);

//            CountDownLatch countDownLatch = new CountDownLatch(1);

            CompletableFuture<String> res = new CompletableFuture<>();
            CallbackFutureMap.addCallBack(myHeader.getRequestId(), res);
            ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);
            buf.writeBytes(msgHeader);
            buf.writeBytes(msgBody);
            //添加回调

            //客户端调用远程服务
            log.info("发起远程调用");
            NioSocketChannel client = ClientFactory.getFactory().getClient(address);
            ChannelFuture channelFuture = client.writeAndFlush(buf);
            channelFuture.sync();

//            countDownLatch.await();
            //添加回调
            return res.get();
        });

    }

    private static MyHeader createMsHeader(byte[] msgBody) {
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits());

        MyHeader myHeader = new MyHeader();
        myHeader.setFlag(0x14141414);
        myHeader.setRequestId(requestId);
        myHeader.setDataLen(msgBody.length);

        return myHeader;
    }
}
