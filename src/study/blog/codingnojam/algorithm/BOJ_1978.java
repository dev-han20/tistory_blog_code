package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1978 {

    // 백준온라인저지 1978번 소수 찾기 java로 문제풀이
    public static void main(String[] args) throws IOException {

        // 입력정보 받기 위한 버퍼 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 수 개수
        int N = Integer.parseInt(br.readLine());

        // 소수인지 아닌지 판별해야 하는 수들
        String[] info = br.readLine().split(" ");

        // 소수 판별에 사용할 배열
        boolean[] chk = new boolean[1001];

        // 소수 개수 저장할 변수
        int result = 0;

        // 0과 1은 소수가 아닙니다.
        chk[0] = true;
        chk[1] = true;

        // 에라토스테네스의 체
        // 1000이하의 자연수에 대해서 소수판별
        for (int i = 2; i < chk.length; i++) {
            // 배열의 값이 true라면 소수가 아니므로 다음 수로 넘어감
            if (chk[i]) {
                continue;
            }

            // 소수의 배수는 소수가 아니므로 체크
            for (int j = i+i; j < chk.length; j=j+i) {
                chk[j] = true;
            }
        }

        // 문제에서 요구하는 숫자들 소수인지 아닌지 판별
        for (int i = 0; i < info.length; i++) {
            // 배열에서 숫자 꺼내기
            int num = Integer.parseInt(info[i]);

            //chk배열의 인덱스는 수를 의미합니다.
            // 배열의 값이 false면 소수, true는 소수가 아님
            if (!chk[num]) {
                // 소수일 때 변수 값 1증가
                result++;
            }
        }
        // 출력
        System.out.println(result);
    }
}
