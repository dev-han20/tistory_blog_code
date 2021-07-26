package study.blog.codingnojam.algorithm;

import java.io.*;

public class BOJ_6588 {

    // 백준온라인저지 6588번 골드바흐의 추측 Java 풀이
    public static void main(String[] args) throws IOException {

        // 입출력을 위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 소수 판별에 사용할 배열
        // 인덱스를 숫자자체로 사용하기 위해 길이 1추가
        // 백만이하의 숫자에 대해서만 골드바흐의 추측을 검증할꺼니까 백만일로 길이설정
        boolean[] arr = new boolean[1000001];
        // 0과 1은 소수가 아닙니다.
        arr[0] = true;
        arr[1] = true;

        // 에라토스테네스의 체를 사용해서 소수 판별
        for (int i = 2; i < 1001 ; i++) {
            // 소수가 아니면 다음 숫자로 넘어감
            if (arr[i]) {
                continue;
            }
            // 소수의 배수들을 모두 체크
            // true : 소수아님, false : 소수
            for (int j = i * i; j <arr.length ; j = j + i) {
                arr[j] = true;
            }
        }

        // 테스트케이스가 10만개까지이므로 반복문은 10만번으로 설정
        for(int i =0; i < 100000; i++){
            // 주어진 짝수 받기
            int N = Integer.parseInt(br.readLine());
            // 주어진 수가 0이면 반복문의 더 이상 남은 케이스가 없으므로 종료
            if (N == 0) {
                break;
            }

            // 에라토스테네스의 체를 사용해서 소수를 판별한 배열로 반복문사용
            // 문제에서 두수의 차가 가장 큰 경우를 출력해야한다고 했으므로
            // 배열의 끝에서부터 인덱스를 줄어가면서 반복문 실행
            // 여기서 배열의 인덱스는 숫자 그 자체를 의미함
            for (int j = arr.length-1; j >= 3; j--) {
                // 주어진 수보다 현재 배열에서 읽은 숫자가 더 큰 경우
                if (j >= N) {
                    // 그냥 단순하게 모든 반복문을 루프 돌아도 되지만
                    // 그렇게 되면 시간초과 판정을 받을 확률이 높아집니다.
                    // 그러므로 주어진 수보다 낮은 경우부터 시작해야 합니다.
                    // 이를 위해 배열에서 꺼낼 인덱스를 주어진 수와 같은 수로 변경
                    // 이제 다음 반복문 실행할 때 증감식에 의해서 더 낮은 숫자부터 시작
                    j = N;
                    continue;
                }
                // 배열에서 읽은 숫자가 소수가 아닌경우 다음 수로 넘어감
                if (arr[j]) {
                    continue;
                }

                // 배열에서 읽은 숫자가 주어진 수보다 작고, 소수인경우
                // 주어진 수 - 현재 읽은 소수 = 두수의 차를 구함
                int k = N - j;

                // 두수의 차가 소수라면 문제에서 요구하는 서식대로 출력문 저장
                if (!arr[k]) {
                    String tempN = String.valueOf(N);
                    String tempK = String.valueOf(k);
                    String tempJ = String.valueOf(j);
                    bw.write(tempN + " = " + tempK + " + " + tempJ);
                    bw.newLine();
                    break;
                }

                // 반복문의 마지막까지 실행을 했을 경우
                // 주어진수를 소수의 합으로 표현할 수 없으므로
                // 문제에서 요구하는 출력문 저장
                if (j == 3) {
                    bw.write("Goldbach's conjecture is wrong.");
                    bw.newLine();
                }
            }
        }
        // 최종 출력
        bw.flush();
    }
}
