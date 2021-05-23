package com.yzt.jm.algorithm.search;

/**
 * @Description:
 * 假设字符串str长度为N，想返回最长回文子串的长度
 * 时间复杂度O(N)
 * @Author: min
 * @Date: 2021-04-29 22:27
 */
public class Manacher {

    public static void main(String[] args){

        String c = "ap12321d12321";
        System.out.println(manacher(c));
        System.out.println(rt(c));
    }

    public static int manacher(String s){
        //先处理##
        char[] str = manacherString(s);
        //回文半径数组
        int[] pArr = new int[str.length];
        //回文当前右边界
        int r = -1;
        //回文当前中心
        int c = -1;
        int max = 0;
        for(int i=0; i<str.length; i++){
            // i在c 到 r中 有三种情况 的初始化
            //1. i'的左边界在L右   pArr[2*c-i]
            //2. i'的左边界在L上   r-i
            //3.i'的左边界在L左   半径 = r-i
            pArr[i] = r > i ? Math.min(pArr[2*c-i], r-i) : 1;

            while(i+pArr[i]<str.length && i-pArr[i] > -1){
                if(str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if(i + pArr[i] > r){
                r = i+pArr[i];
                c = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max-1;
    }

    public static int rt(String s){
        char[] str = manacherString(s);

        int max = 0;
        for(int i=0; i<str.length; i++){
            int l = i-1;
            int r = i+1;
            while(l>=0 && r < str.length && str[l] == str[r]){
                    l--;
                    r++;
            }
            max = Math.max(r - l - 1, max);
        }
        return max / 2;
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
