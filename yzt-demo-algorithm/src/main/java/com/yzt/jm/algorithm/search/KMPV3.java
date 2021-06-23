package com.yzt.jm.algorithm.search;

/**
 * @Description: 手撕KMP
 * @Author: min
 * @Date: 2021-04-28 22:44
 */
public class KMPV3 {

    public static void main(String[] args) {
        String s1 = "abab12fcdfc2";
        String s2 = "fc2abcfc24adfc2abc";
        System.out.println(getIndexOf(s1, s2));
    }

    public static int getIndexOf(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        //获取next数组
        int[] next = getNext(c2);
        //俩个下标 i主串匹配位置 j模串位置
        int i = 0, j = 0;
        //参考next数组匹配
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            }//如果没有匹配到，模串参考next数组最大成都右移
            else if (next[j] != -1) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == s2.length() ? i - j : -1;
    }

    private static int[] getNext(char[] c2) {
        //初始化next数组
        int[] next = new int[c2.length];
        next[0] = -1;
        next[1] = 0;
        //依托自身
        int i = 2;
        int cn = 0;
        while (i < c2.length) {
            if (c2[i] == c2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }

        return next;
    }
}
