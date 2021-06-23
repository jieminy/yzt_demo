package com.yzt.jm.algorithm.贪心;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 求N个数组的笛卡尔积
 * 假设当前有三个不等长数组A，B，C。三个数组分别包含如下元素
 * A：[a1, a2, a3]
 * B：[b1, b2]
 * C：[c1, c2, c3]
 * 从每个数组中选择一项，组成数列，可以获得如下数列：a1b1c1，a1b1c2，a1b1c3，a2b1c1，a2b1c2……
 * 全部的组合即为笛卡尔积。
 * 现题目要求是有N个不等长数组，要求获得这N个数组的笛卡尔积，请编写代码打印出全部的组合。
 * @Author: 殷洁民
 */
public class Interview2 {

    public static void main(String[] args) {

        List<List<String>> originList = initList();
        //初始化笛卡尔积
        List<String> desca = new ArrayList<>(originList.get(0));

        for (int i = 1; i < originList.size(); i++) {
            desca = getDesca(desca, originList.get(i));
        }
        System.out.print(desca);
    }

    /**
     * 获得俩数组笛卡尔积
     *
     * @param a 数组a
     * @param b 数组b
     * @return a与b的笛卡尔积
     */
    public static List<String> getDesca(List<String> a, List<String> b) {
        List<String> ans = new ArrayList<>();
        for (String s : a) {
            for (String value : b) {
                ans.add(s.concat(value));
            }
        }
        return ans;
    }

    public static List<List<String>> initList() {
        List<List<String>> originList = new ArrayList<>();
        List<String> fistRow = new ArrayList<>();
        fistRow.add("a1");
        fistRow.add("a2");
        fistRow.add("a3");
        originList.add(fistRow);
        List<String> secondRow = new ArrayList<>();
        secondRow.add("b1");
        secondRow.add("b2");
        originList.add(secondRow);
        List<String> thirdRow = new ArrayList<>();
        thirdRow.add("c1");
        thirdRow.add("c2");
        thirdRow.add("c3");
        originList.add(thirdRow);
        return originList;
    }


}
