package study.blog.codingnojam.algorithm.codility;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int[] ee = {8,8,4,3};
        int k = 2;
        System.out.println(solution(ee, k));

    }
    public static int solution(int[] A, int K) {

        int result = Integer.MAX_VALUE;

        for (int i = 0; i <= A.length-K; i++) {

            int[] temp = new int[K];
            int[] tempA = new int[A.length];
            for (int j = 0; j < A.length; j++) {
                tempA[j] = A[j];
            }

            for (int j = i; j < K+i; j++) {
                temp[j - i] = tempA[j];
                tempA[j] = -1;
            }
            Arrays.sort(tempA);
            int minValue = tempA[K];
            int maxValue = tempA[A.length - 1];
            int sub = maxValue - minValue;
            result = Math.min(result, sub);

        }


        return result;
    }
}
