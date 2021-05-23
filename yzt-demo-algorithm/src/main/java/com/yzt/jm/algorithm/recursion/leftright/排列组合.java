package com.yzt.jm.algorithm.recursion.leftright;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 排列组合
 * @Author: min
 * @Date: 2021-04-14 09:39
 */
public class 排列组合 {
    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args){
        int[] input = new int[]{1,2,3,4};
        cmn(input, 3, 0);
        List<Character> str = new ArrayList<>();
        str.add('1');
        str.add('2');
        str.add('3');
        List<String> ans = new ArrayList<>();

        cmn1(str, ans, "" );

        System.out.println("");
        System.out.println(ans);


    }

    public static void cmn(int[] input, int tag, int has){
        if(has == tag){
            System.out.print(stack);
            return;
        }

        for(int i=0; i<input.length; i++){
            if(!stack.contains(input[i])){
                // i
                stack.add(input[i]);
                cmn(input, tag, has+1);
                //
                stack.pop();
            }
        }
    }

    public static void cmn1(List<Character> rest, List<String> ans, String path){
       if(rest.isEmpty()){
           ans.add(path);
       }else{
           for(int i=0; i<rest.size(); i++){
               char cur = rest.get(i);
               rest.remove(i);
               cmn1(rest, ans, cur + path);
               rest.add(i, cur);
           }
       }

    }

    public static void cmn2(char[] str, List<String> ans, int index){
        if(index == str.length){
            ans.add(String.valueOf(str));
        }
        for(int i=0; i<str.length; i++){
            swap(str, index, i);
            cmn2(str, ans, index+1);
            swap(str, index, i);
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
