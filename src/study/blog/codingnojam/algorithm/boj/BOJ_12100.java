package study.blog.codingnojam.algorithm.boj;

import java.io.*;

// 백준온라인저지 12100 2048(easy) 문제 풀이 Java
public class BOJ_12100 {

    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        boolean[][] sumChk = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] info = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(info[j]);
            }
        }

        recursion(0, 5, board);


    }

    static void recursion(int index, int limit, int[][] board) {

        for (int i = 0; i < 4; i++) {
            if (i == 0) { //상
                for (int j = 0; j < board.length; j++) {
                    for (int k = 0; k < board.length; k++) {
                        
                    }
                }
                

            } else if (i == 1) {

            } else if (i == 2) {

            } else {
                
            }
            
        }

    }

}
