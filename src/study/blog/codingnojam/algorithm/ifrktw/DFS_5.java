package study.blog.codingnojam.algorithm.ifrktw;

import java.util.Scanner;

public class DFS_5 {

    static int[][] memo;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int R = sc.nextInt();
        memo = new int[N+1][N+1];

        System.out.println(DFS(N, R));
    }

    public static int DFS(int n, int r) {
        if(memo[n][r] > 0) return memo[n][r];
        if (r == 0 || n == r) {
            return memo[n][r] = 1;
        } else {
            return memo[n][r] = DFS(n-1, r-1) + DFS(n-1, r) ;
        }
    }

}
