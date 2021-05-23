package com.yzt.jm.algorithm.recursion.leftright;

/**
 * @Description: 【数字分裂】
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 * @Author: min
 * @Date: 2021-04-24 21:28
 */
public class 数字分裂V1 {

    public static void main(String[] args) {
        System.out.println(process(1, 4));
    }

    public static int process(int pre, int rest) {
        if(rest == 0){
            return 1;
        }
        if(pre > rest){
            return 0;
        }
        int ways = 0;
        for(int index=pre; index<=rest; index++){
            ways += process(pre, rest-pre);
        }
        return ways;
    }
}
