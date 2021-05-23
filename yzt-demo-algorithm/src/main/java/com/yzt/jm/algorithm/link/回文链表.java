package com.yzt.jm.algorithm.link;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-18 18:41
 */
public class 回文链表 {

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

        boolean ishw = ishw(head);
        System.out.println(ishw ? "是回文链表" : "不是回文链表");
    }

    public static boolean ishw(Node head){
        boolean flag = true;
        Node cntNode = head;
        int cnt = 0;
        while (cntNode!=null){
            cntNode = cntNode.next;
            cnt++;
        }
        Node upMid = findPreDownMid(head);
        //奇数
        if(cnt % 2 != 0){
            Node reverse = reverse(upMid.next);
            Node start = head;
            Node end = reverse;

            while(start != null && end !=null){
                if(head.data != reverse.data){
                    flag = false;
                    break;
                }
                start = start.next;
                end = end.next;
            }

            upMid.next = reverse(reverse);
            head.print(head);

        }else{//偶数
            Node reverse = reverse(upMid.next);
            Node start = head;
            Node end = reverse;

            while(start != null && end !=null){
                if(head.data != reverse.data){
                    flag = false;
                    break;
                }
                start = start.next;
                end = end.next;
            }
            upMid.next = reverse(reverse);
            head.print(head);
        }
        return flag;
    }

    public static Node findPreDownMid(Node head){
        if(head == null || head.next == null){
            return null;
        }
        Node slow = head;
        Node quick = head.next;

        while(quick.next!=null && quick.next.next!=null){
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    public static Node reverse(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }
}
