package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1016 {
    // 백준온라인저지(BOJ -1016) 제곱ㄴㄴ 수 문제풀이
    public static void main(String[] args) throws IOException {

        // 문제에서 주어지는 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        long min = Long.parseLong(info[0]);
        long max = Long.parseLong(info[1]);

        // 제곱ㄴㄴ수 판별에 사용 할 배열
        long[] arr = new long[1000001];

        // 배열에 저장 될 값 초기화를 위해 사용할 변수
        long init = min;

        // 제곱ㄴㄴ 개수 저장 할 변수
        int count = 0;

        // 배열 초기화 (min ~ max까지의 값 배열에 저장)
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = init++;
            if(init == max+1){
                break;
            }
        }

        // 2이상의 수에서 제곱을 해야 하기때문에 2부터 시작
        // 문제에서 max의 최대값은 1조1백만이므로 백만*백만 까지만 반복하면 됨
        // 1000001 * 1000001은 1조1백만 이상의 값이 나옴
        for (long i = 2; i <= 1000000 ; i++) {
            // 제곱수 계산
            long squareNumber = i * i;

            // 제곱수가 max값 보다 크면 더 이상 반복하지 않음
            if (squareNumber > max) {
                break;
            } else { // 제곱수가 max값이랑 같거나 작을 때
                // 제곱수로 나누어떨어지는 가장 작은 값을 찾기 위한 연산 시작
                // min을 제곱수로 나누어서 몫을 구함
                long quot = (min / squareNumber);
                // 구한 몫에 다시 제곱수를 곱해서 그 값이 min보다 작으면 몫에 1을 더함
                if (quot * squareNumber < min) {
                    quot++;
                }
                // 계산된 몫에 제곱수를 곱한 후 min값을 뺴면 배열의 인덱스를 구할 수 있음
                long j = (quot * squareNumber) - min;

                // 배열에 위에서 구한 인덱스의 값을 0으로 변경 (제곱수로 나누어지는 숫자임)
                // 인덱스에 제곱수만큼 더해서 그 다음 인덱스의 값을 0으로 변경 (제곱수의 배수이므로 나누어지는 수임)
                // 배열의 길이보다 커질 때까지 반복
                for (long k = j; k < arr.length; k = k+squareNumber) {
                    arr[(int)k] = 0;
                }
            }
        }
        // 배열에서 제곱 ㄴㄴ수 개수 세기
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] != 0){
                count++;
            }
        }

        // 출력
        System.out.println(count);
    }
}
