package com.yzt.jm.algorithm.link;

import lombok.Data;

@Data
public class Node{
    public Node(int data){
        this.data = data;
    }
    public int data;
    public Node next;

    public boolean hasNext(){
        return next != null;
    }
    public void print(Node head){
        while(head != null){
            System.out.print(head.data);
            head = head.next;
        }
    }
}
