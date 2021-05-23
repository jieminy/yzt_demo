package com.yzt.jm.algorithm.recursion.范围尝试;

/**
 * @Description:
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 * @Author: min
 * @Date: 2021-04-22 19:12
 */
public class 贴纸 {

    public static void main(String[] args){
        System.out.println(minus("abc", "a"));
    }

    /**
     * @param spiter 贴纸
     * @param target 目标字符
     * @return 至少几个贴纸
     */
    public int process(String[] spiter, String target) {
        if (target.length() == 0) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < spiter.length; i++) {
            String first = spiter[i];
            //过滤target种得first字符
            String rest = minus(target, first);
            if(rest.length()!=target.length()){
                 min = Math.min(process(spiter, rest), min);
            }
        }
        return min;
    }

    public static String minus(String target, String first) {
        int[] targetCharCnt = new int[27];
        for (char c : target.toCharArray()) {
            targetCharCnt[c-'a'] = targetCharCnt[c-'a'] + 1;
        }
        for (char c : first.toCharArray()) {
            targetCharCnt[c-'a'] = targetCharCnt[c-'a'] - 1;
        }

        StringBuilder sb = new StringBuilder("");
        for (int i : targetCharCnt) {
            if(targetCharCnt[i] > 0){
                for(int j=0; j< targetCharCnt[i]; j++){
                    sb.append((char)(targetCharCnt[i] + 'a'));
                }
            }
        }
        return sb.toString();
    }

}
