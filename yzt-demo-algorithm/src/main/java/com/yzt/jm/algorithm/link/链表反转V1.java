package com.yzt.jm.algorithm.link;

/**
 * @Description: 反转链表
 * @Author: min
 * @Date: 2021-04-09 09:26
 */
public class 链表反转V1 {

    public static void main(String[] args) {
        Node head = new Node(-1);
        Node node1 = new Node(1);
        head.next = node1;
        Node node2 = new Node(2);
        node1.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }

        temp = reverse(head);
        System.out.println("");
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
    }

    /**
     * 递归
     * 【关键点】压栈保存当前head（并且知道next）, 直到next=null（末尾）
     * 从后往前处理【倒置】 next.next = head  next = null
     *
     * @param cur 头
     * @return 反向链表
     */
    public static Node reverse(Node cur) {
        Node pre = null;
        Node next;

        //1.需要提前知道pre cur
        //2.在转换之前（cur.next = pre）要先备份next
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * a->b->c->d
     * a<-b<-c<-d
     *
     * @param pre
     * @return
     */
    public static Node reverse1(Node pre) {
        //依次递归至最后节点
        if(pre== null || pre.next == null){
            return pre;
        }
        Node last = reverse1(pre.next);
        // 每个递归都知道
        Node cur = pre.next;
        cur.next = pre;
        pre.next = null;
        return last;
    }

    public static class Node {
        public Node(int data) {
            this.data = data;
        }

        public int data;
        public Node next;

        public boolean hasNext() {
            return next != null;
        }
    }
}


