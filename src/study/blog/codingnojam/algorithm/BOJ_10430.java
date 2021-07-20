package study.blog.codingnojam.algorithm;

import java.io.IOException;
import java.util.Scanner;

public class BOJ_10430 {

    // 백준온라인저지 10430번 나머지 Java로 문제 풀이
    public static void main(String[] args) throws IOException {

        // 주어진 입력 정보 받기
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        // 문제에서 요구하는 대로 출력
        System.out.println( (A+B)%C );
        System.out.println( ((A%C) + (B%C)) % C ) ;
        System.out.println( (A*B)%C );
        System.out.println( ((A%C) * (B%C)) % C ) ;
    }
}
