package study.blog.codingnojam.algorithm;

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

        // 제곱수 개수 저장 할 변수
        int count = 0;

        // 배열 초기화 (min ~ max까지의 값 배열에 저장)
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = init++;
            if(init == max+1){
                break;
            }
        }
        
        for (long i = 2; i <= 1000000 ; i++) {
            long squareNumber = i * i;
            if (squareNumber > max) {
                break;
            } else {
                long temp = (min / squareNumber);
                if (temp * squareNumber < min) {
                    temp++;
                }
                long j = (temp * squareNumber) - min;
                for (long k = j; k < arr.length; k = k+squareNumber) {
                    arr[(int)k] = 0;
                }
            }
        }
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] != 0){
                count++;
            }
        }

        System.out.println(count);
    }
}
