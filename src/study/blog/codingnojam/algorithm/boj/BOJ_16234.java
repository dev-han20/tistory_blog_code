package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 온라인저지 16234번 인구이동 문제 풀이
 **/
public class BOJ_16234 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] info = reader.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int L = Integer.parseInt(info[1]);
        int R = Integer.parseInt(info[2]);

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = reader.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Queue<Country> Q = new LinkedList<>();
        int[] moveRow = {-1, 1, 0, 0};
        int[] moveCol = {0, 0, -1, 1};
        int result = 0;

       while (true) {
           int[][] chk = new int[N][N];
           boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ArrayList<Country> list = new ArrayList<>();

                    int sum = 0;
                    if (chk[i][j] == 0) {
                        Q.offer(new Country(i, j));
                        list.add(new Country(i, j));
                        sum += map[i][j];
                        chk[i][j] = 1;
                    } else {
                        continue;
                    }

                    while (!Q.isEmpty()) {
                        Country country = Q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextRow = country.row + moveRow[k];
                            int nextCol = country.col + moveCol[k];

                            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || chk[nextRow][nextCol] == 1) {
                                continue;
                            }
                            int diff = Math.abs(map[country.row][country.col] - map[nextRow][nextCol]);
                            if( diff < L || diff > R){
                                continue;
                            }

                            Q.offer(new Country(nextRow, nextCol));
                            list.add(new Country(nextRow, nextCol));
                            sum += map[nextRow][nextCol];
                            chk[nextRow][nextCol] = 1;
                        }
                    }

                    if (list.size() >= 2) {
                        int population = sum / list.size();
                        for (Country country : list) {
                            map[country.row][country.col] = population;
                        }
                        flag = true;
                    }
                }
            }

            if (flag) {
                result++;
            } else {
                System.out.println(result);
                return;
            }
        }

    }

    static class Country{
        int row;
        int col;

        public Country(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
