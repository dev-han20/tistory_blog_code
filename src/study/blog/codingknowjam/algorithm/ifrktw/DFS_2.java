package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class DFS_2 {

    static int R = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int N = sc.nextInt();


        int[] arr = new int[N];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        DFS(0,0,N,arr,C);

        System.out.println(R);
    }

    public static void DFS(int level, int ws, int N, int[] arr, int C) {
        if(ws > C) return;
        if (level == N) {
            R = Math.max(R, ws);
            return;
        }

        DFS(level+1, ws+arr[level], N, arr,C);
        DFS(level+1, ws, N, arr,C);

    }
}
