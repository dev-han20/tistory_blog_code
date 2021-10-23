package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class Greedy_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = N;
        Man[] mans = new Man[N];

        for (int i = 0; i < N; i++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            mans[i] = new Man(h,w);
        }

        for (int i = 0; i < mans.length; i++) {
            for (int j = 0; j < mans.length; j++) {
                if (mans[i].height < mans[j].height && mans[i].weight < mans[j].weight) {
                    result--;
                    break;
                }
            }
        }
        System.out.println(result);

    }

    static class Man {
        int height;
        int weight;

        public Man(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }
}
