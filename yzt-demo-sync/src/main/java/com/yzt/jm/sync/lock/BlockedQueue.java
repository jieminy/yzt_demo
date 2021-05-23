package com.yzt.jm.sync.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 阻塞队列
 * @author: jiemin
 * @date: 2021-01-23 11:32
 */
public class BlockedQueue {
    private final int MAX_LENGTH = 5;
    List<String> list = new ArrayList<>(MAX_LENGTH);

    public void set(String item){
        list.add(item);
    }

    public String get(){
        return list.get(0);
    }

    public boolean isFull(){
        return MAX_LENGTH - list.size() == 0;
    }

    public boolean isEmpty(){
        return list.size() == 0;
    }
}
