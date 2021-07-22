package study.blog.codingnojam.algorithm;

import java.util.Scanner;

public class BOJ_2609 {

    // 백준온라인저지 2609번 최대공약수와 최소공배수 Java로 문제풀이
    public static void main(String[] args) {

        //
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        int N = eucd(Math.max(A, B), Math.min(A, B));

        A = A / N;
        B = B / N;

        int M = A * B * N;

        System.out.println(N);
        System.out.println(M);
    }

    static int eucd(int bn, int sn) {
        int r = bn % sn;
        if (r == 0) {
            return sn;
        } else {
            return eucd(sn, r);
        }
    }

}
