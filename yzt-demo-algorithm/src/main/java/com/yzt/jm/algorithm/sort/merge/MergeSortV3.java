package com.yzt.jm.algorithm.sort.merge;

/**
 * @Description: 归并排序
 * @Author: min
 * @Date: 2021-03-27 20:42
 */
public class MergeSortV3 {
    public static void main(String [] args){
        MergeSortV3 mergeSort = new MergeSortV3();
        int [] a = new int[]{2,5,7,3,5,4,1};
        mergeSort.mergeSort(a,  a.length);
        for (int item:
                a){
            System.out.print(item);
            System.out.print(",");
        }
    }

    public void mergeSort(int [] a, int n){
        process(a, 0, n-1);
    }

    private void process(int[] a, int low, int high) {
        //终止
        if(low >= high){
            return;
        }
        //中间节点
        int mid = (low + high) >> 1;
        //左递归
        process(a, low, mid);
        //右递归
        process(a, mid+1 , high);
        //merge
        merge(a, low, mid, high);
    }

    private void merge(int[] a, int low, int mid, int high) {

        int i=low, j=mid+1, k=0;
        int[] tmp = new int[high - low + 1];
        while(i<=mid && j<=high){
            if(a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }

        while(i<=mid){
            tmp[k++] = a[i++];
        }

        while(j<=high){
            tmp[k++] = a[j++];
        }

        for(i = 0; i<tmp.length; i++){
            a[low + i] = tmp[i];
        }

    }


}
