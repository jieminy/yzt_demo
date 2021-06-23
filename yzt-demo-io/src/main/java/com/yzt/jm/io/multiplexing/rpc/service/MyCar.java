package com.yzt.jm.io.multiplexing.rpc.service;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 20:23
 */
public class MyCar implements Car {
    @Override
    public String go(String msg) {
        System.out.print("car is going : " + msg);
        return msg;
    }
}
