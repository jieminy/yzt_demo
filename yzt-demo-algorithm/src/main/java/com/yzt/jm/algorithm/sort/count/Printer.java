package com.yzt.jm.algorithm.sort.count;

import java.util.Stack;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-02 23:15
 */
public class Printer {

    public static void printArr(int[] arr){
        System.out.println("Wow ! Wonderful ");
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    public static void printStack(Stack stack){
        while(!stack.isEmpty()){
            Object pop = stack.pop();
            System.out.print(pop);
        }
    }
}
