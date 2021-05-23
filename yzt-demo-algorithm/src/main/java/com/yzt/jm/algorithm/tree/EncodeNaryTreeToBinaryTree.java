package com.yzt.jm.algorithm.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 多叉树-二叉树的序列化与反序列化(lecode 431)
 * @Author: min
 * @Date: 2021-04-11 17:49
 */
public class EncodeNaryTreeToBinaryTree {

    /**
     * 序列化
     * @param root 多叉树头节点
     * @return 二叉树头节点
     */
    public Node encode(NaryNode root){
        if(root == null){
            return null;
        }
        Node head = new Node(root.data);
        head.left = en(root.children);
        return head;

    }

    /**
     * 深度先遍历
     * @param children
     * @return
     */
    private Node en(List<NaryNode> children) {
        //终止条件
        if(children == null){
            return null;
        }
        //构建左子树有边界
        Node head = null;
        Node cur = null;
        for (NaryNode naryNode :
                children) {
            Node node = new Node(naryNode.data);
            if(cur == null){
                head = node;
            }else{
                //2.回过头来串右子树
                cur.right = node;
            }
            cur = node;
            //1.深度先遍历
            node.left = en(naryNode.children);
        }
        return head;
    }

    /**
     * 反序列化
     * @param root 二叉树头节点
     * @return 多叉树头节点
     */
    public NaryNode decode(Node root){
        if(root == null){
            return null;
        }
        return new NaryNode(root.data, de(root.left));
    }

    public List<NaryNode> de(Node root){
        List<NaryNode> children = new ArrayList<>();
        //
        while(root != null){
            //递归当前节点左子树
            NaryNode node = new NaryNode(root.data, de(root.left));
            //遍历添加右子树
            children.add(node);
            root = root.right;
        }
        return children;
    }

}
