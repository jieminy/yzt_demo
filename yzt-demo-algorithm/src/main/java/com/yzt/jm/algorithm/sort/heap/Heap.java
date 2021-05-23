package com.yzt.jm.algorithm.sort.heap;

/**
 * @Description: 堆
 * @Author: min
 * @Date: 2021-04-17 16:18
 */
public class Heap {

    public static void main(String [] args){
        int [] a = new int[]{2,5,7,3,5,4,1};
        heapSort(a);
        for (int item : a){
            System.out.print(item);
            System.out.print(",");
        }
    }

    /**
     * 在堆尾插入新的数据
     * 大根堆（小）堆化
     * @param arr 堆
     * @param index 下标
     */
    public static void heapInsert(int[] arr, int index){
        if(index == 0){
            return;
        }
        //父节点 index-1/2  左子节点 2*index+1 右2*index+2
        while(arr[(index-1) / 2] < arr[index]){
            swap(arr, index, (index-1) >> 1);
            index = (index-1) >> 1;
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * index位置下沉
     * 跟较大的孩子交换位置
     */
    public static void heapify(int[] a, int index, int heapSize){
        int left = 2 * index + 1;
        while(left<heapSize){
            int largest = left + 1 < heapSize && a[left+1] > a[left] ? left + 1 : left;
            int maxIndex = a[index] > a[largest] ? index : largest;

            if(maxIndex == index){
                break;
            }

            swap(a, index, maxIndex);
            index = maxIndex;
            left = 2 * maxIndex + 1;
        }
    }

    public static void heapSort(int[] a){
        if(a == null || a.length<2){
            return;
        }

        //从上至下建堆
        for(int i=0; i<=a.length-1; i++){
            heapInsert(a, i);
        }

        int tmp = a.length-1;

        while(tmp>0){
            swap(a, 0, tmp);
            heapify(a, 0, tmp--);
        }

    }
}
