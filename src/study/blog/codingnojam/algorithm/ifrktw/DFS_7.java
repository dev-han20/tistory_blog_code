package study.blog.codingnojam.algorithm.ifrktw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DFS_7 {

    static int[][] miro = new int[7][7];
    static boolean[][] visited = new boolean[7][7];
    static int[] moveRow = {-1, 1, 0, 0};
    static int[] moveCol = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 7; i++) {
            String[] info = reader.readLine().split(" ");
            for (int j = 0; j <info.length; j++) {
                miro[i][j] = Integer.parseInt(info[j]);
            }
        }
        visited[0][0] = true;
        DFS(0, 0);

        System.out.println(result);

    }

    public static void DFS(int row, int col) {
        if (row == 6 && col == 6) {
            result++;
            return;
        } else {
            int nextRow = 0;
            int nextCol = 0;

            for (int i = 0; i < 4; i++) {
                nextRow = row + moveRow[i];
                nextCol = col + moveCol[i];

                if (nextRow < 0 || nextRow >= 7 || nextCol < 0 || nextCol >= 7 || visited[nextRow][nextCol] || miro[nextRow][nextCol] == 1) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                DFS(nextRow, nextCol);
                visited[nextRow][nextCol] = false;
            }
        }
    }
}
