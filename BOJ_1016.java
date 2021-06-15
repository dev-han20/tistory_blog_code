package study.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1016 {
    // 백준온라인저지 제곱 ㄴㄴ 수 문제풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");
        long[] arr = new long[1000001];

        long min = Long.parseLong(info[0]);
        long max = Long.parseLong(info[1]);
        long init = min;
        int count = 0;

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
