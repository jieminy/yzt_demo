package com.yzt.jm.algorithm.sort.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 加强堆
 * @Author: min
 * @Date: 2021-04-20 19:00
 */
public class HeapGreater<T> {
    private ArrayList<T> nodes;
    private int heapSize;
    private Map<T, Integer> idxMap;
    private Comparator<T> comparator;

    public static void main(String [] args){
        int [] a = new int[]{2,5,7,3,8,4,1};
        HeapGreater<Integer> heapV1 = new HeapGreater<>((o1, o2) -> o1-o2);
        for (int item:
                a){
            heapV1.push(item);
        }

        for (int item:
                a){
            System.out.print("堆排序结果：" + heapV1.pop());
        }
    }

    public HeapGreater(Comparator<T> c) {
        this.nodes = new ArrayList<>();
        heapSize = 0;
        idxMap = new HashMap<>();
        comparator = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return idxMap.containsKey(obj);
    }

    public T peek() {
        return nodes.get(0);
    }

    public void push(T obj) {
        nodes.add(obj);
        idxMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T data = nodes.get(0);
        swap(0, --heapSize);
        heapify(0);
        idxMap.remove(data);
        nodes.remove(data);
        return data;
    }

    public void remove(T obj) {
        if(!idxMap.containsKey(obj)){
            return;
        }
        int idx = idxMap.get(obj);
        swap(idx, heapSize-1);
        heapify(idx);
        nodes.remove(obj);
        idxMap.remove(obj);
    }

    public void resign(T obj) {
        int idx = idxMap.get(obj);
        heapInsert(idx);
        heapify(idx);
    }



    private void heapInsert(int index) {
        //跟父比较是否交换
        while(comparator.compare(nodes.get(index), nodes.get((index-1)/2)) > 0){
            swap(index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    private void heapify(int index) {
        //左子
        int left = 2*index + 1;

        //跟最大（小）子交换
        while(left < heapSize){
            T largestNode = left + 1 < heapSize && comparator.compare(nodes.get(left), nodes.get(left+1)) < 0 ? nodes.get(left + 1) : nodes.get(left);
            //子节点下标
            int largestIndex = largestNode == nodes.get(left) ? left : left + 1;

            if(largestIndex == index){
                break;
            }

            swap(largestIndex, (largestIndex-1)/2);

            left = 2*left + 1;
        }
    }

    private void swap(int i, int j) {
        T ti = nodes.get(i);
        T tj = nodes.get(j);

        nodes.set(i, tj);
        nodes.set(j, ti);

        idxMap.put(ti, j);
        idxMap.put(tj, i);
    }
}
