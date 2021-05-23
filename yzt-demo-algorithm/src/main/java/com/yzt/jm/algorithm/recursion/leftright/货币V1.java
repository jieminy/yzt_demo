package com.yzt.jm.algorithm.recursion.leftright;

/**
 * @Description: 【货币】
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * @Author: min
 * @Date: 2021-04-24 12:28
 */
public class 货币V1 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 1};

        System.out.print(process(a, 0, 4));
    }

    /**
     * @param a     货币集合（每个货币认为都不同）
     * @param index 当前下标
     * @param rest  目标总额
     * @return 方法数
     */
    public static int process(int[] a, int index, int rest) {
        if(rest < 0){
            return 0;
        }
        if (index == a.length) {
            return rest == 0 ? 1 : 0;
        }
        //index位置要或者不要的所有尝试之和
        int ways=0;
        for(int zhang=0; zhang<=1; zhang++){
            ways+=process(a, index + 1, rest - zhang * a[index]);
        }
        return ways;
    }


}
