package com.yzt.jm.algorithm.recursion.范围尝试;

/**
 * @Description: 【最长公共子序列】
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * @Author: min
 * @Date: 2021-04-22 22:41
 */
public class 最长公共子序列 {

    public static void main(String[] args) {
        char[] a = "123abbcc3".toCharArray();
        char[] b = "ai3abbcc99".toCharArray();
        System.out.println(process(a, b, a.length - 1, b.length - 1));
        System.out.println(dp(a, b));
    }

    /**
     * @param a 串a
     * @param b 串b
     * @param i a下标
     * @param j b下标
     * @return 每个递归里得最长子序列
     */
    public static int process(char[] a, char[] b, int i, int j) {
        if (i == 0 && j == 0) {
            return a[0] == b[0] ? 1 : 0;
        } else if (i == 0) {
            if (a[0] == b[j]) {
                return 1;
            }
            return process(a, b, 0, j - 1);
        } else if (j == 0) {
            if (a[i] == b[0]) {
                return 1;
            }
            return process(a, b, i - 1, 0);
        } else {
            // i√ j不一定
            int p1 = process(a, b, i - 1, j);
            // i不一定 j√
            int p2 = process(a, b, i, j - 1);
            // i√ j√
            int p3 = a[i] == b[j] ? (1 + process(a, b, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int dp(char[] a, char[] b) {
        int aLen = a.length;
        int bLen = b.length;
        // [1,2,3]
        // [4,3,2,5]
        int[][] cache = new int[aLen][bLen];

        cache[0][0] = a[0] == b[0] ? 1 : 0;

        for (int j = 1; j < bLen; j++) {
            cache[0][j] = a[0] == b[j] ? 1 : cache[0][j - 1];
        }

        for (int i = 1; i < aLen; i++) {
            cache[i][0] = a[i] == b[0] ? 1 : cache[i - 1][0];
        }
        for (int i = 1; i < aLen; i++) {
            for (int j = 1; j < bLen; j++) {
                int p1 = cache[i - 1][j];
                int p2 = cache[i][j - 1];
                int p3 = a[i] == b[j] ? (1 + cache[i - 1][j - 1]) : 0;
                cache[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return cache[aLen-1][bLen-1];
    }
}
