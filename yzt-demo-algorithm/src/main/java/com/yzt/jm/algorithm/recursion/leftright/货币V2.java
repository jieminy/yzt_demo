package com.yzt.jm.algorithm.recursion.leftright;

import java.util.List;
import java.util.Set;
import java.util.Stack;

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
public class 货币V2 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 1};

//        System.out.print(process(a, 0, 4));
        System.out.print(dp(a, 4));
    }

    /**
     * @param coins 货币集合（每个货币认为都不同）
     * @param index 当前下标
     * @param rest  目标总额
     * @return 方法数
     */
    public static int process(int[] coins, int[] zhangs, int index, int rest) {
//        if (index == a.length) {
//            return rest == 0 ? 1 : 0;
//        }
//        return process(a, index + 1, rest) + process(a, index + 1, rest - a[index]);
        if (rest < 0) {
            return 0;
        }
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int num = 0;
        // 0 - 1张情况
//        for (int zhang = 0; zhang <= 1; zhang++) {
//            num += process(a, index + 1, rest - zhang * a[index]);
//        }

        // 0 - 无穷
//        for (int zhang = 0; zhang * a[index] < rest; zhang++) {
//            num += process(a, index + 1, rest - zhang * a[index]);
//        }

        //0 - 限定张数
        for (int zhang = 0; zhang * coins[index] < rest && zhang <= zhangs[index]; zhang++) {
            num += process(coins, zhangs, index + 1, rest - zhang * coins[index]);
        }
        return num;
    }

    //背包问题
    public static int processBag(int[] weight, int[] value, int index, int bagValid) {
        if (bagValid < 0) {
            return 0;
        }
        if (index == weight.length) {
            return 0;
        }
        int max;

        int yes = 0;
        if (bagValid - weight[index] >= 0) {
            yes = value[index] + processBag(weight, value, index + 1, bagValid - weight[index]);
        }

        int no = processBag(weight, value, index + 1, bagValid);

        max = Math.max(yes, no);

        return max;
    }

    public static int process(int pre, int rest){

        if(rest < 0){
            return 0;
        }
        if(pre > rest){
            return 0;
        }

        int num=0;

        for(int index= pre; index <= rest; index++){
            num += process(index, rest - index);
        }
        return num;
    }

    public static void process(char[] chars, int limit, int has, Stack<Character> ans){
        if(limit == has){
            System.out.print(ans);
            return;
        }

        for(int i=0; i<chars.length; i++){
            if(!ans.contains(chars[i])){
                ans.push(chars[i]);
            }
            process(chars, limit, has + 1, ans);
            ans.pop();
        }
    }

    /**
     * @param a   货币集合（每个货币认为都不同）
     * @param aim 目标总额
     * @return 方法数
     */
    public static int dp(int[] a, int aim) {
        int[][] cache = new int[a.length + 1][aim + 1];
        cache[a.length][0] = 1;
        //index要或者不要
        for (int index = a.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                cache[index][rest] = cache[index + 1][rest] + (rest - a[index] >= 0 ? cache[index + 1][rest - a[index]] : 0);
            }
        }
        return cache[0][aim];
    }
}
