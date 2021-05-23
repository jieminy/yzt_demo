package com.yzt.jm.algorithm.search.str;

/**
 * @description: bad character rule（坏字符） good suffix shift（好后缀）匹配算法
 * @author: jiemin
 * @date: 2020-09-11 11:15
 */
public class BMMatcher {
    private static final int SIZE = 256;

    /**
     * 初始化bc 模式串对应ascii码为下标，对应的模式串中的下标
     */
    private static void generateBC(char[] b, int m, int[] bc){
        for(int i=1; i<SIZE; i++){
            bc[i] = -1;
        }
        for(int i=0; i<m; i++){
            int ascii = b[i];
            bc[ascii] = i;
        }
    }

    //坏字符匹配
    public static int match(char[] a, int n, char[] b, int m){
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);
        int[] suffix = new int[m];
        boolean [] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0;
        while( i <= n - m){
            //j 当前匹配字符下标
            int j;
            for(j = m-1; j >=0; j--){
                if(a[i + j] != b[j]){
                    break;
                }
            }
            if(j < 0){
                return i;
            }
            int x = j - bc[a[i+j]];
            int y = 0;
            if(j < m-1){
                y = moveToGs(j, m, suffix, prefix);
            }

            i = i + Math.max(x,y);
        }
        return -1;
    }

    private static int moveToGs(int j, int m, int[] suffix, boolean[] prefix){
        //好后缀长度
        int k = m - 1 - j;
        if(suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for(int i = j+2; i < m-1; i++){
            if(prefix[i]){
                return i;
            }
        }
        return m;
    }

    /**
     * 好前缀
     * @param b 模式串
     * @param m 模式串长度
     * @param suffix 好后缀数组
     * @param prefix 记录模式串的后缀子串是否能匹配模式串的前缀子串
     */
    private static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        // b[0, i]逐个跟后缀字串比较
        for(int i=0; i<m-1; i++){
            //长度
            int j=i;
            //辅助对比
            int k=0;
            //长度0-i
            while(j >= 0 && b[j] == b[m -1 - k]){
                j--;
                k++;
                suffix[k] = j+1;
            }
            if(j < 0){
                prefix[k] = true;
            }
        }

    }
}
