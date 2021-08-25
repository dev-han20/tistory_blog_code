package study.blog.codingnojam.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

// 백준온라인저지 3190번 뱀문제 Java 문제풀이
public class BOJ_3190 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int boardSize = Integer.parseInt(br.readLine());

        String[][] board = new String[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(board[i], "");
        }

        int appleCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < appleCount; i++) {
            String[] appleLocation = br.readLine().split(" ");
            int row = Integer.parseInt(appleLocation[0]) -1 ;
            int col = Integer.parseInt(appleLocation[1]) -1 ;
            board[row][col] = "apple";
        }

        int commandCount = Integer.parseInt(br.readLine());

        LinkedList<String[]> commands = new LinkedList<>();
        for (int i = 0; i < commandCount; i++) {
            commands.add(br.readLine().split(" "));
        }

        int time = 0;

        int[] moveR = {0, 1, 0, -1};
        int[] moveC = {1, 0, -1, 0};

        Location head = new Location(0, 0, 0);
        Location tail = new Location(0, 0, 0);
        LinkedList<Location> tailDirections = new LinkedList<>();
        board[0][0] = "X";

        while (true) {
            time++;

            boolean appleEat = false;
            head.row = head.row + moveR[head.direction];
            head.column = head.column + moveC[head.direction];

            if (head.row >= boardSize || head.row < 0
                    || head.column >= boardSize || head.column < 0
                    || board[head.row][head.column].equals("X")) {
                break;
            }

            if (board[head.row][head.column].equals("apple")) {
                appleEat = true;
            }

            board[head.row][head.column] = "X";

            if (commands.size() != 0) {
                String[] command = commands.getFirst();
                if (command[0].equals(String.valueOf(time))) {
                    if (command[1].equals("D")) {
                        head.direction = head.direction + 1 == 4 ? 0 : head.direction + 1;
                    } else {
                        head.direction = head.direction - 1 == -1 ? 3 : head.direction - 1;
                    }
                    tailDirections.add(new Location(head.row, head.column, head.direction));
                    commands.removeFirst();
                }
            }

            if (!appleEat) {
                board[tail.row][tail.column] = "";
                tail.row = tail.row + moveR[tail.direction];
                tail.column = tail.column + moveC[tail.direction];

                if (tailDirections.size() != 0) {
                    Location temp = tailDirections.getFirst();
                    if (tail.row == temp.row && tail.column == temp.column) {
                        tail.direction = temp.direction;
                        tailDirections.removeFirst();
                    }
                }
            }
        }

        System.out.println(time);

    }

    static class Location {
        int row;
        int column;
        int direction;

        public Location(int row, int column, int direction) {
            this.row = row;
            this.column = column;
            this.direction = direction;
        }
    }

}
