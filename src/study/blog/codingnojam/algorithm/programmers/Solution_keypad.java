package study.blog.codingnojam.algorithm.programmers;

import java.util.HashMap;

// 2020 카카오 인턴쉽 - 키패드 누르기
class Solution_keypad {
    Location leftFinger = new Location(4,1);
    Location rightFinger = new Location(4,3);
    StringBuilder sb = new StringBuilder();

    public String solution(int[] numbers, String hand) {

        HashMap<Integer, Location> keypad = new HashMap<>();
        keypad.put(0, new Location(4, 2));
        keypad.put(1, new Location(1, 1));
        keypad.put(2, new Location(1, 2));
        keypad.put(3, new Location(1, 3));
        keypad.put(4, new Location(2, 1));
        keypad.put(5, new Location(2, 2));
        keypad.put(6, new Location(2, 3));
        keypad.put(7, new Location(3, 1));
        keypad.put(8, new Location(3, 2));
        keypad.put(9, new Location(3, 3));

        for (int i = 0; i < numbers.length; i++) {

            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                fingerMove(keypad.get(numbers[i]).row, keypad.get(numbers[i]).column, "L");
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                fingerMove(keypad.get(numbers[i]).row, keypad.get(numbers[i]).column, "R");
            } else {
                int row = keypad.get(numbers[i]).row;
                int column = keypad.get(numbers[i]).column;
                int leftDistance = Math.abs(row - leftFinger.row) + Math.abs(column - leftFinger.column);
                int rightDistance = Math.abs(row - rightFinger.row) + Math.abs(column - rightFinger.column);
                if (leftDistance == rightDistance) {
                    String temp = hand.equals("left") ? "L" : "R";
                    fingerMove(row, column, temp);
                } else if (leftDistance < rightDistance) {
                    fingerMove(row, column, "L");
                } else {
                    fingerMove(row, column, "R");
                }
            }
        }
        return sb.toString();
    }

    void fingerMove(int row, int column, String hand) {
        if (hand.equals("L")) {
            sb.append("L");
            leftFinger.row = row;
            leftFinger.column = column;
        } else {
            sb.append("R");
            rightFinger.row = row;
            rightFinger.column = column;
        }
    }

    class Location {
        int row;
        int column;

        public Location(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }
}