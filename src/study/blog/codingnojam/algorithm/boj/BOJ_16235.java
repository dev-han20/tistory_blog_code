package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 온라인 저지 16235 나무재테크 문제 풀이
public class BOJ_16235 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] N_M_K = reader.readLine().split(" ");
        int N = Integer.parseInt(N_M_K[0]);
        int M = Integer.parseInt(N_M_K[1]);
        int K = Integer.parseInt(N_M_K[2]);
        int[][] foods = new int[N][N];
        ArrayList<ArrayList<Land>> lands = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] nourish = reader.readLine().split(" ");
            lands.add(new ArrayList<Land>());
            for (int j = 0; j < N; j++) {
                foods[i][j] = Integer.parseInt(nourish[j]);
            }
        }

        for (int i = 0; i < M; i++) {
            String[] tree = reader.readLine().split(" ");
            lands.get(Integer.parseInt(tree[0]) - 1)
                    .get(Integer.parseInt(tree[1]) - 1);

        }




    }

    public static void spring() {

    }
    public static void summer() {

    }
    public static void fall() {

    }
    public static void winter() {

    }


    static class Land {
        ArrayList<Tree> trees = new ArrayList<>();
        int row;
        int col;

        public Land(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Tree {
        int age;

        public Tree(int age) {
            this.age = age;
        }
    }

}
