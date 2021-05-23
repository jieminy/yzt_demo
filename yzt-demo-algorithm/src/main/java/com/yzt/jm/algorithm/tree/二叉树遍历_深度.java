package com.yzt.jm.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class 二叉树遍历_深度 {

    List<Integer> preArr = new ArrayList<>();
    List<Integer> postArr = new ArrayList<>();

    public 二叉树遍历_深度(){
        initTree();
    }

    public static void main(String[] args){

        二叉树遍历_深度 binaryTree = new 二叉树遍历_深度();
        Node head = binaryTree.initTree();
        System.out.println("前序-中左右");
        binaryTree.preTraversal(head);
        System.out.println("");
        binaryTree.preTraversalWithStack(head);
        System.out.println("中序-左中右");
        binaryTree.midTraversal(head);
        System.out.println("");
        binaryTree.midTraversalWithStack(head);
        System.out.println("后序-左右中（中右左倒叙）");
        binaryTree.postTraversal(head);
        System.out.println("");
        binaryTree.postTraversalWithStack(head);

    }

    public Node initTree(){
        Node head = new Node(10);
        head.left = new Node(8);
        head.right = new Node(20);
        head.left.left = new Node(6);
        head.left.left.left = new Node(4);
        head.left.left.right = new Node(7);

        head.left.right = new Node(9);
        head.left.right.left = new Node(12);
        return head;
    }
    /**
     *  前序遍历
     */
    public void preTraversal(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.data + ",");
        preArr.add(head.data);
        preTraversal(head.left);
        preTraversal(head.right);
    }
    public void preTraversalWithStack(Node head){
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.print(cur.data + ",");
            if(cur.hasRight()){
                stack.push(cur.right);
            }
            if(cur.hasLeft()){
                stack.push(cur.left);
            }
        }
    }


    /**
     * 后续遍历
     */
    public void postTraversal(Node head){
        if(head == null){
            return;
        }
        postTraversal(head.left);
        postTraversal(head.right);
        System.out.print(head.data + ",");
        postArr.add(head.data);
    }

    /**
     * 左右中
     * @param head
     */
    public void postTraversalWithStack(Node head){
        //中右左
        Stack<Node> stack = new Stack<>();
        Stack<Node> reStack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            reStack.push(cur);
            if(cur.hasLeft()){
                stack.push(cur.left);
            }
            if(cur.hasRight()){
                stack.push(cur.right);
            }
        }
        while (!reStack.isEmpty()) {
            System.out.print(reStack.pop().data + " ");
        }
    }

    /**
     * 中序遍历
     */
    public void midTraversal(Node head){
        if(head == null){
            return;
        }
        midTraversal(head.left);
        System.out.print(head.data + ",");
        midTraversal(head.right);
    }

    public void midTraversalWithStack(Node cur){
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || cur!=null){
            //有左子树 压入左子树
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                //左子树不存在   打印
                cur = stack.pop();
                System.out.print(cur.data+",");
                cur = cur.right;
            }


        }
        //1.压入整条左边界
        //2.左子节点不存在，出栈，取右子节点开启下一轮
    }



}
