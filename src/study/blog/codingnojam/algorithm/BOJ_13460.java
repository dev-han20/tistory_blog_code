package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준온라인저지 13460번 구슬탈출2 문제풀이
public class BOJ_13460 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Board board = new Board(N, M);

        for (int i = 0; i < board.map.length; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < board.map[i].length; j++) {
                board.map[i][j] = temp[j];
                if (temp[j].equals("R")) {
                    board.redBeadRow = i;
                    board.redBeadColumn = j;
                } else if (temp[j].equals("B")) {
                    board.blueBeadRow = i;
                    board.blueBeadColumn = j;
                }
            }
        }

        int[] moveRow = {-1, 1, 0, 0};
        int[] moveColumn = {0, 0, -1, 1};

        Queue<Board> bfs = new LinkedList<>();
        
        bfs.offer(board);

        // 여기부터 시작
        while (!bfs.isEmpty()) {
            Board tempBoard = bfs.poll();

            for (int i = 0; i < 4; i++) {

                switch (i) {
                    case 0 : //상
                        if (tempBoard.redBeadRow < tempBoard.blueBeadRow) {
                            int moveR = tempBoard.redBeadRow + moveRow[i];
                            int moveC = tempBoard.redBeadColumn + moveColumn[i];

                            while (tempBoard.map[moveR][moveC].equals(".")) {
                                moveR = moveR + moveRow[i];
                                moveC = moveC + moveColumn[i];
                            }

                            if (tempBoard.map[moveR][moveC].equals("#")) {
                                moveR = moveR + moveRow[i];
                                moveC = moveC + moveColumn[i];

                            } else if (tempBoard.map[moveR][moveC].equals("O")) {

                            }





                        }


                        break;
                    case 1 : // 하
                        break;
                    case 2 : // 좌
                        break;
                    case 3 : // 우
                        break;

                }
                int moveR = bead.row + moveRow[i];
                int moveC = bead.column + moveColumn[i];

                if (board[moveR][moveC].equals("#") || board[moveR][moveC].equals("B")) {
                    continue;
                } else if (board[moveR][moveC].equals(".")) {

                    while ((board[moveR][moveC].equals("."))) {
                        moveR = bead.row + moveRow[i];
                        moveC = bead.column + moveColumn[i];
                        

                    }

                } else {

                }

            }

        }


    }

    static class Board {

        String[][] map;
        int redBeadRow;
        int redBeadColumn;
        int blueBeadRow;
        int blueBeadColumn;

        public Board(int N, int M) {
            this.map = new String[N][M];
        }
    }


//    static class Bead {
//        String color;
//        int row;
//        int column;
//
//        public Bead(String color) {
//            this.color = color;
//        }
//    }

}
