package com.yzt.jm.algorithm;

import com.yzt.jm.algorithm.search.str.BMMatcher;
import com.yzt.jm.algorithm.search.str.BruteForceMatcher;
import com.yzt.jm.algorithm.search.str.RabinKarpMatcher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: jiemin
 * @date: 2020-09-10 19:27
 */
@SpringBootTest
public class StrMatcherTest {

    @Test
    public void test(){
        String major = "abcasdoijgpwekfmaojvpmaalsdkfpwemgpaosdfpmqoergmqwokdaomignaonsdcoiaeognoqwaneoansocnoaiengooweijoancoanoefijonfn";
        String patten = "jvp";
        long start = System.currentTimeMillis();
        int match = BruteForceMatcher.match(major, patten);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "ms 结果：" + match);

        start = System.currentTimeMillis();
        match = RabinKarpMatcher.match(major, patten);
        end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "ms 结果：" + match);

        start = System.currentTimeMillis();
        match = BMMatcher.match(major.toCharArray(), major.length(), patten.toCharArray(), patten.length());
        end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "ms 结果：" + match);
    }
}
