package study.blog.codingnojam.algorithm.ifrktw;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 미로의 최단거리 통로
public class BFS_2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] miro = new int[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                miro[i][j] = sc.nextInt();
            }
        }

        Queue<Man> q = new LinkedList<>();
        boolean[][] chk = new boolean[7][7];
        chk[0][0] = true;
        q.offer(new Man(0, 0,0));
        int[] moveRow = {-1, 1, 0, 0};
        int[] moveCol = {0, 0, -1, 1};
        int result = -1;

        while (!q.isEmpty()) {
            Man man = q.poll();
            if (man.col == 6 && man.row == 6) {
                result = man.dis;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int mr = man.row + moveRow[i];
                int mc = man.col + moveCol[i];

                if (mr < 0 || mr >= 7 || mc < 0 || mc >= 7 || chk[mr][mc] || miro[mr][mc] == 1) {
                    continue;
                }

                q.offer(new Man(mr, mc, man.dis + 1));
                chk[mr][mc] = true;
            }
        }
        System.out.println(result);

    }

    static class Man{
        int row;
        int col;
        int dis;

        public Man(int row, int col, int dis) {
            this.row = row;
            this.col = col;
            this.dis = dis;
        }
    }
}
