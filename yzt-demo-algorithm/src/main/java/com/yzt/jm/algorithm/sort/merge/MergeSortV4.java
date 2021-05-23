package com.yzt.jm.algorithm.sort.merge;

/**
 * @Description: 归并排序
 * @Author: min
 * @Date: 2021-03-27 20:42
 */
public class MergeSortV4 {
    public static void main(String[] args) {
        MergeSortV4 mergeSort = new MergeSortV4();
        int[] a = new int[]{2, 5, 7, 3, 5, 4, 1};
        mergeSort.mergeSort(a, a.length);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    public void mergeSort(int[] a, int n) {
        process(a, 0, n - 1);
    }

    private void process(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >> 1;
        process(a, low, mid);
        process(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    private void merge(int[] a, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[] tmp = new int[high - low + 1];
        while (i <= mid && j <= high) {
            tmp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        }

        while (i <= mid) {
            tmp[k++] = a[i++];
        }

        while (j <= high) {
            tmp[k++] = a[j++];
        }

        for (i = 0; i < tmp.length; i++) {
            a[low + i] = tmp[i];
        }
    }


}
