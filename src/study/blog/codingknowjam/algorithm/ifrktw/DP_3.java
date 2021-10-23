package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Arrays;
import java.util.Scanner;

public class DP_3 {
    // LIS
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.fill(dp, 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
