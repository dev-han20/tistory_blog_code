package study.blog.codingnojam.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
    // 입출력을 위한 객체
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    // 문제에서 요구하는 패스워드 문자 개수
    static int L;
    // 현재 조합된 패스워드 문자 개수
    static int passwordCount = 0;
    // 현재 패스워드의 모음 개수
    static int vowelCount = 0;
    // 현재 패스워드의 자음 개수
    static int consCount = 0;

    public static void main(String[] args) throws IOException {

        // 주어진 정보 받기
       StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 패스워드를 구성하는 문자열
        String[] arr = br.readLine().split(" ");
        // 패스워드 구성하는 문자열의 현재 사용여부 판단 배열
        boolean[] chk = new boolean[arr.length];

        // 문제에서 주어진 패스워드 구성 문자열 사전순으로 정렬
        Arrays.sort(arr);
        passwordSearch(arr, chk);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void passwordSearch(String[] arr, boolean[] chk) throws IOException {
        if(passwordCount >= L){
            if (vowelCount >= 1 && consCount >= 2) {
                bw.write(sb.toString());
                bw.newLine();
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (chk[i]) {
                continue;
            }else{
                if (sb.toString().length() >= 1) {
                    if (sb.toString().charAt(passwordCount - 1) > arr[i].charAt(0)) {
                        continue;
                    }
                }
            }
            sb.append(arr[i]);
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                vowelCount++;
            } else {
                consCount++;
            }
            passwordCount++;
            chk[i] = true;
            passwordSearch(arr, chk);
            sb.deleteCharAt(passwordCount - 1);
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                vowelCount--;
            } else {
                consCount--;
            }
            passwordCount--;
            chk[i] = false;
        }
    }

}
