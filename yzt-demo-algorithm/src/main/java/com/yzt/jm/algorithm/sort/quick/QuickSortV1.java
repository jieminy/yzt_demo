package com.yzt.jm.algorithm.sort.quick;

import java.util.Random;

/**
 * @description: 快排
 * @author: jiemin
 * @date: 2021-03-26 09:27
 */
public class QuickSortV1 {
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

    private static void quickSortRecursion(Integer[] a, int low, int high) {
        //终止条件
        if (low >= high) {
            return;
        }
        //先左右分区并返回分区点
        int[] pivot = partition(a, low, high);
        //左递归
        quickSortRecursion(a, low, pivot[0] - 1);
        //右递归
        quickSortRecursion(a, pivot[1] + 1, high);
    }

    private static int[] partition(Integer[] a, int low, int high) {
        int pivot = a[high];
         //123 [] 78
        int less = low - 1;
        int more = high;
        int index = low;
        while(index < high){
            if(a[index] < pivot){
                swap(a, ++less, index++);
            }else if(a[index] == pivot){
                index ++;
            }else{
                swap(a, index++, --more);
            }
        }
        swap(a, more, high);
        return new int[]{less+1, more};
    }

    public static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
