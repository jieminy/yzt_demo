package com.yzt.jm.algorithm.recursion.leftright;

/**
 * @Description: 【货币】
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * @Author: min
 * @Date: 2021-04-24 12:28
 */
public class 货币 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 1};

        System.out.print(process(a, 0, 4));
        System.out.print(dp(a, 4));
    }

    /**
     * @param a     货币集合（每个货币认为都不同）
     * @param index 当前下标
     * @param rest  目标总额
     * @return 方法数
     */
    public static int process(int[] a, int index, int rest) {
        if(rest < 0){
            return 0;
        }
        if (index == a.length) {
            return rest == 0 ? 1 : 0;
        }
        //index要或者不要
        return process(a, index + 1, rest) + process(a, index + 1, rest - a[index]);
    }

    /**
     * @param a     货币集合（每个货币认为都不同）
     * @param aim  目标总额
     * @return 方法数
     */
    public static int dp(int[] a, int aim) {
        int[][] cache = new int[a.length + 1][aim + 1];
        cache[a.length][0] = 1;
        //index要或者不要
        for(int index = a.length-1; index>=0; index--){
            for(int rest=0; rest<=aim; rest++){
                cache[index][rest] = cache[index+1][rest] + (rest-a[index] >= 0 ? cache[index+1][rest-a[index]] : 0);
            }
        }
        return cache[0][aim];
    }
}
