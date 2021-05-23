package com.yzt.jm.algorithm.sort.heap;

/**
 * @Description: 堆
 * @Author: min
 * @Date: 2021-04-17 16:18
 */
public class MaxHeapV1 {

    public int[] a;
    public int heapSize;
    public int limit;

    public MaxHeapV1(int limit){
        this.limit = limit;
        a = new int[limit];
        heapSize = 0;
    }

    public static void main(String [] args){
        int [] a = new int[]{2,5,7,3,5,4,1};
        MaxHeapV1 heapV1 = new MaxHeapV1(7);
        for (int item:
                a){
            heapV1.push(item);
        }

        for (int item:
                a){
            System.out.print("堆排序结果：" + heapV1.pop());
        }
    }

    /**
     * 在堆尾插入新的数据
     * 大根堆化
     * 父亲节点 i-1/2
     * 左子节点 2*i + 1  右子节点 2*i +2
     * @param heap 堆
     * @param index 下标
     */
    public void heapInsert(int[] heap, int index){
        if(index < 1){
            return;
        }
        while(heap[index] > heap[(index-1)/2]){
            swap(heap, index,  (index-1)/2);
            index = (index-1)/2;
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
    private void heapify(int index, int heapSize){
        //
        int left = 2*index + 1;
        while(left < heapSize){
            int largest = left + 1 < heapSize ? Math.max(a[left], a[left+1]) : a[left];
            int largestIndex = largest == a[left] ? left : left+1;
            if(index == largestIndex){
                break;
            }
            swap(a, (left-1)/2, largestIndex);
            left = 2 * left + 1;
        }
    }

    public int pop(){
        if(heapSize < 1){
            return -1;
        }
        int data = this.a[0];
        swap(a,0,--heapSize);
        heapify(0, heapSize);
        return data;
    }

    public void push(int data){
        if(heapSize < limit){
            a[heapSize] = data;
            heapInsert(a, heapSize++);
        }
    }

}
