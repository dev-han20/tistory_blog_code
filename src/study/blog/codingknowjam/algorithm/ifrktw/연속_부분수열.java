package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class 연속_부분수열 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int start = 0;
        int end = 0;
        int sum = arr[start];
        int count = 0;

        while (end < arr.length) {
            if (sum == M) {
                count++;
                sum = sum - arr[start++];
                end++;
                if(end == arr.length) break;
                sum = sum + arr[end];
            } else if (sum > M) {
                sum = sum - arr[start++];
            } else {
                end++;
                if(end == arr.length) break;
                sum = sum + arr[end];
            }
        }
        System.out.println(count);
    }
}
