package com.yzt.jm.algorithm.tree;

import java.util.*;


public class BinaryTree {

    public BinaryTree(){
        initTree();
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

}
