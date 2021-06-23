package com.yzt.jm.io.multiplexing.rpc.client;

import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 21:27
 */
@Data
public class ClientPool {
    private NioSocketChannel[] clients;
    private Object[] locks;

    public ClientPool(int size) {
        clients = new NioSocketChannel[size];
        locks = new Object[size];
        for (int i = 0; i < size; i++) {
            locks[i] = new Object();
        }
    }

}
