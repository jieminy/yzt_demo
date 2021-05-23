package com.yzt.jm.algorithm.sort.heap;

import java.util.*;

/**
 * @Description: 加强堆
 * @Author: min
 * @Date: 2021-04-20 19:00
 */
public class HeapGreaterV1<T> {
    private List<T> nodes;
    private Map<T, Integer> idxMap;
    private int size;
    private Comparator<T> comparator;

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 7, 3, 8, 4, 1};
        HeapGreaterV1<Integer> heapV1 = new HeapGreaterV1<>((o1, o2) -> o1 - o2);
        for (int item :
                a) {
            heapV1.push(item);
        }

        for (int item :
                a) {
            System.out.print("堆排序结果：" + heapV1.pop());
        }
    }

    public HeapGreaterV1(Comparator<T> c) {
        this.nodes = new ArrayList<>();
        this.idxMap = new HashMap<>();
        this.size = 0;
        this.comparator = c;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    public boolean contains(T obj) {
        return idxMap.containsKey(obj);
    }

    public T peek() {
        return nodes.get(0);
    }

    public void push(T obj) {
        nodes.add(obj);
        idxMap.put(obj, size);
        heapInsert(size++);
    }

    public T pop() {
        T node = nodes.get(0);
        swap(0, size-1);
        idxMap.remove(node);
        nodes.remove(--size);
        heapify(0);
        return node;
    }

    public void remove(T obj) {
        if(!idxMap.containsKey(obj)){
            return;
        }
        int index = idxMap.get(obj);
        swap(index, size-1);
        heapify(index);
        nodes.remove(--size);
        idxMap.remove(obj);
    }

    public void resign(T obj) {

    }


    private void heapInsert(int index) {
        //跟父比较是否交换
        while (comparator.compare(nodes.get(index), nodes.get((index - 1) / 2)) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        //左子
        int left = index * 2 + 1;
        //跟最大（小）子交换
        while (left < size) {
            int biggerIdx = (left + 1) < size ? (comparator.compare(nodes.get(left), nodes.get(left + 1)) > 0 ? left : left + 1) : left;

            if(comparator.compare(nodes.get(index), nodes.get(biggerIdx)) > 0){
                break;
            }

            swap(biggerIdx, index);
            left = biggerIdx;
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
