package study.blog.codingknowjam.algorithm.boj;

import java.io.*;

public class BOJ_1934 {

    // 백준온라인저지 1934번 최소공배수 Java로 문제풀이
    public static void main(String[] args) throws IOException {

        // 주어진 입력정보 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스 갯수
        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 갯수만큼 반복
        for (int i = 0; i < T; i++) {
            // 두개의 수 받기
            String[] temp = br.readLine().split(" ");
            int A = Integer.parseInt(temp[0]);
            int B = Integer.parseInt(temp[1]);

            // 최대공약수 구하기
            int gcf = euclidean(Math.max(A, B), Math.min(A, B));

            // 최소공배수 = 두 수의 곱 / 최대공약수
            int lcm = A * B / gcf ;

            // 출력
            bw.write(String.valueOf(lcm));
            bw.newLine();
        }
        // 출력
        bw.flush();
    }

    /**
     * 유클리드 호제법을 구현한 메서드
     * @param bigNumber : 두개의 수 중 큰 수
     * @param smallNumber : 두개의 수 중 작은 수
     * @return
     * 큰 수를 작은 수로 나눈 나머지가 0이면 작은 수를 리턴
     * 0이 아니면 재귀형태로 다시 메서드 호출하며 파라미터로 작은 수, 나머지를 넘거줌
     */
    static int euclidean(int bigNumber, int smallNumber) {
        // 큰 수를 작은 수로 나눈 나머지를 구함
        int R = bigNumber % smallNumber;
        // 나머지가 0인 경우 작은 수 리턴
        if (R == 0) {
            return smallNumber;
        } else {
            // 나머지가 0이 아닌경우 재귀형태로 자기 자신 호출
            // 파라미터로 작은 수, 나머지를 넘김
            return euclidean(smallNumber, R);
        }
    }
}
