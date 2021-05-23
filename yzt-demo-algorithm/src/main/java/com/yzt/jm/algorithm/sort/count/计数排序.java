package com.yzt.jm.algorithm.sort.count;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-05-02 23:02
 */
public class 计数排序 {

    public static void main(String[] args){
        int[] arr = new int[]{1,3,5,6,7,8,9,3,6,8,2,8};
        countSort(arr);
        Printer.printArr(arr);
    }

    // only for 0~200 value
    public static void countSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for(int i=0; i< arr.length; i++){
            bucket[arr[i]]++;
        }
        int i=0;
        for(int j=0; j<bucket.length; j++){
            while(bucket[j]-- > 0){
                arr[i++] = j;
            }
        }
    }
}
