package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14499 {

    // 백준온라인저지 14499번 주사위 굴리기 Java로 문제 풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);
        int R = Integer.parseInt(info[2]);
        int C = Integer.parseInt(info[3]);
        int K = Integer.parseInt(info[4]);

        Dice dice = new Dice();
        dice.row = R;
        dice.column = C;

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        


    }

    static class Dice {
        int top, bottom, left, right, front, back;
        int row, column;
    }
}
