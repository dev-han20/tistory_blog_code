package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준온라인저지 13460번 구슬탈출2 문제풀이
public class BOJ_13460 {

    static int[] moveRow = {-1, 1, 0, 0};
    static int[] moveColumn = {0, 0, -1, 1};

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


        Queue<Board> bfs = new LinkedList<>();

        bfs.offer(board);

        // 여기부터 시작
        while (!bfs.isEmpty()) {
            Board tempBoard = bfs.poll();
            tempBoard.count++;
            int result;

            for (int i = 0; i < 4; i++) {

                switch (i) {
                    case 0: //상
                        if (tempBoard.redBeadRow <= tempBoard.blueBeadRow) {
                            result = firstRedSecondBlue(tempBoard, i);
                        } else {
                            result = firstBlueSecondRed(tempBoard, i);
                        }

                        if (result == -2) {
                            continue;
                        } else if (result == 0) {
                            Board newBoard = new Board(N, M);
                            newBoard.map = System.a
                        } else {
                            System.out.println(result);
                        }



                        break;
                    case 1: // 하
                        break;
                    case 2: // 좌
                        break;
                    case 3: // 우
                        break;

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
        int count;

        public Board(int N, int M) {
            this.map = new String[N][M];
        }

    }

    static int firstRedSecondBlue(Board tempBoard, int i) {
        int moveR = tempBoard.redBeadRow + moveRow[i];
        int moveC = tempBoard.redBeadColumn + moveColumn[i];
        boolean moveCheck = false;
        int result = 0;

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
            moveCheck = true;
        }

        if (tempBoard.map[moveR][moveC].equals("#") || tempBoard.map[moveR][moveC].equals("B")) {
            if (!moveCheck) {
                return -2 ;
            }
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            tempBoard.map[tempBoard.redBeadRow][tempBoard.redBeadColumn] = ".";
            tempBoard.map[moveR][moveC] = "R";

        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return tempBoard.count;
        }

        moveR = tempBoard.blueBeadRow + moveRow[i];
        moveC = tempBoard.blueBeadColumn + moveColumn[i];

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        if (tempBoard.map[moveR][moveC].equals("#") || tempBoard.map[moveR][moveC].equals("R")) {
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            tempBoard.map[tempBoard.blueBeadRow][tempBoard.blueBeadColumn] = ".";
            tempBoard.map[moveR][moveC] = "B";

        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return -1;
        }
        return 0;
    }

    static int firstBlueSecondRed(Board tempBoard, int i) {
        int moveR = tempBoard.blueBeadRow + moveRow[i];
        int moveC = tempBoard.blueBeadColumn + moveColumn[i];
        boolean moveCheck = false;

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        if (tempBoard.map[moveR][moveC].equals("#") || tempBoard.map[moveR][moveC].equals("R")) {
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            tempBoard.map[tempBoard.blueBeadRow][tempBoard.blueBeadColumn] = ".";
            tempBoard.map[moveR][moveC] = "B";

        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return -1;
        }

        moveR = tempBoard.redBeadRow + moveRow[i];
        moveC = tempBoard.redBeadColumn + moveColumn[i];

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
            moveCheck = true;
        }

        if (tempBoard.map[moveR][moveC].equals("#") || tempBoard.map[moveR][moveC].equals("B")) {
            if (!moveCheck) {
                return -2;
            }
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            tempBoard.map[tempBoard.redBeadRow][tempBoard.redBeadColumn] = ".";
            tempBoard.map[moveR][moveC] = "R";

        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            return tempBoard.count;
        }
        return 0;
    }

}
