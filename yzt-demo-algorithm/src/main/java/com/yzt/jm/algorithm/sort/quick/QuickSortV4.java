package com.yzt.jm.algorithm.sort.quick;

import java.util.Random;
import java.util.Stack;

/**
 * @description: 快排
 * @author: jiemin
 * @date: 2021-03-26 09:27
 */
public class QuickSortV4 {
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
        if(low >=high){
            return;
        }
        int p = partition(a, low, high);
        quickSortRecursion(a, low, p-1);
        quickSortRecursion(a, p+1, high);
    }

    private static int partition(Integer[] a, int low, int high) {
        int pivot= a[high];
        int less = low;
        int j = low;
        while(j < high){
            if(a[j]<pivot){
                swap(a, less++, j);
            }
            j++;
        }
        swap(a, less, high);
        return less;
    }

    public static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
