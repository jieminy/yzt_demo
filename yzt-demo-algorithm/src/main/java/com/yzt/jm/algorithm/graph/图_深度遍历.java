package com.yzt.jm.algorithm.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-11 19:20
 */
public class 图_深度遍历 {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        //用于存放遍历路径
        Stack<Node> stack = new Stack<>();
        //集合 用于存放遍历过的节点
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.print(node.value + "");
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if(!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.value + "");
                    break;
                }
            }
        }
    }
}
