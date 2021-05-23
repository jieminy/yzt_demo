package com.yzt.jm.algorithm.recursion;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-20 23:14
 */
public class 汉诺塔 {

    public static void main(String[] args){
        hanoi(3);
    }

    public static void hanoi(int n){
        move(n, "left", "right", "mid");
    }

    public static void move(int n, String from, String to, String other){
        if(n==1){
            System.out.println("move 1 from " + from + " to " + to);
        }else{
            move(n-1, from, other, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            move(n-1, other, to, from);
        }
    }
}
