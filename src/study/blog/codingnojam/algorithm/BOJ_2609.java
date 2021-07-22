package study.blog.codingnojam.algorithm;

import java.util.Scanner;

public class BOJ_2609 {

    // 백준온라인저지 2609번 최대공약수와 최소공배수 Java로 문제풀이
    public static void main(String[] args) {
        // 주어진 입력정보 받기
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        // 유클리드 호제법을 사용해서 두수의 최대공약수 얻기
        int N = eucd(Math.max(A, B), Math.min(A, B));

        // 최대공약수로 두수를 나눠서 몫 구하기
        A = A / N;
        B = B / N;

        // 두 수의 최소공배수 = 두 수의 최대 공약수 * 위에서 구한 몫
        int M = A * B * N;

        // 출력
        System.out.println(N);
        System.out.println(M);
    }

    /**
     * 유클리드 호제법 구현 메서드
     * @param bn : 큰 숫자
     * @param sn : 작은 숫자
     * @return
     * 큰 숫자를 작은숫자로 나눈 값이 0이면 작은숫자 리턴, 아니면 재귀형태로 자신을 호출
     */
    static int eucd(int bn, int sn) {
        // 큰숫자를 작은숫자로 나눈 나머지를 계산
        int r = bn % sn;
        // 나머지가 0이면 작은숫자가 최대공약수이므로 작은숫자 리턴
        if (r == 0) {
            return sn;
        } else {
            // 나머지가 0 이상이면 재귀형태로 호출
            // 이때 파라미터는 작은숫자와 나머지를 넣어줌
            return eucd(sn, r);
        }
    }

}
