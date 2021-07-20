package study.blog.codingnojam.algorithm;

import java.io.*;

public class BOJ_14499 {

    // 백준온라인저지 14499번 주사위 굴리기 Java로 문제 풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

        String[] command = br.readLine().split(" ");

        int[] moveRow = {0, 0, -1, 1};
        int[] moveColumn = {1, -1, 0, 0};

        for (int i = 0; i < K; i++) {
            int direct = Integer.parseInt(command[i]);
            int mr = dice.row + moveRow[direct-1];
            int mc = dice.column + moveColumn[direct-1];

            if (mr < 0 || mr >= N || mc < 0 || mc >= M) {
                continue;
            } else {
                dice.row = mr;
                dice.column = mc;
                int temp = 0;
                switch (direct) {
                    case 1 :    // 동
                        temp = dice.top;
                        dice.top = dice.left;
                        dice.left = dice.bottom;
                        dice.bottom = dice.right;
                        dice.right = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                    case 2 : // 서
                        temp = dice.top;
                        dice.top = dice.right;
                        dice.right = dice.bottom;
                        dice.bottom = dice.left;
                        dice.left = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                    case 3 :
                        temp = dice.top;
                        dice.top = dice.back;
                        dice.back = dice.bottom;
                        dice.bottom = dice.front;
                        dice.front = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                    case 4 :
                        temp = dice.top;
                        dice.top = dice.front;
                        dice.front = dice.bottom;
                        dice.bottom = dice.back;
                        dice.back = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                }
                bw.write(String.valueOf(dice.top));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }

    static class Dice {
        int top, bottom, left, right, front, back;
        int row, column;
    }
}
