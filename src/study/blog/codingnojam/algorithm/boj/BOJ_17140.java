package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준온라인저지 17140번 이차원 배열과 연산 문제풀이 Java
public class BOJ_17140 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        int r = Integer.parseInt(info[0]);
        int c = Integer.parseInt(info[1]);
        int k = Integer.parseInt(info[2]);

        int[][] arr = new int[101][101];
        for (int i = 1; i < 4; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j < 4; j++) {
                arr[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        int time = 0;
        int rowCount = 3;
        int colCount = 3;

        while (true) {

            if (arr[r][c] == k) {
                break;
            }

            time++;

            if (rowCount >= colCount) {
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i][1] == 0) {
                        break;
                    }
                    HashMap<Integer, Integer> dupCount = new HashMap<>();
                    List<Node> list = new ArrayList<>();
                    for (int j = 1; j < arr[i].length; j++) {
                        if (arr[i][j] != 0) {
                            dupCount.put(arr[i][j], dupCount.getOrDefault(arr[i][j], 0) + 1);
                        } else {
                            break;
                        }
                    }

                    for (Map.Entry<Integer, Integer> e : dupCount.entrySet()) {
                        list.add(new Node(e.getKey(), e.getValue()));
                    }

                    Collections.sort(list, (o1, o2) -> {
                            if (o1.count > o2.count) {
                                return 1;
                            } else if (o1.count == o2.count) {
                                if (o1.value > o2.value) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                return -1;
                            }
                    });

                    int index = 0;
                    for (int j = 0; j < list.size() ; j++) {
                        if (j == 50) {
                            break;
                        }
                        int length = (j + 1) * 2;
                        index = length;
                        Node node = list.get(j);
                        arr[i][(j + 1) * 2 - 1] = node.value;
                        arr[i][(j + 1) * 2] = node.count;
                    }

                    colCount = Math.max(colCount, index);

                    for (int j = index+1; j < 101; j++) {
                        if (arr[i][j] != 0) {
                            arr[i][j] = 0;
                        } else {
                            break;
                        }
                    }
                }
            } else {

            }


        }

        System.out.println(time);

    }

    static class Node {
        int value;
        int count;

        public Node(int value, int count){
            this.value = value;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", count=" + count +
                    '}';
        }
    }
}
