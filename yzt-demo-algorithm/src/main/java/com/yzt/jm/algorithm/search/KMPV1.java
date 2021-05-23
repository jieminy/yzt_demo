package com.yzt.jm.algorithm.search;

/**
 * @Description: 手撕KMP
 * @Author: min
 * @Date: 2021-04-28 22:44
 */
public class KMPV1 {

    public static void  main(String[] args){
        String s1 = "abab12fcd";
        String s2 = "cd";
        System.out.println(getIndexOf(s1, s2));
    }

    public static int getIndexOf(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        //主串 模串的指针
        int i=0, j=0;

        //next数组
        int[] next = getNext(c2);

        while(i < c1.length && j < c2.length){
            if(c1[i] == c2[j]){
                i++;
                j++;
            }else if(next[j] != -1){
                j = next[j];
            }else{
                i++;
            }
        }
        return j == c2.length ? i-j : -1;
    }

    private static int[] getNext(char[] c2) {
        if(c2.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[c2.length];
        next[0] = -1;
        next[1] = 0;
        //compare next
        int cn = 0;
        //每次比较的是i-1
        int i = 2;

        while(i < c2.length){
            if(c2[i] == cn){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                i++;
            }
        }
        return next;
    }
}
