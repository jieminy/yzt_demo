package com.yzt.jm.algorithm.tree;

import java.util.List;

/**
 * @Description: 判断俩个节点公共的祖先节点
 * 对于节点X，树中所有节点分类情况：
 * 1.祖先节点
 * 2.X自己
 * 3.X的孩子节点
 * 4.X作为右子树姿态下的左兄
 * 5.X作为左子树姿态下的右兄
 *
 * 【题目】
 * 找出节点X，Y的共同祖先节点
 * 【结论】X的先序遍历 ∩ Y的后续遍历 = 头 左（X） 右 ∩ 左 右（X） 头  = 公共头
 * @Author: min
 * @Date: 2021-04-11 14:37
 */
public class Ancestor {
    public static void main(String[] args){

        BinaryTree binaryTree = new BinaryTree();
        Node head = binaryTree.initTree();

        System.out.println("前序");
//        binaryTree.preTraversal(head);
        System.out.println("");
        System.out.println("后序");
//        binaryTree.postTraversal(head);
        System.out.println("");
        Ancestor ancestor = new Ancestor();
        //7, 20的公共祖先

        Info info = ancestor.process(head, head.left.left.left, head.left.right.left);
        System.out.println("the lowest ancestor of " +head.left.left.left.data + "and" +  head.left.right.left.data + " is node: " + info.ans.data);

    }

    public String findSameAncestor(int a, int b, List<Integer> pre, List<Integer> post){
        if(a > b){
            return "null";
        }

        String ancestors = "";
        for(int i=0; i<a; i++){
            for(int j=b+1; j<=post.size()-1; j++){
                if(pre.get(i).equals(post.get(j))){
                    ancestors = ancestors.concat(pre.get(i) + ",");
                }
            }
        }

        return ancestors;
    }
    class Info{
        //找到A
        boolean findA;
        //找到B
        boolean findB;
        //公共祖先节点
        Node ans;
        Info(boolean findA, boolean findB, Node ans){
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }
    public Info process(Node cur, Node a, Node b){
        if(cur == null){
            return new Info(false, false, null);
        }
        //找左子树索要信息
        Info leftInfo = process(cur.left, a, b);
        //找右子树索要信息
        Info rightInfo = process(cur.right, a, b);
        //自己是a 左数发现a 右子树发现a
        boolean findA = cur == a || leftInfo.findA || rightInfo.findA;
        //自己是b 左子树发现b 右子树发现b
        boolean findB = cur == b || rightInfo.findB || leftInfo.findB;


        Node ans = null;
        //左子树右答案
        if(leftInfo.ans != null){
            ans = cur.left;
        }
        //右子树有答案
        else if(rightInfo.ans != null){
            ans = cur.right;
        }
        //当前节点findA 并且findB
        else {
            if(findA && findB){
                ans = cur;
            }
        }
        return new Info(findA, findB, ans);
    }

}

