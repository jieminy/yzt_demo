package com.yzt.jm.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class 二叉树遍历_广度 {

    List<Integer> preArr = new ArrayList<>();
    List<Integer> postArr = new ArrayList<>();

    public 二叉树遍历_广度(){
        initTree();
    }

    public static void main(String[] args){

        二叉树遍历_广度 binaryTree = new 二叉树遍历_广度();
        Node head = binaryTree.initTree();
        binaryTree.recordLevel(head);
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
     * 按层遍历
     * @param head 头节点
     */
    public void level(Node head){
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.data + ",");
            if(cur.hasLeft()){
                queue.add(cur.left);
            }
            if(cur.hasRight()){
                queue.add(cur.right);
            }
        }

    }

    /**
     * 按层遍历并记录每一层
     * @param head
     */
    public void recordLevel(Node head){
        if(head == null){
            return;
        }
        Node curEnd = head;
        Node nextEnd = null;
        int level = 0;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.print(cur.data + ",");
            if(cur.hasLeft()){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.hasRight()){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            if(cur.equals(curEnd)){
                curEnd = nextEnd;
                nextEnd = null;
                System.out.println("第" + (++level) + "层");
            }
        }
    }

}
