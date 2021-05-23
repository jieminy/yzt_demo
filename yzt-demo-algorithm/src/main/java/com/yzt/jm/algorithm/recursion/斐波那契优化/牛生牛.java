package com.yzt.jm.algorithm.recursion.斐波那契优化;

/**
 * @Description:
 * 第一年农场有1只成熟的母牛A，往后的每年：
 * 1）每一只成熟的母牛都会生一只母牛
 * 2）每一只新出生的母牛都在出生的第三年成熟
 * 3）每一只母牛永远不会死
 * 返回N年后牛的数量
 * @Author: min
 * @Date: 2021-04-27 20:57
 */
public class 牛生牛 {

    public static void main(String[] args){
        System.out.println(process(10));
    }

    /**
     * 1 2 3 4 5 6 7 8 9
     * 1 2 3 4 6
     * 1 1 1 2 3
     */
    public static int process(int n){
        if(n ==1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(n == 3){
            return 4;
        }

        return process(n-1) + process(n-3);
    }
}
