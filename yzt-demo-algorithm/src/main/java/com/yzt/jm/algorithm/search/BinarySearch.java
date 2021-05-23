package com.yzt.jm.algorithm.search;

/**
 * @Description: 二分查找
 * @Author: min
 * @Date: 2021-03-26 09:28
 */
public class BinarySearch {
    public static void main(String [] args ){
        Integer[] arr = new Integer[]{ 2, 3, 5, 6, 9, 15};
        System.out.println(search(arr, 9, 0, arr.length - 1));

        lefter(arr, 4, 0, arr.length-1, -1);

        righter(arr, 7, 0, arr.length-1, -1);

    }

    private static int search(Integer[] a, int search, int low, int high){
        //终止条件
        if(low > high){
            return -1;
        }
        //寻找中值 int mid = (high+low)/2;
        int mid = low + (high - low >> 1);
        //在mid左方
        if(search < a[mid]){
            return search(a, search, low, mid-1);
        }
        //在mid右方
        else if(search > a[mid]){
            return search(a, search, mid+1, high);
        }else{
            return mid;
        }
    }

    /**
     * 有序数组中，查找>=3最左端  [2,4,6,6,7]   6 > 3
     * mid > 3  左边
     * mid < 3  右边
     * mid = 3  左边
     */
    public static void lefter(Integer[] a, int search, int low, int high, int tmp){
        if(low >= high){
            System.out.println("最左端是" + tmp);
            return;
        }
        int mid = (high - low) >> 1 + low;
        if(a[mid]>=search){
            tmp = mid;
            lefter(a, search, low, mid-1, tmp);
        }else{
            lefter(a, search,mid+1, high, tmp);
        }
    }

    /**
     * 有序数组中，查找<=6最右端  [2,4,6,6,7]
     * mid > 6  左边
     * mid < 6  右边
     * mid = 3  右边
     */
    public static void righter(Integer[] arr, int search, int low, int high, int tmp){
        if(low >= high){
            System.out.println("<=最右端：" + tmp);
            return;
        }
        int mid = (high - low >> 1) + low;
        if(arr[mid] <= search){
            tmp = mid;
            righter(arr, search, mid + 1, high, tmp);
        }else{
            righter(arr, search, low, mid -1, tmp);
        }
    }

}
