package study.blog.codingknowjam.algorithm.codility;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Codlity_greedy_MaxNonoverlappingSegments {
    public int solution(int[] A, int[] B) {

        if (A.length == 0 || B.length==0) {
            return 0;
        }

        int lt = 0;
        int rt = 1;
        int result = 1;

        while (rt < A.length) {
            if (B[lt] < A[rt]) {
                result++;
                lt = rt;
            }
            rt++;
        }
        return result;

    }
}
