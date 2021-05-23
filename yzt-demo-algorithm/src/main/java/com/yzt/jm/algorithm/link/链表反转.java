package com.yzt.jm.algorithm.link;

import java.util.PriorityQueue;

/**
 * @Description: 反转链表
 * @Author: min
 * @Date: 2021-04-09 09:26
 */
public class 链表反转 {

    public static void main(String[] args){
        Node head = new Node(-1);
        Node node1 = new Node(1);
        head.next = node1;
        Node node2 = new Node(2);
        node1.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;

        Node temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }

       temp = reverse(head);

        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * 递归
     * 【关键点】压栈保存当前head（并且知道next）, 直到next=null（末尾）
     * 从后往前处理【倒置】 next.next = head  next = null
     * @param head 头
     * @return 反向链表
     */
    public static Node reverse(Node head){
        //终止条件
        if(head == null || head.next == null){
            return head;
        }
        //1.找到末尾节点 并返回
        Node last = reverse(head.next);
        //1.倒数第二个节点  d->c
        head.next.next = head;
        // 1.c->null
        head.next = null;
        //返回c
        return last;
    }

    /**
     *  a->b->c->d
     *  a<-b<-c<-d
     * @param head
     * @return
     */
    public static Node reverse1(Node head){
        Node pre = null;
        Node post;

        while(head != null){
            // 1.a  post = head.next = b 2.b post = c
            post = head.next;
            // 1.a.next = pre = null  2.b.next = a
            head.next = pre;
            //1. pre = a  2.pre = b
            pre = head;
            //1. head = b  2. head = c
            head = post;
        }
        return pre;
    }
    public static class Node{
        public Node(int data){
            this.data = data;
        }
        public int data;
        public Node next;

        public boolean hasNext(){
            return next != null;
        }
    }
}


