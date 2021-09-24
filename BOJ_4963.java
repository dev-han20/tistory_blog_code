package study.blog.codingnojam.boj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// 백준온라인저지 4963번 섬의 개수 문제풀이
public class BOJ_4963 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] info = reader.readLine().split(" ");
            int R = Integer.parseInt(info[1]);
            int C = Integer.parseInt(info[0]);
            if (R == 0 && C == 0) {
                writer.flush();
                return;
            }
            int[][] map = new int[R][C];

            for (int i = 0; i < map.length; i++) {
                String[] temp = reader.readLine().split(" ");
                for (int j = 0; j < temp.length; j++) {
                    map[i][j] = Integer.parseInt(temp[j]);
                }
            }

            Queue<Land> q = new LinkedList<>();
            int result = 0;

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == 1) {
                        q.offer(new Land(i,j));
                        result++;
                        int[] moveRow = {-1, -1, 0, 1, 1, 1, 0, -1};
                        int[] moveCol = {0, 1, 1, 1, 0, -1, -1, -1};

                        while (!q.isEmpty()) {
                            Land land = q.poll();
                            for (int k = 0; k < moveRow.length; k++) {
                                int nextRow = land.row + moveRow[k];
                                int nextCol = land.col + moveCol[k];

                                if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C
                                        || map[nextRow][nextCol] == 0) {
                                    continue;
                                }

                                q.offer(new Land(nextRow, nextCol));
                                map[nextRow][nextCol] = 0;
                            }
                        }
                    }
                }
            }
            writer.write(String.valueOf(result));
            writer.newLine();
        }
    }

    static class Land{
        int row;
        int col;

        public Land(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
