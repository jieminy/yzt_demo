package com.yzt.jm.algorithm.search;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-28 22:44
 */
public class KMP {

    public static void  main(String[] args){
        String s1 = "abab12fcd";
        String s2 = "ab12ab";
        System.out.println(getIndexOf(s1, s2));
    }

    public static int getIndexOf(String s1, String s2){
        if(s1.length()==0 || s2.length() == 0 || s1.length() < s2.length()){
            return -1;
        }
        //转成数组
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int i=0,j=0;
        //next数组
        int[] next = getNext(c2);
        //依次遍历c1
        while(i<c1.length && j<c2.length){
            if(c1[i] == c2[j]){
                i++;
                j++;
            }
            //j==0
            else if(next[j] == -1){
                i++;
            }else{
                j = next[j];
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
        //比较的位置是i， 1位置返回的是0下边的next
        next[1] = 0;
        int i = 2;
        // compare next 当前哪个下标在跟i-1比较
        int cn = 0;
        // 0  1 2 3 4
        // cn i
        while(i<c2.length){
            if(c2[i-1] == c2[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }
}
