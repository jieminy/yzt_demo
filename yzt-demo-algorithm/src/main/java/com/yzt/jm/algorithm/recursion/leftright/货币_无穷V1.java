package com.yzt.jm.algorithm.recursion.leftright;

/**
 * @Description: 【货币不限制张数】
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * @Author: min
 * @Date: 2021-04-24 15:17
 */
public class 货币_无穷V1 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2};

        System.out.println(process(a, 0, 4));
        System.out.println(dp(a, 4));
        System.out.println(dp2(a, 4));
    }

    /**
     * @param a     货币面值集合（无限张）
     * @param index 当前货币下标
     * @param rest  目标
     * @return 总方法数
     */
    public static int process(int[]a, int index, int rest) {
        //index 0~n尝试
        if(rest<0){
            return 0;
        }
        if(index == a.length){
            return rest ==0? 1: 0;
        }
        int ways=0;
        for(int zhang=0; zhang * a[index] <= rest; zhang++){
            ways += process(a, index+1, rest- zhang* a[index]);
        }
        return ways;
    }

    /**
     * @param a     货币面值集合（无限张）
     * @param aim  目标
     * @return 总方法数
     */
    public static int dp(int[] a, int aim) {
        int n = a.length;
        int[][] cache = new int[n+1][aim+1];

        cache[n][0] = 1;



        for(int index= n-1; index>=0; index--){
            //一定要记得该aim 为 rest
            for(int rest= 0; rest<=aim; rest++){
                int ways = 0;
                for (int zhang = 0; zhang * a[index] <= rest; zhang++) {
                    ways += cache[index+1][rest - (zhang * a[index])];
                }
                cache[index][rest] = ways;
            }
        }

        return cache[0][aim];
    }

    /**
     * @param a     货币面值集合（无限张）
     * @param aim  目标
     * @return 总方法数
     */
    public static int dp2(int[] a, int aim) {
        int n = a.length;
        int[][] cache = new int[n+1][aim+1];
        cache[n][0] = 1;

        for(int index= n-1; index>=0; index--){
            //一定要记得该aim 为 rest
            for(int rest= 0; rest<=aim; rest++){
                cache[index][rest] = cache[index + 1][rest];
                if(rest - a[index] >= 0){
                    cache[index][rest] += cache[index][rest - a[index]];
                }
            }
        }

        return cache[0][aim];
    }
}
