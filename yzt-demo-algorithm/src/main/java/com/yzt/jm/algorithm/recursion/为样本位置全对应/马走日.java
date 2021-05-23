package com.yzt.jm.algorithm.recursion.为样本位置全对应;

/**
 * @Description: 【马走日】
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * @Author: min
 * @Date: 2021-04-26 18:48
 */
public class 马走日 {

    public static void main(String[] args) {
        System.out.println(process(5, 4 , 3));
    }

    public static int process(int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (k == 0) {
            return 0 == x && 0 == y ? 1 : 0;
        }
        int ways = 0;
        //8种情况
        ways += process(x + 2, y + 1, k - 1);
        ways += process(x + 1, y + 2, k - 1);
        ways += process(x - 1, y + 2, k - 1);
        ways += process(x - 2, y + 1, k - 1);
        ways += process(x - 2, y - 1, k - 1);
        ways += process(x - 1, y - 2, k - 1);
        ways += process(x + 1, y - 2, k - 1);
        ways += process(x + 2, y - 1, k - 1);

        return ways;

    }
}
