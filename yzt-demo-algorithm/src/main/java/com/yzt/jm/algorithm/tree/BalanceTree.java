package com.yzt.jm.algorithm.tree;

/**
 * @Description: 寻找最大平衡二叉子树，并且返回
 * @Author: min
 * @Date: 2021-04-13 22:38
 */
public class BalanceTree {


    class Info {
        //最大平衡子树节点数
        private int maxBSTSubSize;
        //自己的大小
        private int allSize;
        //叶子节点最小
        private int min;
        //叶子节点最大
        private int max;
        public Info(int maxBSTSubSize, int allSize, int min, int max){
            this.maxBSTSubSize = maxBSTSubSize;
            this.allSize = allSize;
            this.min = min;
            this.max = max;
        }
    }

    public Info process(Node cur){

        Info leftInfo = process(cur.left);
        Info rightInfo = process(cur.right);
        //
        int max = cur.data;
        int min = cur.data;
        int allSize = 1;
        if(leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            allSize = rightInfo.allSize + 1;
        }
        if(rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allSize = rightInfo.allSize + 1;
        }

        int maxBstSubSize;
        int p1 = -1;
        if(leftInfo != null){
            p1 = leftInfo.maxBSTSubSize;
        }
        int p2 = -1;
        if(rightInfo != null){
            p2 = rightInfo.maxBSTSubSize;
        }
        int p3 = -1;
        boolean leftBst = leftInfo == null || leftInfo.maxBSTSubSize == leftInfo.allSize;
        boolean rightBst = rightInfo == null || rightInfo.maxBSTSubSize == rightInfo.allSize;
        if(leftBst && rightBst){
            boolean leftMaxLessX = leftInfo == null || leftInfo.max < cur.data;
            boolean rightMinBiggerX = rightInfo == null || rightInfo.min > cur.data;
            if(leftMaxLessX && rightMinBiggerX){
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        maxBstSubSize = Math.max(p1, Math.max(p2, p3));
        return new Info(maxBstSubSize, allSize, min, max);


    }

}
