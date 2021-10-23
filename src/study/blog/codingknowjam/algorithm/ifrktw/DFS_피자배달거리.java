package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS_피자배달거리 {
    static int[][] map;
    static int N;
    static int M;
    static ArrayList<Location> totalPizza = new ArrayList<>();
    static LinkedList<Location> pizzas = new LinkedList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = sc.nextInt();
                if (temp == 2) {
                    totalPizza.add(new Location(i, j));
                }
                map[i][j] = temp;
            }
        }
        DFS(0);
        System.out.println(result);
    }

    public static void DFS(int index) {
        if (M == pizzas.size()) {
            int tempResult = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) {
                        int minDis = Integer.MAX_VALUE;
                        for (Location pizza : pizzas) {
                            int dis = Math.abs(i - pizza.row) + Math.abs(j - pizza.col);
                            minDis = Math.min(dis, minDis);
                        }
                        tempResult += minDis;
                    }
                }
            }
            result = Math.min(result, tempResult);
            return;
        }
        for (int i = index; i < totalPizza.size(); i++) {
            pizzas.addLast(totalPizza.get(i));
            DFS(i+1);
            pizzas.removeLast();
        }
    }

    static class Location{
        int row;
        int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
