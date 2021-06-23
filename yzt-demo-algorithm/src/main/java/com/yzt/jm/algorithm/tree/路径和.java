package com.yzt.jm.algorithm.tree;


import java.util.Stack;

public class 路径和 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;
        路径和 路径和 = new 路径和();
        System.out.print(路径和.sumNumbers(treeNode));
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private int sum = 0;

    /**
     * @param root TreeNode类
     * @return int整型
     */
    public int sumNumbers(TreeNode root) {
        // write code here
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        process(root, stack);
        return sum;
    }

    public void process(TreeNode head, Stack<TreeNode> stack) {
        if (head == null) {
            return;
        }
        String path = "";
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            path = path.concat(String.valueOf(cur.val));
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left == null && cur.right == null) {
                sum += Integer.parseInt(path);
                path = path.substring(0, path.length() - 1);
            }
        }
    }
}
