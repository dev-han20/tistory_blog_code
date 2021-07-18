package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458 {

    // 백준온라인저지 13458번 시험감독 문제 풀이
    public static void main(String[] args) throws IOException {

        // 주어진 입력 정보 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 시험장 개수
        int N = Integer.parseInt(br.readLine());
        // 시험장에 있는 응시자의 수 배열
        String[] arr = br.readLine().split(" ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 총감독관이 감독할 수 잇는 응시자의 수
        int B = Integer.parseInt(st.nextToken());
        // 부감독관이 감독할 수 있는 응시자의 수
        int C = Integer.parseInt(st.nextToken());

        // 필요한 감독관의 수를 저장할 결과 값 변수
        long count = 0;
1
        // 시험장의 수만큼 반복문 실행
        for (int i = 0; i < arr.length; i++) {
            // 시험장의 응시자 수
            int result = Integer.parseInt(arr[i]);

            // 남은 응시자 수 = 응시자 수 - 총 감독관이 감독할 수 있는 응시자 수
            result = result - B;
            // 필요한 감독관 수 +1
            count++;

            // 남은 응시자 수가 0명 이하면 부감독관 필요없으므로 다음 시험장으로 이동
            if (result <= 0) {
                continue;
            } else {
                // 남은 응시자 수가 1명 이상일 때

                // 남은 응시자 수 / 부감독관이 감독가능한 응시자 수
                // 위의 나눗셈 연산을 통해서 몫과 나머지를 구함
                int q = result / C;     // 몫
                int r = result % C;     // 나머지

                // 몫은 남은 응시자 감독에 필요한 부감독관의 수이므로 그대로 더해줌
                count = count + q;

                // 나머지가 1이상인 경우는 부감독관이 1명 더 필요하므로 감독관 수 +1 증가
                if (r > 0) {
                    count++;
                }
            }
        }
        // 결과 값 출력
        System.out.println(count);
    }
}
