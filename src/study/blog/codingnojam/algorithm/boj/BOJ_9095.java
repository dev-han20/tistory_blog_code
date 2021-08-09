package study.blog.codingnojam.algorithm.boj;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class BOJ_9095 {

    static int result = 0;

    // 백준온라인저지 9095번 1,2,3 더하기 문제풀이 (Java)
    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int count = 0;
            recursion(count, n);
            bw.write(String.valueOf(result));
            bw.newLine();
            result = 0;
        }

        bw.flush();
        bw.close();

    }

    static void recursion(int count, int n) {

        if (count == n) {
            result++;
            return;
        } else if (count > n) {
            return;
        }

        for (int i = 1; i <= 3; i++) {
            count += i;
            recursion(count, n);
            count -= i;
        }
    }
}
