package study.blog.codingnojam.algorithm.boj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준온라인저지 1759번 암호만들기 문제풀이 (BOJ-1759)
public class BOJ_1759 {
    // 입출력을 위한 객체
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    // 문제에서 요구하는 패스워드 문자 개수
    static int L;
    // 현재까지 조합 된 패스워드 문자 개수
    static int passwordCount = 0;
    // 현재까지 조합 된 패스워드의 모음 개수
    static int vowelCount = 0;
    // 현재까지 조합 된 패스워드의 자음 개수
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
        // 패스워드 찾기
        passwordSearch(arr, chk);

        br.close();
        bw.flush();
        bw.close();
    }

    /**
     * 패스워드가 가능한 문자열 모두 찾기
     * @param arr : 문제에서 주어진 패스워드를 구성하는 문자열 배열
     * @param chk : 문자의 사용여부를 판단하는 배열
     */
    public static void passwordSearch(String[] arr, boolean[] chk) throws IOException {
        // 현재까지 조합 된 패스워드 문자개수가 문제에서 요구하는 개수보다 클 경우
        if(passwordCount >= L){
            // 현재까지 조합 된 문자가 모음 1개이상, 자음 2개이상인 경우 출력
            if (vowelCount >= 1 && consCount >= 2) {
                bw.write(sb.toString());
                bw.newLine();
            }
            // 메서드 종료
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            // 이미 조합에 사용 된 문자열인 경우 반복문 다시 실행
            if (chk[i]) {
                continue;
            }else{
                // 현재까지 조합 된 문자열의 개수가 1개 이상인 경우
                if (sb.toString().length() >= 1) {
                    // 현재까지 조합 된 문자열의 마지막 문자가 사용되지 않은 문자열 보다 유니코드값이 큰 경우 반복문 다시 실행
                    if (sb.toString().charAt(passwordCount - 1) > arr[i].charAt(0)) {
                        continue;
                    }
                }
            }
            // 패스워드 조합
            sb.append(arr[i]);
            // 패스워드 조합에 사용 된 문자가 모음인 경우
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                // 모음 개수 추가
                vowelCount++;
            } else {
                // 패스워드 조합에 사용 된 문자가 자음인 경우 자음 개수 추가
                consCount++;
            }
            // 패스워드 조합에 사용된 문자열 개수 추가
            passwordCount++;
            // 현재 문자를 패스워드 조합에 사용한걸로 체크
            chk[i] = true;

            // 패스워드 조합을 하기 위해 메서드 호출 (재귀)
            passwordSearch(arr, chk);

            // 재귀형태로 호출 된 메서드가 종료되면 사용했던 문자의 사용여부, 자음모음개수, 현재까지 조합된 패스워드 문자열 개수 등을
            // 다시 원복해서 해당 문자를 다른 반복문에서 재사용 할 수 있도록 함
            // 현재까지 조합된 패스워드에서 마지막에 조합된 문자 제거
            sb.deleteCharAt(passwordCount - 1);
            // 현재 문자가 모음인 경우
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                // 모음 개수 감소
                vowelCount--;
            } else {
                // 자음 개수 감소
                consCount--;
            }
            // 현재까지 조합 된 패스워드 문자열 개수 감소
            passwordCount--;
            // 현재 문자 사용안함으로 변경
            chk[i] = false;
        }
    }

}
