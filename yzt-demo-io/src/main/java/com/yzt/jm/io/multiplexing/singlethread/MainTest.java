package com.yzt.jm.io.multiplexing.singlethread;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-05 16:53
 */
public class MainTest {
    public static void main(String[] args){
        SocketMultiplexingSingleThread thread = new SocketMultiplexingSingleThread();
        thread.start();
    }
}
