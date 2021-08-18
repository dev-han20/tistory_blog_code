package study.blog.codingnojam.algorithm.boj;

import java.io.*;

// 백준온라인저지 12100 2048(easy) 문제 풀이 Java
public class BOJ_12100 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        boolean[][] sumChk = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] info = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(info[j]);
            }
        }

        recursion(0, 5);


    }

    static void recursion(int index, int limit) {

    }

}
