package com.yzt.jm.algorithm.sort.merge;

/**
 * @Description: 小和问题
 * 排序，并求出所有【左边数比右边数小】的数的【总和】
 * 例如[1,3,7,4,8]  1 * 4 + 3 * 2 + 7 * 1 + 4 * 1 = 21
 * [1][3] | [7][4]| [8]
 * @Author: min
 * @Date: 2021-04-17 09:36
 */
public class MergeSort小和V1 {
    public static void main(String[] args) {
        // 2 * 5 + 5 + 3 * 2 = 21
        int[] a = new int[]{2, 5, 7, 3, 5, 4, 1};
        int sum = partitionAndMerge(a, 0, a.length - 1);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }

        System.out.println("最小和为：" + sum);


    }

    /**
     * 递归分区并且合并
     *
     * @param arr  待排序数组
     * @param low  地位
     * @param high 高位
     */
    public static int partitionAndMerge(int[] arr, int low, int high) {
        int sum = 0;
        if (low >= high) {
            return sum;
        }

        int mid = (low + high) >> 1;


        sum += partitionAndMerge(arr, low, mid);
        sum += partitionAndMerge(arr, mid + 1, high);
        sum += merge(arr, low, mid, high);
        return sum;
    }

    public static int merge(int[] a, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[] tmp = new int[high - low + 1];
        int sum = 0;
        while (i <= mid && j <= high) {
            sum += a[i] < a[j] ? a[i] * (high - j + 1) : 0;
            tmp[k++] = a[i] < a[j] ? a[i++] : a[j++];
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
        return sum;
    }
}
