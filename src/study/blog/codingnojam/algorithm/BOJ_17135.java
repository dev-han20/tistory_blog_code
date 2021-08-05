package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_17135 {

    static int N;
    static int M;
    static int D;
    static int[][] map;
    static List<Archer> archers = new ArrayList<>();
    static int count = 0;
    static int result = 0;

    // 백준온라인저지 17135번 캐슬디펜스 문제 Java풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        D = Integer.parseInt(info[2]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        search(0);
        System.out.println(result);

    }

    static void search(int index) {

        if (archers.size() == 3) {
            int enemy = 99;
            while (enemy > 0) {
                for (Archer archer : archers) {
                    int minDistance = Integer.MAX_VALUE;
                    for (int j = 0; j < M; j++) {
                        for (int i = N-1; i <= 0; i--) {
                            if (map[i][j] == 1) {
                                int temp = Math.abs(i-archer.row) + Math.abs(j- archer.column);
                                if (temp > D) {
                                    break;
                                }

                                if (minDistance > temp) {
                                    minDistance = temp;
                                    archer.targetRow = i;
                                    archer.targetColumn = j;
                                }
                            }
                        }
                    }
                }

                for (Archer archer : archers) {
                    if (map[archer.row][archer.column] == 1) {
                        map[archer.row][archer.column] = 0;
                        count++;
                    }
                }

                enemy = 0;

                for (int j = 0; j < M; j++) {
                    for (int i = N - 1; i <= 0; i--) {
                        if (map[i][j] == 1) {
                            int moveR = i + 1;
                            if (moveR >= N) {
                                map[i][j] = 0;
                            } else {
                                map[i][j] = 0;
                                map[moveR][j] = 1;
                            }
                            enemy++;
                        }
                    }
                }
            }
            result = Math.max(result, count);
            count = 0;
            return;
        }

        for (int i = index; i < M; i++) {
            archers.add(new Archer(N, i));
            search(index + 1);
            archers.remove(archers.size()-1);
        }


    }

    static class Archer {
        int row;
        int column;
        int targetRow;
        int targetColumn;

        public Archer(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
