package study.blog.codingnojam.algorithm.boj;

import java.io.*;

// 백준온라인저지 12100 2048(easy) 문제 풀이 Java
public class BOJ_12100 {

    // 상하좌우 이동을 반복문으로 구현하기 위해 사용할 배열
    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {

        // 입력을 받을 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 크기
        int n = Integer.parseInt(br.readLine());
        // 보드
        int[][] board = new int[n][n];


        // 보드판 정보 받아서 초기화
        for (int i = 0; i < n; i++) {
            String[] info = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(info[j]);
            }
        }

        // 5번 이하로 움직이는 모든 경우를 판달할 재귀 메서드
        recursion(0, 5, board);

        System.out.println(result);


    }

    /**
     * 이동하는 모든 경우의 수를 체크할 재귀메서드
     * @param index : 재귀메서드 진행 횟수
     * @param limit : 보드가 움직여야하는 횟수
     * @param board : 보드판
     */
    static void recursion(int index, int limit, int[][] board) {

        if (index == limit) {
            int tempReulst = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    Math.max(tempReulst, board[i][j]);
                }
            }
            Math.max(result, tempReulst);
            return;
        }

        // 보드 내에서 숫자가 합쳐졌는지 여부를 판단할 배열
        boolean[][] sumChk = new boolean[board.length][board.length];

        // 반복문으로 상하좌우 이동
        for (int i = 0; i < 4; i++) {
                for (int j = 0; j < board.length; j++) {
                    for (int k = 0; k < board[j].length; k++) {
                        int nowRow = j;
                        int nowCol = k;
                        int moveRow = nowRow + moveR[i];
                        int moveCol = nowCol + moveC[i];
                        if (moveRow >= board.length || moveRow < 0 || moveCol >= board.length || moveCol < 0) {
                            continue;
                        }
                        boolean end = false;
                        while (!end) {
                            if (board[moveRow][moveCol] == 0) {
                                board[moveRow][moveCol] = board[nowRow][nowCol];
                                board[nowRow][nowCol] = 0;
                                nowRow = moveRow;
                                nowCol = moveCol;
                                moveRow = nowRow + moveR[i];
                                moveCol = nowCol + moveC[i];
                                if (moveRow >= board.length || moveRow < 0 || moveCol >= board.length || moveCol < 0) {
                                    end = true;
                                }
                            } else if (board[moveRow][moveCol] == board[nowRow][nowCol]) {
                                if (!sumChk[moveRow][moveCol]) {
                                    board[moveRow][moveCol] = board[moveRow][moveCol] * 2;
                                    board[nowRow][nowCol] = 0;
                                    sumChk[moveRow][moveCol] = true;
                                }
                                end = true;
                            }
                        }
                    }
                }
                recursion(index +1, limit, board);
        }

    }

}
