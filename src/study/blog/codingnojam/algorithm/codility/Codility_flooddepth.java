package study.blog.codingnojam.algorithm.codility;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Codility_flooddepth {

    public static void main(String[] args) {
        int[] a = {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2};
        System.out.println(solution(a));

    }

    public static int solution(int[] A) {
        int result = 0;

        if (A.length <= 2) {
            return result;
        }

        int startIndex = -1;
        int index = 0;
        while (true) {
            if (startIndex == -1) {
                if (index >= A.length - 2) {
                    break;
                }
                if (A[index] > A[index + 1]) {
                    startIndex = index;
                    index++;
                }
                index++;
            } else {
                if (index >= A.length) {
                    break;
                }
                if (A[index] >= A[startIndex]) {
                    for (int i = index; i > startIndex ; i--) {
                        result = Math.max(result, A[startIndex] - A[i]);
                    }
                    startIndex = -1;

                } else {
                    if (A[index] > A[index - 1]) {
                        for (int i = index-1; i > startIndex ; i--) {
                            if()
                            result = Math.max(result, A[index] - A[i]);
                        }
                    }
                    index++;
                }
            }
        }

        return result;
    }
}
