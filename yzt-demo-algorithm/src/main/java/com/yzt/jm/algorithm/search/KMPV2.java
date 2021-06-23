package com.yzt.jm.algorithm.search;

/**
 * @Description: 手撕KMP
 * @Author: min
 * @Date: 2021-04-28 22:44
 */
public class KMPV2 {

    public static void main(String[] args) {
        String s1 = "abab12fcd";
        String s2 = "cd";
        System.out.println(getIndexOf(s1, s2));
    }

    public static int getIndexOf(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int i = 0;
        int j = 0;
        int[] next = getNext(c2);
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else if (next[j] != -1) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == c2.length ? i - j : -1;
    }

    private static int[] getNext(char[] c2) {

        int i = 2;
        int cn = 0;
        int[] next = new int[c2.length];
        next[0] = -1;
        next[1] = 0;
        while (i < c2.length) {
            if (c2[i] == c2[cn]) {
                next[i++] = ++cn;
            }// 结束条件 cn必须归-1
            else if (cn > 0) {
                cn = next[cn];
            } else {
                i++;
            }
        }
        return next;
    }
}
