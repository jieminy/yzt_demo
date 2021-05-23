package com.yzt.jm.algorithm.recursion.leftright;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-20 23:31
 */
public class 全部子序列 {

    public static void main(String[] args){
        String a = "abcde";
        Set<String> allSub = getAllSub(a.toCharArray());
        allSub.forEach(System.out::println);
    }

    public static Set<String> getAllSub(char[] a){
        Set<String> ans = new HashSet<>();
        process(a, 0,  ans, "");
        return ans;
    }
    public static void process(char[] a, int index, Set<String> ans, String path){
        if(index == a.length){
            ans.add(path);
            return;
        }

        //没要
        process(a, index+1, ans, path);

        //要了
        process(a, index+1, ans, path + a[index]);

    }
}
