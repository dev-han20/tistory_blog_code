package study.blog.codingnojam.algorithm.codility;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Codility_array1 {
    public int[] solution(int[] A, int K) {

        if (A.length == 0) {

        } else {
            for (int k = 0; k < K; k++) {
                int temp = A[A.length-1];
                for (int i = A.length-2; i >= 0; i--) {
                    A[i + 1] = A[i];
                }
                A[0] = temp;
            }
        }


        return A;
    }
}
