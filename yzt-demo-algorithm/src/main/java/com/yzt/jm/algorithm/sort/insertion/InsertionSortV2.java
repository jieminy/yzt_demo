package com.yzt.jm.algorithm.sort.insertion;

/**
 * @Description: 插入排序
 * @Author: min
 * @Date: 2021-03-28 22:28
 */
public class InsertionSortV2 {
    public static void main(String[] args) {
        InsertionSortV2 insertionSort = new InsertionSortV2();
        int[] a = new int[]{2, 5, 7, 3, 5, 4, 1};
        insertionSort.sort(a, a.length);
        for (int item :
                a) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    public void sort(int[] a, int n) {
        //i左边是排好序的a 右边依次在a中找到位置并插入
        for(int i=1; i<n; i++){
            int tmp = a[i];
            int j = i-1;
            while(j>=0){
                if(tmp < a[j]){
                    a[j+1] = a[j--];
                }else{
                    break;
                }
            }
            //最后肯定会多减掉1  while循环决定的
            a[j+1] = tmp;
        }

    }
}
