package com.yzt.jm.algorithm.sort.merge;

/**
 * @Description: 逆序度
 * 排序，并求出所有【左边数比右边数大】的【个数】
 * 例如[2,5,7,3,5,4,1,2]  1 * 4 + 3 * 2 + 7 * 1 + 4 * 1 = 21
 * [2][3][5][7] | [1][2][4][5]
 * X在左边， 求X大于右边的个数, 需要从后往前滑动（前边都是小的）
 * X在左边， 求X小于右边的个数，需要从前往后滑动（后边都是大的）
 * @Author: min
 * @Date: 2021-04-17 09:36
 */
public class MergeSort逆序V1 {
    public static void main(String[] args) {
        // 1 + 4 + 5 + 2 + 3 + 2 = 17
        int[] a = new int[]{2, 5, 7, 3, 5, 4, 1, 2};
        int sum = partitionAndMerge(a, 0, a.length - 1);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }

        System.out.println("逆序度为：" + sum);
    }

    /**
     * 递归分区并且合并
     *
     * @param arr  待排序数组
     * @param low  地位
     * @param high 高位
     */
    public static int partitionAndMerge(int[] arr, int low, int high) {
        //结果集
        int sum = 0;

        //终止条件
        if (low >= high) {
            return sum;
        }

        //找中间节点
        int mid = (high + low) >> 1;
        sum += partitionAndMerge(arr, low, mid);
        sum += partitionAndMerge(arr, mid + 1, high);
        sum += merge(arr, low, mid, high);

        return sum;
    }

    public static int merge(int[] a, int low, int mid, int high) {
        //k的下标  high -low  长度 high - low +1
        int i = mid, j = high, k = high - low;
        int[] tmp = new int[high - low + 1];
        int sum = 0;
        while (i >= low && j >= mid + 1) {
            sum += a[i] > a[j] ? j - mid : 0;
            tmp[k--] = a[i] > a[j] ? a[i--] : a[j--];
        }
        while (i >= low) {
            tmp[k--] = a[i--];
        }
        while (j >= mid + 1) {
            tmp[k--] = a[j--];
        }

        for (i = 0; i <= tmp.length - 1; i++) {
            a[low + i] = tmp[i];
        }
        return sum;
    }
}
