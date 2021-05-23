package com.yzt.jm.algorithm.sort.quick;

/**
 * @description: 快排
 * @author: jiemin
 * @date: 2021-03-26 09:27
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{2, 5, 7, 3, 5, 4, 1};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(a, a.length);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    public void quickSort(Integer[] a, int n) {
        quickSortRecursion(a, 0, n - 1);
    }

    private void quickSortRecursion(Integer[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(a, low, high);
        quickSortRecursion(a, low, p - 1);
        quickSortRecursion(a, p + 1, high);
    }

    private int partition(Integer[] a, int low, int high) {
        int pivot = a[high];
        //i作为分界点，左边是小于pivot的，右边是待处理的
        int i = low, j = low;
        //从low到high依次处理
        for (; j <= high - 1; j++) {
            //小于pivot则a[i] a[j]交换,分界点i右移一位
            if (pivot > a[j]) {
                swap(a, i, j);
                i++;
            }
        }
        //最后需要交换分界点i和pivot
        swap(a, i, high);
        return i;
    }

    private void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
