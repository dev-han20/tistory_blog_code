package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준온라인저지 13460번 구슬탈출2 문제풀이
public class BOJ_13460 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[][] board = new String[N][M];

        for (int i = 0; i < board.length; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = temp[j];
                if (temp[j].equals("R")) {
                    Bead redBead = new Bead("R", i, j);
                } else if (temp[j].equals("B")) {
                    Bead blueBead = new Bead("B", i, j);
                }
            }
        }



    }

    static class Bead {
        String color;
        int row;
        int column;

        public Bead(String color, int row, int column) {
            this.color = color;
            this.row = row;
            this.column = column;
        }
    }

}
