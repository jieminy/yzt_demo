package com.yzt.jm.algorithm.link;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-18 16:34
 */
public class 链表中点V1 {
    public static void main(String[] args){
        Node head = new Node(1);
        Node node1 = new Node(2);
        head.next = node1;
        Node node2 = new Node(3);
        node1.next = node2;
        Node node3 = new Node(4);
        node2.next = node3;
        Node node4 = new Node(5);
        node3.next = node4;
        Node node5 = new Node(6);
        node4.next = node5;
        Node node6 = new Node(7);
        node5.next = node6;

        Node temp = head;
        while(temp != null){
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println("");

        Node upMid = findUpMid(head);
        System.out.println("上中点Node:" + upMid);

        Node mid = findDownMid(head);
        System.out.println("下中点Node:" + mid);



    }

    /**
     * 先一起走一步
     * 1 2 3 4 5 6
     * 1 2 3 4 5 6
     *        slow  quick
     * preUp  head  head.next.next 前上中点就是 【快先走一步】
     * up     head  head   正常情况是上中点     【正常】
     * preDown head head.next 前下中点就是      【slow少走一步】
     * down   head.next head.next  下中点就     【一起走一步】
     * @param head
     * @return
     */
    public static Node findDownMid(Node head){
        Node slow = head.next;
        Node quick = head.next;

        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }

        return slow;
    }
    //
    //
    /**
     * 慢节点走一步 快节点走俩不
     * 1 2 3 4 5 6
     * 1 2 3 4 5 6
     * @param head
     * @return
     */
    public static Node findUpMid(Node head){
        Node slow = head;
        Node quick = head;

        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

}
