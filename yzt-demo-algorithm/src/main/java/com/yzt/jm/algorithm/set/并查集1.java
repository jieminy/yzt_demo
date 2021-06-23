package com.yzt.jm.algorithm.set;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-19 18:50
 */
public class 并查集1 {
    @Data
    public static class Node<T> {
        T value;

        public Node(T t) {
            this.value = t;
        }
    }

    public static class UnionFriend<T> {
        public HashMap<T, Node<T>> nodes = new HashMap<>();
        //初始化所有节点父节点
        public HashMap<Node<T>, Node<T>> parents = new HashMap<>();
        //并查集的size
        public HashMap<Node<T>, Integer> sizeMap = new HashMap<>();

        public UnionFriend(List<Node<T>> list) {
            list.forEach(item -> {
                nodes.put(item.value, item);
                parents.put(item, item);
                sizeMap.put(item, 0);
            });
        }

        //查找祖先
        private Node<T> findAncestor(Node<T> node) {
            Node<T> pNode = parents.get(node);
            while (pNode != null && pNode != node) {
                pNode = parents.get(pNode);
            }
            return pNode;
        }

        //是否是相同祖先
        private boolean isSameAncestor(Node<T> a, Node<T> b) {
            return findAncestor(a).equals(findAncestor(b));
        }

        //合并
        private void union(Node<T> a, Node<T> b) {
            if (isSameAncestor(a, b)) {
                return;
            }
            Integer sizeA = sizeMap.get(a);
            Integer sizeB = sizeMap.get(b);
            //todo 有问题  小集合所有父节点都需要改

            Node<T> max = sizeA > sizeB ? a : b;
            Node<T> min = a.equals(max) ? b : a;

            parents.put(min, findAncestor(max));
            sizeMap.put(max, sizeA + sizeB);
            sizeMap.remove(min);
        }
    }


}

