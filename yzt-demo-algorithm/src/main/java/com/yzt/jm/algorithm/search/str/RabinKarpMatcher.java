package com.yzt.jm.algorithm.search.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Rabin-Karp算法
 * @author: jiemin
 * @date: 2020-09-09 09:16
 */
public class RabinKarpMatcher {

    /**
     * 时间复杂度 o(n)
     * @param majorStr
     * @param pattenStr
     * @return
     */
    public static int match(String majorStr, String pattenStr){
        int majorLength = majorStr.length();
        int pattenLength = pattenStr.length();

        if(majorLength == 0 || pattenLength == 0){
            return -1;
        }
        char[] majors = majorStr.toCharArray();
        char[] patten = pattenStr.toCharArray();

        int pattenHash = 0;

        int[] s = new int[pattenLength];
        int[] h = new int[majorLength - pattenLength + 1];
        for(int j = 0; j < pattenLength; j ++){
            s[pattenLength - j -1] = power(26, pattenLength - j -1);
            pattenHash += (patten[j] - 'a') * s[pattenLength - j -1];
            h[0] += (majors[j] - 'a') * s[pattenLength - j -1];
        }
        if(pattenHash == h[0]){
            return 0;
        }

        for(int i=1; i <= majorLength - pattenLength; i++){
            h[i] += (h[i-1] - s[pattenLength -1] * (majors[i-1] - 'a')) * 26 + (majors[i + pattenLength - 1] - 'a');
            if(pattenHash == h[i]){
                return i;
            }
        }
        return -1;
    }

    public static int power(int a , int b) {
        int power = 1;
        for (int c = 0; c < b; c++){
            power *= a;
        }
        return power;
    }

}
