package com.yzt.jm.algorithm.link;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-18 16:33
 */
public class 链表交叉 {

    public static void main(String[] args){
        Node head = new Node(4);
        Node node1 = new Node(3);
        head.next = node1;
        Node node2 = new Node(2);
        node1.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;
        Node node4 = new Node(5);
        node3.next = node4;

        Node headB = new Node(9);
        Node nodeB1 = new Node(11);
        headB.next = nodeB1;
        Node nodeB2 = new Node(22);
        nodeB1.next=nodeB2;
        Node nodeB3 = new Node(33);
        nodeB2.next = nodeB3;
        Node nodeB4 = new Node(44);
        nodeB3.next = nodeB4;
        nodeB4.next = node3;

        Node xNode = findX(head, headB, 5, 7);
        System.out.println(xNode!=null ? "有交叉节点" + xNode : "无交叉节点");
    }


    public static Node findX(Node a, Node b, int lenA, int lenB){
        int cntx = Math.max(lenA, lenB) -Math.min(lenA, lenB);
        if(lenA > lenB){
            for(int i=0; i<cntx; i++){
                a = a.next;
            }
        }else if(lenA < lenB){
            for(int i=0; i<cntx; i++){
                b = b.next;
            }
        }

        while(a!=null && b!=null){
            if(a == b){
                return a;
            }
            a=a.next;
            b=b.next;
        }
        return null;
    }



}
