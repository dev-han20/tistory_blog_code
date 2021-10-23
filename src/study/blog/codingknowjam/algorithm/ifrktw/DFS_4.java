package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class DFS_4 {
    static int N, M;
    static int result = Integer.MAX_VALUE;
    static Integer[] coins;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        coins = new Integer[N];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = sc.nextInt();
        }
        M = sc.nextInt();
        Arrays.sort(coins, Collections.reverseOrder());

        DFS(0, 0);

        System.out.println(result);
    }

    public static void DFS(int len, int sum) {
        if(sum > M || len >= result) return;
        if (sum == M) {
            result = Math.min(result, len);
        } else {
            for (int i = 0; i < coins.length; i++) {
                DFS(len+1, sum + coins[i]);
            }
        }
    }
}
