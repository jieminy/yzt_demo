package com.yzt.jm.algorithm.search;

/**
 * @Description: 假设字符串str长度为N，想返回最长回文子串的长度
 * 时间复杂度O(N)
 * @Author: min
 * @Date: 2021-04-29 22:27
 */
public class ManacherV2 {

    public static void main(String[] args) {

        String c = "ap12321d12321";
        System.out.println(manacher(c));
    }

    public static int manacher(String s) {
        //回文半径
        int r = -1;
        //回文中点
        int c = -1;
        //最大半径
        int max = 0;

        //加工数组
        char[] str = manacherString(s);
        //参考数组
        int[] pArr = new int[s.length()];
        //主流程
        for (int i = 0; i < s.length(); i++) {
            pArr[i] = r > i ? Math.min(pArr[2 * c - i], r - i) : 1;
            while (i + pArr[i] < s.length() && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(pArr[i], max);
        }
        return max;
    }


    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
}
