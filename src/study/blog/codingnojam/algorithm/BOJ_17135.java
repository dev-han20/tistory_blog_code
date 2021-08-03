package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17135 {

    // 백준온라인저지 17135번 캐슬디펜스 문제 Java풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);
        int D = Integer.parseInt(info[2]);
        int E = 0;

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(temp[j]) == 1) {
                    map[i][j] = Integer.parseInt(temp[j];
                    E++;
                }
            }
        }






    }

    static void search(int r, int c, int[][] map) {


        for (int i = r; i <map.length ; i++) {
            for (int j = c; j < map[i].length; j++) {


            }
        }
    }
}
