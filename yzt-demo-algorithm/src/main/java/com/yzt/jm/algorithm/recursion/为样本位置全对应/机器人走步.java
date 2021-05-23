package com.yzt.jm.algorithm.recursion.为样本位置全对应;

/**
 * @Description:
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 * @Author: min
 * @Date: 2021-04-21 19:05
 */
public class 机器人走步 {
    public static void main(String[] args){
        System.out.println(go(6, 3, 7, 10));
        System.out.println(dp(3, 7, 6, 10));
    }
    /**
     * @param rest 剩余rest步
     * @param cur 起始
     * @param to 目标位置
     * @param n n个位置
     * @return 多少种走法
     */
    public static int go(int rest, int cur, int to, int n){
        if(rest == 0){
            return cur == to ? 1 : 0;
        }
        //起始在1
        if(cur == 1){
            return go(rest-1, 2, to, n);
        }else if(cur == n){
            //起始在N
            return go(rest-1, n-1, to, n);
        }else{
            //在中间
            return go(rest-1, cur-1,to, n) + go(rest-1, cur+1, to, n);
        }
    }

    public static int dp(int from, int k, int to, int n){
        int[][] cache = new int[n+1][k+1];
        cache[to][0] = 1;
        for(int rest=1; rest<k; rest++){
            cache[1][rest] = cache[2][rest-1];
            for(int i=2; i<n-1; i++){
                cache[i][rest] = cache[i-1][rest-1] + cache[i+1][rest-1];
            }
            cache[n][rest] = cache[n-1][rest-1];
        }

        return cache[from][k];
    }



}
