package com.yzt.jm.algorithm.recursion.leftright;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 【货币_定量】
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * @Author: min
 * @Date: 2021-04-24 16:02
 */
public class 货币_定量 {

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    public static void main(String[] args){
        int[] coins = new int[]{1,2};
        int[] zhangs = new int[]{4,2};
        System.out.println(process(coins, zhangs, 0, 4));
        System.out.println(dp(coins, zhangs, 4));
        System.out.println(dp2(coins, zhangs, 4));
    }
    public static int process(int[]coins, int[]zhangs, int index, int rest){
        if(index == coins.length){
            return rest == 0 ? 1: 0;
        }

        int ways=0;
        for(int zhang=0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++ ){
            ways += process(coins, zhangs, index + 1, rest - zhang * coins[index]);
        }
        return ways;
    }


    public static int dp(int[]coins, int[]zhangs, int aim){
        int n = coins.length;
        int[][] cache = new int[n+1][aim+1];
        cache[n][0] = 1;

        for(int index=n-1; index>=0;index--){
            for(int rest=0; rest<=aim;rest++){
                int ways=0;
                for(int zhang=0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++ ){
                    ways += cache[index + 1][rest - zhang * coins[index]];
                }
                cache[index][rest] = ways;
            }
        }

        return cache[0][aim];
    }

    /**
     * 复杂度优化
     * @param coins
     * @param zhangs
     * @param aim
     * @return
     */
    public static int dp2(int[]coins, int[]zhangs, int aim){
        int n = coins.length;
        int[][] cache = new int[n+1][aim+1];
        cache[n][0] = 1;

        for(int index=n-1; index>=0;index--){
            for(int rest=0; rest<=aim;rest++){
                cache[index][rest] = cache[index+1][rest];
                if(rest >= coins[index]){
                    cache[index][rest] += cache[index][rest - coins[index]];
                }

                if(rest - coins[index] * (zhangs[index] + 1) > 0){
                    cache[index][rest] -= cache[index+1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }

        return cache[0][aim];
    }
}
