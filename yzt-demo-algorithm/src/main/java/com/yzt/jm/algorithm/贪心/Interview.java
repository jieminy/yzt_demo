package com.yzt.jm.algorithm.贪心;


/**
 * @Description: 从(0, 0)到达(x, y)的路径数之和
 * @Author: 殷洁民
 */
public class Interview {

    public static void main(String[] args) {
        int m = 3;
        int n = 4;
        //方法一：
        int sum = process(0, 0, m, n);
        System.out.println(sum);
        //方法二：
        sum = dp(m, n);
        System.out.println(sum);
    }

    /**
     * 方法一：递归思路
     *
     * @param x x轴当前位置
     * @param y y轴当前位置
     * @param m 目标x
     * @param n 目标y
     * @return 总路径数
     */
    public static int process(int x, int y, int m, int n) {
        if (x == m && y == n) {
            return 1;
        }
        int sum = 0;
        if (x + 1 <= m) {
            sum += process(x + 1, y, m, n);
        }
        if (y + 1 <= n) {
            sum += process(x, y + 1, m, n);
        }
        return sum;
    }

    /**
     * 方法二：dp思路
     *
     * @param m 目标x
     * @param n 目标y
     * @return 总路径数
     */
    public static int dp(int m, int n) {
        //假设支持8以内的dp
        int[][] dpArr = getDpArr(m + 1, n + 1);
        return dpArr[m][n];
    }

    public static int[][] getDpArr(int x, int y) {
        int[][] arr = new int[x][y];
        //初始化0行
        for (int i = 0; i < x; i++) {
            arr[i][0] = 1;
        }
        //初始化0列
        for (int i = 0; i < y; i++) {
            arr[0][i] = 1;
        }
        //递推x,y
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr;
    }


}
