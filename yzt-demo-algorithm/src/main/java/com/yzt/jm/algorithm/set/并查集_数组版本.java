package com.yzt.jm.algorithm.set;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-19 18:50
 */
public class 并查集_数组版本 {

    public static void main(String[] args){
        int[][] m = new int[5][5];
        m[1][2] = m[2][1] = 1;
        m[3][4] = m[4][3] = 1;
         //{12} {34} {5}
        int circleNum = findCircleNum(m);
        System.out.println("认识人群：" + circleNum +"组");
    }

    public static int findCircleNum(int[][] m) {
        int row = m.length;
        // {0} {1} {2} {N-1}
       UnionFriend unionFriend = new UnionFriend(row);
        for(int i=0; i<=row-1; i++){
            for(int j=i+1; j<=m[i].length-1; j++){
                //i和j互相认识
                if(m[i][j] == 1){
                    unionFriend.union(i, j);
                }
            }
        }
        return unionFriend.sets;
    }

    public static class UnionFriend{
        //父亲节点存放集合
        public int[] parents;
        //并查集合数
        public int[] size;
        public int sets;
        public UnionFriend(int n){
            parents = new int[n];
            size = new int[n];
            sets = n;
            for (int i=0; i<n; i++){
                size[i] = 1;
                parents[i] = i;
            }
        }

        //查找祖先
        public int findAncestor(int node){
            while(node != parents[node]){
                node = parents[node];
            }
            return node;
        }

        /**
         * 是否在同一集合
         * @param nodeA 节点a
         * @param nodeB 节点b
         * @return true/false
         */
        public boolean isSameSet(int nodeA, int nodeB){
            return findAncestor(nodeA) == findAncestor(nodeB);
        }

        /**
         * 合并
         * @param a 节点a
         * @param b 节点b
         */
        public void union(int a, int b){
            Integer sizeA = size[a];
            Integer sizeB = size[b];

            int max = sizeA >= sizeB ? a : b;
            int min = max == a ? b : a;

            parents[min] = findAncestor(max);

            size[max] =  sizeA+sizeB;
            size[min] = 0;
            sets --;
        }

    }
}
