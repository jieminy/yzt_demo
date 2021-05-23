package com.yzt.jm.algorithm.recursion.leftright;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-20 23:31
 */
public class 全部子序列V1 {

    public static void main(String[] args){
        String a = "abcde";
        Set<String> allSub = getAllSub(a.toCharArray());
        System.out.println(allSub);
    }

    public static Set<String> getAllSub(char[] a){
        Set<String> ans = new HashSet<>();
        process(ans, a, 0, "");
        return ans;
    }

    /**
     *
     * @param ans
     * @param a
     * @param index
     * @param path
     */
    public static void process(Set<String> ans, char[] a, int index, String path){
        if(index == a.length){
            ans.add(path);
            return;
        }
        //要
        process(ans, a, index+1, path + a[index]);
        //不要
        process(ans, a, index+1, path);
    }

    public static void process1(Set<String> ans, char[] a, int index, String path){
        if(index == a.length){
            ans.add(path);
            return;
        }
        //要
        process(ans, a, index+1, path + a[index]);
        //不要
        process(ans, a, index+1, path);
    }
}
