package com.yzt.jm.algorithm.sort.count;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-02 23:17
 */
public class 基数排序 {

    public static void main(String[] args) {
        int[] a = new int[]{101, 201, 301, 222, 543, 312, 321};
        radixSort(a, 0, a.length - 1, getMaxDigit(a));
        Printer.printArr(a);
    }

    /**
     * 基数排序
     *
     * @param a     目标数组
     * @param L     左下标
     * @param R     右下标
     * @param digit 最大位数
     */
    public static void radixSort(int[] a, int L, int R, int digit) {
        //入桶
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[10];
            for (int i = 0; i <= R; i++) {
                int digitItem = getDigitItem(a[i], d);
                count[digitItem]++;
            }

            for (int j = 0; j < 9; j++) {
                count[j + 1] += count[j];
            }

            //构建辅助数组 help
            int[] help = new int[R - L + 1];
            for (int i = R; i >= L; i--) {
                int digitItem = getDigitItem(a[i], d);
                //出桶
                help[--count[digitItem]] = a[i];
            }

            for (int i = L; i <= R; i++) {
                a[i] = help[i];
            }
        }


    }

    public static int getDigitItem(int item, int digit) {
        return (item / (int) Math.pow(10, digit - 1)) % 10;
    }

    /**
     * 获取最大位数
     *
     * @param a 目标数组
     * @return 最大位数
     */
    public static int getMaxDigit(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

}
