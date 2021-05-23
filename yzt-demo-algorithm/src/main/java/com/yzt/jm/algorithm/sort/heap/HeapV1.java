package com.yzt.jm.algorithm.sort.heap;

/**
 * @Description: 堆
 * @Author: min
 * @Date: 2021-04-17 16:18
 */
public class HeapV1 {

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 7, 3, 5, 4, 1};
        heapSort(a);
//        print(a);
    }

    private static void print(int[] a) {
        for (int item : a) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    /**
     * 在堆尾插入新的数据
     * 从下至上堆化
     *
     * @param arr   堆
     * @param index 下标
     */
    public static void heapInsert(int[] arr, int index) {
        if (index > arr.length || index == 1) {
            return;
        }
        //父节点 index-1/2  左子节点 2*index+1 右2*index+2
        while (index > 0) {
            int pid = (index - 1) / 2;
            //大顶堆
            if (arr[index] > arr[pid]) {
                swap(arr, index, pid);
            }
            //小顶堆
//            if(arr[index] < arr[pid]){
//                swap(arr, index, pid);
//            }
            index = pid;
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
    public static void heapify(int[] a, int index, int heapSize) {
        if (index + 1 > heapSize) {
            return;
        }

        while (2 * index + 1 < heapSize) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int biggerIdx = right < heapSize - 1 ? (a[left] > a[right] ? left : right) : left;
            int bigger = a[biggerIdx];

            if (a[index] < bigger) {
                swap(a, index, biggerIdx);
            }
            index = biggerIdx;
        }

    }

    public static void heapSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }

        //1.先通过上浮的方式建立大顶堆
        for (int i = 0; i <= a.length - 1; i++) {
            heapInsert(a, i);
        }
        print(a);
        int heapSize = a.length;

        //依次将最大的数据移动至尾部，并重新通过下沉的方式建立规模--heapSize的大顶堆
        //说白了就是重复【1.堆化】【2.取最大值】
        while (heapSize > 0) {
            swap(a, 0, heapSize-1);
            heapify(a, 0, --heapSize);
        }

        System.out.println("");
        print(a);

    }
}
