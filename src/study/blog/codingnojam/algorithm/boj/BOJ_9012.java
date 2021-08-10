package study.blog.codingnojam.algorithm.boj;

import java.io.*;

public class BOJ_9012 {

    // 백준온라인저지 9021번 괄호 문제풀이 Java
    public static void main(String[] args) throws IOException {

        // 입출력을 위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스 횟수 받기
        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 횟수만큼 반복
        for (int i = 0; i < T; i++) {
            // VPS판단 여부를 따질 문자열
            String testData = br.readLine();

            // VPS판단에 사용할 변수
            int count = 0;

            // VPS여부를 저장할 변수
            boolean chk = false;

            // 받은 문자열의 길이만큼 반복
            for (int j = 0; j < testData.length(); j++) {
                // 좌측괄호 이면 카운트 1증가
                if (testData.charAt(j) == '(') {
                    count++;
                } else {
                    // 우측괄호이면 카운트 1 감소
                    count--;
                }

                // 카운트가 0보다 작은 즉, -1이라면 VPS가 무조건 될 수 없음
                if (count < 0) {
                    // ture면 VPS가 아님
                    chk = true;
                    break;
                }

                // 문자열의 마지막 문자까지 체크한 후에
                // 카운트가 양수라면 즉, 0이 아니라면 괄호가 짝이 안맞은 경우임
                if (j == testData.length()-1 && count > 0) {
                    chk = true;
                }
            }

            // true면 VPS가 아니므로 NO
            if (chk) {
                bw.write("NO");
            } else {
                // false면 VPS이므로 YES
                bw.write("YES");
            }
            // 출력 서식에 맞춰 한칸씩 띄우기
            bw.newLine();
        }

        // 출력
        bw.flush();
        bw.close();
    }
}
