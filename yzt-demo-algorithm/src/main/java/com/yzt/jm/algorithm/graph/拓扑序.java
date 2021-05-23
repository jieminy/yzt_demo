package com.yzt.jm.algorithm.graph;

import java.util.*;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-11 19:20
 */
public class 拓扑序 {

    public static List<Node> sortedTopology(Graph graph) {
        List<Node> ans = new ArrayList<>();

        //入度缓存
        Map<Node, Integer> mapIn = new HashMap<>();
        //零入度队列
        Queue<Node> zeroQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroQueue.add(node);
            }
            mapIn.put(node, node.in);
        }

        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            ans.add(cur);
            for (Node next : cur.nexts) {
                mapIn.put(next, mapIn.get(next) - 1);
                if (mapIn.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }

        return ans;
    }
}
