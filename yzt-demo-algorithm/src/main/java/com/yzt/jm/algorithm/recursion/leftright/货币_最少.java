package com.yzt.jm.algorithm.recursion.leftright;

/**
 * @Description: 【货币_最少货币数】
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 * @Author: min
 * @Date: 2021-04-24 19:42
 */
public class 货币_最少 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2};

        System.out.println(process(a, 0, 4));
        System.out.println(dp(a, 4));
        System.out.println(dp2(a, 4));
    }

    /**
     * @param a     货币集合
     * @param index 下标
     * @param rest  剩余货币
     * @return 最少货币数
     */
    public static int process(int[] a, int index, int rest) {
        if (index == a.length) {
            //rest = 0 返回最少货币数量0，因为已经达成了。
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * a[index] <= rest; zhang++) {
            int next = process(a, index + 1, rest - zhang * a[index]);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, zhang + next);
            }
        }
        return min;
    }


    public static int dp(int[] a, int aim) {
        int n = a.length;
        int[][] cache = new int[n + 1][aim + 1];

        cache[n][0] = 0;
        for (int j = 1; j <= aim; j++) {
            cache[n][j] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * a[index] <= rest; zhang++) {
                    int next = cache[index + 1][rest - zhang * a[index]];
                    if (next != Integer.MAX_VALUE) {
                        min = Math.min(min, zhang + next);
                    }
                }
                cache[index][rest] = min;
            }
        }
        return cache[0][aim];
    }

    public static int dp2(int[] a, int aim) {
        int n = a.length;
        int[][] cache = new int[n + 1][aim + 1];

        cache[n][0] = 0;
        for (int j = 1; j <= aim; j++) {
            cache[n][j] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                cache[index][rest] = cache[index + 1][rest];
                if ((rest - a[index] > 0) && cache[index][rest - a[index]] != Integer.MAX_VALUE) {
                    cache[index][rest] = Math.min(cache[index][rest], cache[index][rest - a[index]] + 1);
                }
            }
        }
        return cache[0][aim];
    }
}
