package study.blog.codingnojam.algorithm.ifrktw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DFS_6 {
    static int[][] memo;
    static int[] chk;
    static int N, R;
    static ArrayList<int[]> list = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        memo = new int[N][N];
        int[] arr = new int[N];
        chk = new int[N+1];
        chk[0] = 1;
        int[] reuslt = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = DFS(N - 1, i);
        }

        recursion(0,0,arr,reuslt);

        for (int[] ints : list) {
            for (int i = 0; i < ints.length; i++) {
                System.out.print(ints[i] + " ");
            }
            break;
        }
    }

    public static int DFS(int n, int r) {
        if(memo[n][r] > 0) return memo[n][r];
        if (r == 0 || n == r) {
            return memo[n][r] = 1;
        } else {
            return memo[n][r] = DFS(n-1, r-1) + DFS(n-1, r) ;
        }
    }

    public static void recursion(int len, int sum, int[] arr, int[] result) {
        if(sum > R) return;
        if (len == N ) {
            if (sum == R) {
                int[] temp = new int[result.length];
                for (int i = 0; i < result.length; i++) {
                    temp[i] = result[i];
                }
                list.add(temp);
            } else {
                return;
            }
        } else {
            for (int i = 1; i <= N; i++) {
                if(chk[i] == 0) {
                    result[len] = i;
                    chk[i] = 1;
                    recursion(len + 1, sum + (i * arr[len]), arr, result);
                    chk[i] = 0;
                }
            }
        }
    }
}
