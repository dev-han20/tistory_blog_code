package study.blog.codingnojam.algorithm.ifrktw;

import java.util.Arrays;
import java.util.Scanner;

public class 두_배열_합치기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] n = new int[N];
        for (int i = 0; i < N; i++) {
            n[i] = sc.nextInt();
        }

        int M = sc.nextInt();
        int[] m = new int[M];
        for (int i = 0; i < M; i++) {
            m[i] = sc.nextInt();
        }

        int[] arr = new int[N + M];
        for (int i = 0; i < N; i++) {
            arr[i] = n[i];
        }
        for (int i = N; i < arr.length; i++) {
            arr[i] = m[i - N];
        }

        Arrays.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
