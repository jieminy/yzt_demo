package com.yzt.jm.algorithm.recursion.leftright;

/**
 * @Description:
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * @Author: min
 * @Date: 2021-04-21 23:45
 */
public class 背包问题 {

    public static void main(String[] args){
        int[] w = new int[]{2, 5, 3, 1, 10};
        int[] v = new int[]{6, 2, 5, 3, 5};
        System.out.println(recursion(w, v, 0, 15));
        System.out.println(dp(w, v, 15));
    }

    /**
     *
     * @param w 重量
     * @param v 价值
     * @param index 当前位置
     * @param bag 背包容量
     * @return 最大价值
     */
    public static int recursion(int[] w, int[] v, int index, int bag){
        if(bag<0){
            return -1;
        }
        if(index == w.length){
            return 0;
        }
        //不要
        int no = recursion(w, v, index+1, bag);
        //要
        int restBag = bag - w[index];
        int yes = 0;
        if(restBag >= 0){
            yes = v[index] + recursion(w, v, index+1, bag - w[index]);
        }
        return Math.max(yes, no);
    }


    public static int dp(int[] w, int[] v, int bag){
        int n = w.length;
        int[][] cache = new int[n+1][bag+1];
        //index = length
        for(int i=n-1; i>0;i--){
            for(int j=0; j<=bag; j++){
                int no = cache[i+1][j];
                int restBag = bag - w[i];
                int yes = 0;
                if(restBag > 0){
                    yes = v[i] + cache[i+1][bag-w[i]];
                }
                cache[i][j] = Math.max(yes, no);
            }

        }
        return cache[0][bag];
    }
}
