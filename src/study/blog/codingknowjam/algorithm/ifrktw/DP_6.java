package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class DP_6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] p = new int[N][2];

        for (int i = 0; i < N; i++) {
            p[i][0] = sc.nextInt();
            p[i][1] = sc.nextInt();
        }

        int[] dp = new int[M + 1];

        // 선택할 수 있는 횟수가 유한 한경우 뒤에서부터 해당 경우까지 순차적으로 탐색
        for (int i = 0; i < p.length; i++) {
            for (int j = dp.length-1; j >= p[i][1] ; j--) {
                dp[j] = Math.max(dp[j], dp[j - p[i][1]] + p[i][0]);
            }
        }

        System.out.println(dp[M]);
    }
}
