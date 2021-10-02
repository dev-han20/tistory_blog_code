package study.blog.codingnojam.algorithm.ifrktw;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_DFS_섬나라_아일랜드 {
    static int[] moveR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] moveC = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {

        BFS_DFS_섬나라_아일랜드 T = new BFS_DFS_섬나라_아일랜드();

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }


//        System.out.println(T.solution_BFS(map));
        System.out.println(T.solution_DFS(map));

    }

    void DFS(int lev, int[][] map, int r, int c, boolean[][] chk) {
        for (int k = 0; k < 8; k++) {
            int nr = r + moveR[k];
            int nc = c + moveC[k];

            if (nr >= map.length || nc >= map.length || nr < 0 || nc < 0 || chk[nr][nc] || map[nr][nc] == 0) {
                continue;
            }

            chk[nr][nc] = true;
            DFS(lev + 1, map, nr, nc, chk);
        }
    }

    public int solution_DFS(int[][] map) {
        Queue<Land> q = new LinkedList<>();
        boolean[][] chk = new boolean[map.length][map.length];
        int reuslt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1 && !chk[i][j]) {
                    reuslt++;
                    chk[i][j] = true;
                    DFS(0, map, i, j, chk);
                }
            }
        }

        return reuslt;
    }

    public int solution_BFS(int[][] map) {
        int[] moveR = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] moveC = {0, 1, 1, 1, 0, -1, -1, -1};
        Queue<Land> q = new LinkedList<>();
        boolean[][] chk = new boolean[map.length][map.length];
        int reuslt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1 && !chk[i][j]) {
                    reuslt++;
                    q.offer(new Land(i, j));
                    chk[i][j] = true;

                    while (!q.isEmpty()) {
                        Land now = q.poll();
                        for (int k = 0; k < 8; k++) {
                            int nr = now.row + moveR[k];
                            int nc = now.col + moveC[k];

                            if (nr >= map.length || nc >= map.length || nr < 0 || nc < 0 || chk[nr][nc] || map[nr][nc] == 0) {
                                continue;
                            }

                            q.offer(new Land(nr,nc));
                            chk[nr][nc] = true;
                        }
                    }

                }
            }
        }

        return reuslt;
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
