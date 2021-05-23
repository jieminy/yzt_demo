package com.yzt.jm.algorithm.tree;

import lombok.Data;

/**
 * @Description: 二叉树
 * 【题目】
 * 1.用递归的方式实现先序、后序、中序的遍历方式
 * 2.去递归化先序、后序、中序
 * @Author: min
 * @Date: 2021-04-10 18:47
 */
public class Node {
    Node(int data){
        this.data = data;
    }
    public Node left;
    public Node right;
    public int data;

    public boolean hasLeft(){
        return left != null;
    }

    public boolean hasRight(){
        return right != null;
    }

    @Override
    public String toString() {
        return data+"";
    }
}
