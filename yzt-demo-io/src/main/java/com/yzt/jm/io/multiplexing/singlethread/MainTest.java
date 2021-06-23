package com.yzt.jm.io.multiplexing.singlethread;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-05 16:53
 */
@Slf4j
public class MainTest {
    public static void main(String[] args){
//        SocketMultiplexingSingleThread thread = new SocketMultiplexingSingleThread();
//        thread.start();

        Map<Integer, String> map = new HashMap<>();
        Integer a = 1;
        map.put(a, "aa");

        Long b = 1L;
        log.info("" + a.hashCode());
        log.info("" + b.hashCode());
//        map.get(b);
//        boolean c = (a == b);
        log.info(map.get(a));
    }
}
