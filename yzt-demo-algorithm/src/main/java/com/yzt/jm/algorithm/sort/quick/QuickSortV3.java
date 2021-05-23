package com.yzt.jm.algorithm.sort.quick;

import java.util.Random;
import java.util.Stack;

/**
 * @description: 快排
 * @author: jiemin
 * @date: 2021-03-26 09:27
 */
public class QuickSortV3 {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{2, 5, 7, 3, 5, 4, 1};
        quickSort(a, a.length);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    public static void quickSort(Integer[] a, int n) {
        quickSortRecursion(a, 0, n - 1);
    }

    public static class PV {
        int low;
        int high;
        PV(int low, int high){
            this.low = low;
            this.high = high;
        }
    }
    private static void quickSortRecursion(Integer[] a, int low, int high) {
        Stack<PV> stack = new Stack<>();
        int[] partition = partition(a, low, high);
        stack.push(new PV(low, partition[0]-1));
        stack.push(new PV(partition[1] + 1, high));

        while(!stack.isEmpty()){
            PV pv = stack.pop();
            if(pv.low < pv.high){
                int[] pt = partition(a, pv.low, pv.high);
                stack.push(new PV(low, pt[0]-1));
                stack.push(new PV(pt[1] + 1, high));
            }
        }

    }

    private static int[] partition(Integer[] a, int low, int high) {
        if(low == high){
            return new int[]{low, high};
        }
        if(low > high){
            return new int[]{-1, -1};
        }
        //选择随机下标并替换
        int randomIdx = new Random().nextInt(high - low + 1) + low;
        swap(a, randomIdx, high);

        int pivot = a[high];
        //小于区
        int less = low - 1;
        //大于区
        int more = high;
        //遍历index
        int index = low;

        while (index < more) {
            if (a[index] == pivot) {
                index++;
            } else if (a[index] < pivot) {
                swap(a, index++, ++less);
            } else {
                //右边还有没有看过呢，这就是为啥index不能++
                swap(a, index, --more);
            }
        }
        //
        swap(a, more, high);
        return new int[]{less + 1, more};
    }

    public static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
