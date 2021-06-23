package com.yzt.jm.algorithm.sort.quick;

/**
 * @description: 快排
 * @author: jiemin
 * @date: 2021-03-26 09:27
 */
public class QuickSortV5 {
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

        PV(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    private static void quickSortRecursion(Integer[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int pidx = partition(a, low, high);
        quickSortRecursion(a, low, pidx - 1);
        quickSortRecursion(a, pidx + 1, high);
    }

    private static int partition(Integer[] a, int low, int high) {
        int pivot = a[high];
        //i是排序节点右边界
        int i = low;
        int less = low;
        while (i < high) {
            //当前遍历节点小于比照值，需要跟less区下一个节点替换
            if (pivot > a[i]) {
                swap(a, i, less++);
            }//遍历节点大于对照值，不用管，i++
            i++;
        }
        swap(a, high, less);
        return less;
    }

    public static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
