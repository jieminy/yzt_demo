package com.yzt.jm.algorithm.贪心;

/**
 * @Description: 简单来说可以认为：
 * 有一个大文件，记录一段时间内百度所有的搜索记录，
 * 每行放一个搜索词，因为搜索量很大，文件非常大，
 * 搜索词数量也很多，
 * 内存放不下，求搜索次数最多的TopN个搜索词。
 * <p>
 * 输入：大文件
 * 输出：TopN搜索词列表
 * @Author: min 殷洁民
 */
public class Interview1 {


    public static void main(String[] args) {
        int m = 10;
        int n = 1000;
        int topN = 10;
        int[][] file = new int[m][n];
        int[][] tmpFile = new int[m][topN];
        for (int i = 0; i < m; i++) {
            //依次获取每个文件的top n
            tmpFile[i] = heapSort(file[i], topN);
        }
        //top数组
        int[] topArr = getTopN(tmpFile, topN);
    }

    /**
     * @param tmpFile 所有top n的文件集合
     * @param topN    top数值
     * @return top n
     */
    public static int[] getTopN(int[][] tmpFile, int topN) {
        //每一行当前最大的下标
        int[] idxs = new int[topN];
        //top结果数组
        int[] topArr = new int[topN];
        for (int j = 0; j < topN; j++) {
            int max = 0;
            for (int i = 0; i < tmpFile.length; i++) {
                //如果比max打，idxs对应行下标右移
                if (max < tmpFile[i][idxs[i]++]) {
                    max = tmpFile[i][idxs[i]];
                }
            }
            //第j大数
            topArr[j] = max;
        }
        return topArr;
    }

    /**
     * 排序获取top n
     *
     * @param a 目标数组
     * @param n top n
     * @return top前n个数
     */
    public static int[] heapSort(int[] a, int n) {
        if (a == null || a.length < 2) {
            return new int[]{};
        }

        //从上至下建堆
        for (int i = 0; i <= a.length - 1; i++) {
            heapInsert(a, i);
        }

        int tmp = a.length - 1;
        int[] copy = new int[n];
        int idx = 0;
        while (tmp > tmp - n) {
            copy[idx++] = a[0];
            //堆顶依次在末尾排序
            swap(a, 0, tmp);
            //向下堆化
            heapify(a, 0, tmp--);
        }
        return copy;
    }

    /**
     * 在堆尾插入新的数据
     * 大根堆堆化
     *
     * @param arr   堆
     * @param index 下标
     */
    public static void heapInsert(int[] arr, int index) {
        if (index == 0) {
            return;
        }
        //父节点 index-1/2  左子节点 2*index+1 右2*index+2
        while (arr[(index - 1) / 2] < arr[index]) {
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
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
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && a[left + 1] > a[left] ? left + 1 : left;
            int maxIndex = a[index] > a[largest] ? index : largest;
            if (maxIndex == index) {
                break;
            }
            swap(a, index, maxIndex);
            index = maxIndex;
            left = 2 * maxIndex + 1;
        }
    }


}
