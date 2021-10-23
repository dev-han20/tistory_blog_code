package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// 백준온라인저지 9095번 1,2,3 더하기 문제풀이 (Java)
public class BOJ_9095 {

    // 방법의 수를 저장할 결과값 변수
    static int result = 0;

    public static void main(String[] args) throws IOException {

        // 출력에 사용할 객체
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력에 사용할 객체
        Scanner sc = new Scanner(System.in);

        // 테스트 케이스 횟수 받기
        int T = sc.nextInt();

        // 테스트 케이스 만큼 반복
        for (int i = 0; i < T; i++) {
            // 1,2,3의 더하기로 방법의 수를 구할 숫자 받기
            int n = sc.nextInt();

            // 재귀 실행을 판단할 변수
            int count = 0;
            // 재귀 메서드로 방법의 수 찾기
            recursion(count, n);

            // 결과 값 저장
            bw.write(String.valueOf(result));
            // 한칸 띄우기
            bw.newLine();

            // 결과 값 변수 0으로 초기화 (다음 테스트를 위해)
            result = 0;
        }

        // 출력
        bw.flush();
        bw.close();

    }

    /**
     * 재귀 메서드로 방법의 수 찾기
     * @param count : 숫자를 하나씩 더해서 n과 비교에 사용
     * @param n : 문제에서 주어진 정수
     */
    static void recursion(int count, int n) {

        // 1,2,3의 더하기로 n을 표현할 수 있으면
        if (count == n) {
            // 방법의 수 하나를 찾았으므로 결과값 1 증가
            result++;
            // 메서드 종료
            return;
        }
        // 1,2,3을 더해나가다가 n보다 커지면 메서드 종료
        else if (count > n) {
            return;
        }

        // 1,2,3 반복
        for (int i = 1; i <= 3; i++) {
            // n에 도달할 때까지 count변수에 더해줌
            count += i;

            // 재귀메서드 호출
            recursion(count, n);

            // 재귀가 종료되면 반복문을 통해서
            // 다음 숫자를 더하기 위해 현재 숫자는 빼줌
            count -= i;
        }
    }
}