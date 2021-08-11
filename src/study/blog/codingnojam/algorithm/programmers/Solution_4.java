package study.blog.codingnojam.algorithm.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 2021 카카오 채용연계인턴십  표편집 다음에 다시도전
public class Solution_4 {

    public static void main(String[] args) {
        String str = "U 324453";
        String[] te = str.split(" ");
        System.out.println(te[0]);
        System.out.println(te[1]);
        String[] a = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        String[] b = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};

        System.out.println(solution(8, 2, b));
    }

    public static String solution(int n, int k, String[] cmd) {

        StringBuilder sb = new StringBuilder();
//        Stack<Integer> stack = new Stack<>();
        int cursor = k;
        boolean[] remove = new boolean[n];
        int endIndex = n-1;
        int count = n;

        Stack<Row> stack = new Stack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < cmd.length; i++) {
            String[] now = cmd[i].split(" ");

            if (now[0].equals("U")) {
                int temp = Integer.parseInt(now[1]);
                cursor = cursor - temp;

//                for (int j = cursor-1; j >=0 ; j--) {
//                    if (!remove[j]) {
//                        temp--;
//                    }
//                    if (temp == 0) {
//                        cursor = j;
//                        break;
//                    }
//                }
            } else if (now[0].equals("D")) {
                int temp = Integer.parseInt(now[1]);
                cursor = cursor + temp;

//                for (int j = cursor+1; j <remove.length ; j++) {
//                    if (!remove[j]) {
//                        temp--;
//                    }
//                    if (temp == 0) {
//                        cursor = j;
//                        break;
//                    }
//                }
            } else if (now[0].equals("C")) {
//                stack.push(cursor);
//                remove[cursor] = true;
//                count--;
//                if (cursor == endIndex) {
//                    for (int j = cursor-1; j >=0 ; j--) {
//                        if (!remove[j]) {
//                            cursor = j;
//                            endIndex = j;
//                            break;
//                        }
//                    }
//
//                } else {
//                    for (int j = cursor+1; j <remove.length ; j++) {
//                        if (!remove[j]) {
//                            cursor = j;
//                            break;
//                        }
//                    }
//                }
                stack.push(new Row(cursor, linkedList.get(cursor)));
                if (cursor == linkedList.size() - 1) {
                    linkedList.remove(cursor);
                    cursor--;
                } else {
                    linkedList.remove(cursor);
                }

            } else {
//                int rowIndex = stack.pop();
//                remove[rowIndex] = false;
//                count++;
//                endIndex = Math.max(endIndex, rowIndex);
                Row row = stack.pop();
                linkedList.add(row.index, row.value);
                if (cursor >= row.index) {
                    cursor++;
                }
            }
        }
//        for (int i = 0; i < remove.length; i++) {
//            if (remove[i]) {
//                sb.append("X");
//            } else {
//                sb.append("O");
//            }
//        }
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (linkedList.get(index) == i) {
                index++;
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }

    static class Row {
        int index;
        int value;

        public Row(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

}

