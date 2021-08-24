package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준온라인저지 3190번 뱀문제 Java 문제풀이
public class BOJ_3190 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int boardSize = Integer.parseInt(br.readLine());

        String[][] board = new String[boardSize][boardSize];

        int appleCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < appleCount; i++) {
            String[] appleLocation = br.readLine().split(" ");
            int row = Integer.parseInt(appleLocation[0]);
            int col = Integer.parseInt(appleLocation[1]);
            board[row][col] = "apple";
        }

        int commandCount = Integer.parseInt(br.readLine());

        String[][] commands = new String[commandCount][2];
        for (int i = 0; i < commandCount; i++) {
            String[] command = br.readLine().split(" ");
            commands[i][0] = command[0];
            commands[i][1] = command[1];
        }


    }
}
