package study.blog.codingnojam.algorithm.codility;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.ArrayList;
import java.util.List;

class Codlity_greedy_MaxNonoverlappingSegments {
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8

        if (A.length == 0 || B.length==0) {
            return 0;
        }
        int r = 0;

        for (int i = 0; i < A.length-1; i++) {
            int lt = i;
            int rt = i+1;
            int result = 1;
            while (rt < A.length) {
                if (B[lt] < A[rt]) {
                    result++;
                    lt = rt;
                    rt++;
                } else {
                    rt++;
                }
            }
            r = Math.max(r, result);

        }
        return r;

    }
}
