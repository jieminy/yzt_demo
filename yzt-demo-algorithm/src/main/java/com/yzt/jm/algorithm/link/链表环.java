package com.yzt.jm.algorithm.link;

/**
 * @Description: 快慢指针
 * @Author: min
 * @Date: 2021-04-18 16:33
 */
public class 链表环 {

    public static void main(String[] args){
        Node head = new Node(4);
        Node node1 = new Node(3);
        head.next = node1;
        Node node2 = new Node(2);
        node1.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;
        Node node4 = new Node(4);
        node3.next = node4;
        Node node5 = new Node(5);
        node4.next = node5;
        node5.next = node2;

        System.out.println(isCycle(head) ? "存在环" : "不存在环");
    }

    public static boolean isCycle(Node head){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
