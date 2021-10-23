package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class 최대_매출 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i <N ; i++) {
            arr[i] = sc.nextInt();
        }

        int front = K;
        int back = 0;
        int result = 0;

        for (int i = 0; i < K; i++) {
            result += arr[i];
        }
        int sum = result;
        while (front < arr.length) {
            sum = sum - arr[back] + arr[front];
            result = Math.max(result, sum);
            back++;
            front++;
        }
        System.out.println(result);
    }
}
