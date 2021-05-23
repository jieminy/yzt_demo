package com.yzt.jm.algorithm.graph;

import java.util.*;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-11 21:10
 */
public class Kruskal {
    public static class UnionFriend{
        private Map<Node, Node> parents = new HashMap<>();
        private Map<Node, Integer> sizeMap = new HashMap<>();

        public void makeSet(Collection<Node> nodes){
            parents.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node getFather(Node node){
            Stack<Node> stack = new Stack<>();
            while(node != parents.get(node)){
                stack.push(node);
                node = parents.get(node);
            }

            while(!stack.isEmpty()){
                parents.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node a, Node b){
            return getFather(a).equals(getFather(b));
        }

        public void union(Node a, Node b){
            Node pa = getFather(a);
            Node pb = getFather(b);

            if(pa.equals(pb)){
                return;
            }

            Node max = sizeMap.get(a) >= sizeMap.get(b) ? a : b;
            Node min = a.equals(max) ? b : a;
            parents.put(min, getFather(max));
            sizeMap.put(max, sizeMap.get(a) + sizeMap.get(b));
            sizeMap.remove(min);
        }
    }

    public static Set<Edge> kruskal(Graph graph){
        UnionFriend unionFriend = new UnionFriend();
        unionFriend.makeSet(graph.nodes.values());

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        queue.addAll(graph.edges);

        Set<Edge> ans = new HashSet<>();
        while(!queue.isEmpty()){
            Edge e = queue.poll();
            if(!unionFriend.isSameSet(e.from, e.to)){
                unionFriend.union(e.from, e.to);
                ans.add(e);
            }
        }

        return ans;
    }
}
