package com.yzt.jm.algorithm.recursion;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-21 08:46
 */
public class 斐波那契数列 {

    public static void main(String[] args){
        System.out.println(fn(10));
    }

    public static int fn(int n){
        if(n==1){
            return 1;
        }
        if(n==0){
            return 0;
        }
        return fn(n-1) + fn(n-2);
    }
}
