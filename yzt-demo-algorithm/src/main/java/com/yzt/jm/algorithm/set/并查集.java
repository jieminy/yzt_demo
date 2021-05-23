package com.yzt.jm.algorithm.set;

import lombok.Data;
import lombok.val;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-19 18:50
 */
public class 并查集 {
    @Data
    public static class Node<V>{
        V value;
        public Node(V v){
            this.value = v;
        }
    }

    public static class UnionFriend<V>{
        //节点本身和分装后节点映射
        public HashMap<V, Node<V>> nodes = new HashMap<>();
        //父亲节点存放集合
        public HashMap<Node<V>, Node<V>> parents = new HashMap<>();
        //并查集合数
        public HashMap<Node<V>, Integer> sizeMap = new HashMap<>();
        public UnionFriend(List<V> values){
            values.forEach((item)->{
                Node<V> node = new Node<>(item);
                nodes.put(item, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            });
        }

        //查找祖先
        public Node<V> findAncestor(Node<V>  node){
            Node<V> parent = parents.get(node);
            while(parent != node){
                parent = parents.get(parent);
            }
            return parent;
        }

        /**
         * 是否在同一集合
         * @param a 节点a
         * @param b 节点b
         * @return true/false
         */
        public boolean isSameSet(Node<V> a, Node<V> b){
            return findAncestor(a) == findAncestor(b);
        }

        /**
         * 合并
         * @param a 节点a
         * @param b 节点b
         */
        public void union(Node<V> a, Node<V> b){
            Integer sizeA = sizeMap.get(a);
            Integer sizeB = sizeMap.get(b);

            Node<V> max = sizeA >= sizeB ? a : b;
            Node<V> min = max == a ? b : a;

            parents.put(min, findAncestor(max));

            sizeMap.put(max, sizeA+sizeB);
            sizeMap.remove(min);
        }

    }
}
