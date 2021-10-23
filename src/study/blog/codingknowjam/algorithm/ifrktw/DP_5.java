package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Arrays;
import java.util.Scanner;

public class DP_5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int money = sc.nextInt();

        int[] dp = new int[money+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 주어진 코인개수 만큼 반복하면서 작은값으로 dp의 값을 갱신
        for (int i = 0; i < arr.length ; i++) {
            for (int j = arr[i]; j < dp.length; j++) {
                dp[j] = Math.min(dp[j - arr[i]] + 1, dp[j]);
            }
        }

        System.out.println(dp[money]);
    }
}
