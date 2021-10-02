package study.blog.codingnojam.algorithm.ifrktw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_토마토 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] info = reader.readLine().split(" ");
        int row = Integer.parseInt(info[1]);
        int col = Integer.parseInt(info[0]);
        int[][] farm = new int[row][col];
        int NRT = 0;
        Queue<Tomato> q = new LinkedList<>();

        for (int i = 0; i < farm.length; i++) {
            String[] temp = reader.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                int num = Integer.parseInt(temp[j]);
                farm[i][j] = num;
                if (num == 1) {
                    q.offer(new Tomato(i, j,0));
                } else if (num == 0) {
                    NRT++;
                }
            }
        }
        if(NRT == 0) System.out.println(NRT);

        int[] moveR = {-1, 1, 0, 0};
        int[] moveC = {0, 0, -1, 1};
        int dayCount = 0;

        while (!q.isEmpty()) {
            Tomato now = q.poll();
            dayCount = Math.max(now.day, dayCount);
            for (int i = 0; i < 4; i++) {
                int nextR = moveR[i] + now.row;
                int nextC = moveC[i] + now.col;
                if(nextR >= row || nextC >= col || nextR < 0 || nextC < 0 || farm[nextR][nextC] == -1 || farm[nextR][nextC] == 1){
                    continue;
                }

                q.offer(new Tomato(nextR, nextC, now.day + 1));
                farm[nextR][nextC] = 1;
                NRT--;
            }
        }

        if (NRT == 0) {
            System.out.println(dayCount);
        } else {
            System.out.println(-1);
        }
    }

    static class Tomato {
        int row;
        int col;
        int day;

        public Tomato(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }

}
