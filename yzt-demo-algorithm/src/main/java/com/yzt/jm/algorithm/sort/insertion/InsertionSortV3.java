package com.yzt.jm.algorithm.sort.insertion;

/**
 * @Description: 插入排序
 * @Author: min
 * @Date: 2021-03-28 22:28
 */
public class InsertionSortV3 {
    public static void main(String[] args) {
        InsertionSortV3 insertionSort = new InsertionSortV3();
        int[] a = new int[]{2, 5, 7, 3, 5, 4, 1};
        insertionSort.sort(a, a.length);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    public void sort(int[] a, int n) {
        //依次比较 小于则交换
        //排序边界 默认位置0
        for (int i = 0; i < a.length - 1; i++) {
            //待排序下标
            int j = i+1;
            int tmp = a[j];
            while(j > 0 && tmp < a[j-1]){
                a[j] = a[--j];
            }
            a[j] = tmp;
        }

    }
}
