package com.yzt.jm.algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-11 19:20
 */
public class 图_广度遍历 {

    public static void bfs(Node node){
        if(node == null){
            return;
        }
        //存放每一层
        Queue<Node> queue = new LinkedList<>();
        //存放遍历过的节点
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.value + "");
            for (Node next : cur.nexts) {
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
