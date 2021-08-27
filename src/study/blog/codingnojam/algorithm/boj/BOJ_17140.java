package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        while (true) {

        }


    }

    class Node {
        int count;
        int value;

        public Node(int count, int value){
            this.count = count;
            this.value = value;
        }

    }
}
