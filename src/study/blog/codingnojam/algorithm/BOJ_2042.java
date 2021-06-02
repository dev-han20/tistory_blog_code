package study.blog.codingnojam.algorithm;

import java.io.*;

public class BOJ_2042 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = br.readLine().split(" ");
        // 0인덱스는 사용안하기 위해 배열길이 1 추가
        int[] arr = new int[Integer.parseInt(info[0])+1];
        for (int i = 1; i <= Integer.parseInt(info[0]); i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void segmentTreeInit(){

    }

}

