package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BOJ_4673 {

    // 백준온라인저지 4673 셀프 넘버 문제풀이 Java
    public static void main(String[] args) throws IOException {

        // 출력을 위한 객체
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 0 인덱스 사용안할려고 10001로 길이 줌
        boolean[] arr = new boolean[10001];

        // 0은 셀프넘버가 아님
        arr[0] = true;

        // 배열 길이만큼 반복 1부터 시작
        for (int i = 1; i < arr.length; i++) {

            // d(n)의 값을 저장할 변수
            int result = i;

            // 각 자리수마다 숫자를 뽑기위해 문자열 배열로 변환
            String[] str = String.valueOf(i).split("");

            // 문자열 배열의 각 원소는 자리수이므로 하나씩 result에 더 해줌
            for (String s : str) {
                result = result + Integer.parseInt(s);
            }

            // 더한 값이 10000보다 작거나 같은 경우
            if (result <= 10000) {
                // i는 result의 생성자이므로 result는 셀프넘버가 아님
                // 배열의 값이 true면 셀프넘버가 아니라는 뜻
                arr[result] = true;
            }
        }

        // 배열길이 만큼 반복
        for (int i = 0; i < arr.length; i++) {
            // 배열의 각 원소값이 false면 셀프넘버임
            if (!arr[i]) {
                // 출력문에 저장
                bw.write(String.valueOf(i));
                bw.newLine();
            }
        }
        // 출력
        bw.flush();
    }
}
