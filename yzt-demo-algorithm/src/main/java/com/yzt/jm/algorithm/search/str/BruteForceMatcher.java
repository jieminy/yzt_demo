package com.yzt.jm.algorithm.search.str;

/**
 * @description: 字符串暴力匹配
 * @author: jiemin
 * @date: 2020-09-08 09:10
 */
public class BruteForceMatcher {
    /**
     * 时间复杂度 o(n * m)
     * @param majorStr
     * @param pattenStr
     * @return
     */
    public static int match(String majorStr, String pattenStr){
        char[] majors = majorStr.toCharArray();
        char[] patten = pattenStr.toCharArray();

        for(int i = 0; i < majors.length; i ++){
             for(int j = 0; j < patten.length; j ++){
                if(majors[i + j] != patten[j]){
                    break;
                }
                if(j == patten.length - 1){
                    return i;
                }
            }

        }
        return 0;
    }


}
