package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 온라인저지 16234번 인구이동 문제 풀이
 **/
public class BOJ_16234 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] info = reader.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int L = Integer.parseInt(info[0]);
        int R = Integer.parseInt(info[0]);

        int[][] map = new int[N][N];
        int[][] chk = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = reader.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

            }
        }


    }
}
