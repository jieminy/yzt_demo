package com.yzt.jm.algorithm.recursion.leftright;

import java.util.*;

/**
 * @Description: 排列组合
 * @Author: min
 * @Date: 2021-04-14 09:39
 */
public class 排列组合V1 {
    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        char[] input = new char[]{'1', '2', '3', '4'};
        List<Character> str = new ArrayList<>();
        str.add('1');
        str.add('2');
        str.add('3');
        str.add('4');
//        Set<String> ans = new LinkedHashSet<>();
//        process(str, ans, 3, "");
//        System.out.println(ans);

//        ans = new HashSet<>();
//
//        List<String> ansList = new ArrayList<>();
//        process(input, 3, 0, new Stack<>());
//        System.out.println("");
//        System.out.println(ans);
//        g1(input, 0, ansList);
//        System.out.println("");
//        System.out.println(ans);


    }

    public static void process(List<Character> a, Set<String> ans, int aim, String path) {
        if (aim == 0) {
            ans.add(path);
            return;
        }

        //index 有
        for (int index = 0; index <= a.size() - 1; index++) {
            Character remove = a.remove(index);
            process(a, ans, aim - 1, remove + path);
            a.add(index, remove);
        }
    }

    public static void process(char[] chars, int limit, int has, Stack<Character> ans) {
        if (limit == has) {
            System.out.print(ans);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (!ans.contains(chars[i])) {
                ans.push(chars[i]);
            }
            process(chars, limit, has + 1, ans);
            ans.pop();
        }
    }

    public static void g1(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            g1(str, index + 1, ans);
            swap(str, index, i);
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
