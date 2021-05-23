package com.yzt.jm.algorithm.sort.quick;

import java.util.Random;

/**
 * @description: 快排
 * @author: jiemin
 * @date: 2021-03-26 09:27
 */
public class QuickSortV2 {
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

        while (index < high) {
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
