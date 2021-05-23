package com.yzt.jm.algorithm.tree;

import java.util.List;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-11 17:57
 */
class NaryNode {
    NaryNode(int data){
        this.data = data;
    }
    NaryNode(int data, List<NaryNode> children){
        this.data = data;
        this.children = children;
    }
    public List<NaryNode> children;
    public int data;

}
