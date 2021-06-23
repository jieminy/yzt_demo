package com.yzt.jm.algorithm.recursion.leftright;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-04-20 23:31
 */
public class 全部子串 {

    public static void main(String[] args) {
        String a = "abcd";
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            process(a.toCharArray(), ans, true, i, "");
        }
//        Set<String> allSub = getAllSub(a.toCharArray());
        ans.forEach(System.out::println);
    }

    public static Set<String> getAllSub(char[] a) {
        Set<String> ans = new HashSet<>();
        return ans;
    }

    //abcd  a a + b a + bc a + bcd b bc bcd
    public static void process(char[] c, List<String> as, boolean yes, int index, String path) {
        if (index == c.length || !yes) {
            if (path.trim().length() != 0) {
                as.add(path);
            }
            return;
        }
        //没要
        process(c, as, false, index + 1, path);
        //要了
        process(c, as, yes, index + 1, path + c[index]);
    }
}
