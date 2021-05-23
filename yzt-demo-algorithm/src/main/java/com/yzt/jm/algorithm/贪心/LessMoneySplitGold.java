package com.yzt.jm.algorithm.贪心;

import java.util.PriorityQueue;

/**
 * @Description: 最少的钱去分割黄金
 * @Author: min
 * @Date: 2021-04-15 19:44
 */
public class LessMoneySplitGold {

    public static void main(String[] args){
        int[] arr = new int[]{1,1,2};
        LessMoneySplitGold lessMoneySplitGold = new LessMoneySplitGold();
        System.out.println(lessMoneySplitGold.process(arr));

    }
    private int process(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int item :
                arr) {
            queue.add(item);
        }

        int sum = 0;
        int cur;
        while(queue.size() > 1){
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }
}
