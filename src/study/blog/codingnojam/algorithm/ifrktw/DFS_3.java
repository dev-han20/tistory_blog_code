package study.blog.codingnojam.algorithm.ifrktw;

import java.util.Scanner;

public class DFS_3 {

    static int N, M, R;
    static Problem[] problems;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        problems = new Problem[N];
        for (int i = 0; i < problems.length; i++) {
            int score = sc.nextInt();
            int time = sc.nextInt();
            problems[i] = new Problem(score, time);
        }

        DFS(0, 0, 0);
        System.out.println(R);

    }

    public static void DFS(int len, int ss, int st) {
        if (st > M)  return;
        if(len == N){
            R = Math.max(R, ss);
            return;
        }

        DFS(len + 1, ss + problems[len].score, st+ problems[len].time);
        DFS(len + 1, ss, st);

    }

    static class Problem{
        int score;
        int time;

        public Problem(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
}
