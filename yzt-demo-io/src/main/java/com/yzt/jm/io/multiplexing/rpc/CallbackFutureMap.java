package com.yzt.jm.io.multiplexing.rpc;

import com.yzt.jm.io.multiplexing.rpc.protocol.MsgPack;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-13 11:18
 */
public class CallbackFutureMap {
    public static ConcurrentHashMap<Long, CompletableFuture> callbackFutures = new ConcurrentHashMap<>();

    public static void addCallBack(long requestId, CompletableFuture res) {
        callbackFutures.putIfAbsent(requestId, res);
    }

    public static void runCallBack(MsgPack msg) {
        CompletableFuture completableFuture = callbackFutures.get(msg.getHeader().getRequestId());
        completableFuture.complete(msg.getBody().getRes());
        remove(msg.getHeader().getRequestId());
    }

    public static void remove(long requestId) {
        callbackFutures.remove(requestId);
    }

}
