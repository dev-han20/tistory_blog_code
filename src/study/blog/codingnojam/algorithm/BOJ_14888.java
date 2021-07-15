package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 백준온라인저지 14888번 연산자 끼워넣기 문제풀이
public class BOJ_14888 {

    // 순열에서 결과 값 저장에 사용할 리스트
    static List<Integer> tempList = new ArrayList<>();
    // 최댓 값 저장할 변수
    static int max = Integer.MIN_VALUE;
    // 최솟 값 저장할 변수
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        // 주어진 입력 정보 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        String[] op = br.readLine().split(" ");

        // 순열을 사용하기 위해 연산자 개수만큼의 배열 생성 후 초기화
        // 0 : 더하기, 1 : 빼기, 2 : 곱하기, 3 : 나누기
        List<Integer> opList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(op[i]);
            while (temp != 0) {
                opList.add(i);
                temp--;
            }
        }

        // 순열에서 사용할 체크 배열
        boolean[] chk = new boolean[opList.size()];

        // 메서드 실행
        process(opList, chk, arr);

        // 최대, 최소 값 출력
        System.out.println(max);
        System.out.println(min);

    }

    /**
     * 순열을 통해서 주어진 문제를 해결할 메서드
     * @param opList : 연산자가 들어있는 배열
     * @param chk    : opList의 해당 인덱스의 값을 사용했는지 여부 판별
     * @param arr    : 문제에서 주어진 숫자를 저장한 배열
     */
    static void process (List<Integer> opList, boolean[] chk, String[] arr) {

        // 모든 연산자를 사용한 경우
        if (tempList.size() == opList.size()) {
            // 연산자를 순서대로 숫자 사이에 배치해서 계산한 결과 값을 저장할 변수
            // 숫자 배열의 길이가 1더 크므로 초기에 첫번째 숫자값 변수에 저장
            int result = Integer.parseInt(arr[0]);
            
            // 연산이 끝날때까지 반복
            for (int i = 0; i < tempList.size() ; i++) {
                if (tempList.get(i) == 0) { // 더하기 연산
                    result = result + Integer.parseInt(arr[i+1]);
                } else if (tempList.get(i) == 1) {  // 빼기 연산
                    result = result - Integer.parseInt(arr[i+1]);
                } else if (tempList.get(i) == 2) {  // 곱하기 연산
                    result = result * Integer.parseInt(arr[i+1]);
                } else {    // 나누기 연산
                    // 나눗셈의 경우 앞에서 계산한 값이 -라면 양수로 바꾼 후 나눈 후에 다시 음수로 변환
                    if (result < 0) {
                        result = -(Math.abs(result) / Integer.parseInt(arr[i + 1]));
                    } else {
                        // 양수인 경우 그냥 계산
                        result = result / Integer.parseInt(arr[i + 1]);
                    }
                }
            }
            // 얻은 결과 값으로 최댓값, 최솟값 저장
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }

        // 연산자 배열의 값으로 순열구하기
        // 순열은 재귀함수의 형태로 아래와 같이 구현
        for (int i = 0; i < opList.size(); i++) {
            // 해당 배열의 인덱스가 순열에 사용한 연산자면 다음 인덱스로 넘어감
            if (chk[i]) {
                continue;
            }

            // 연산자 리스트에 추가
            tempList.add(opList.get(i));
            // 해당 인덱스 연산자 사용한 것으로 체크
            chk[i] = true;
            // 재귀호출
            process(opList, chk, arr);
            // 해당 인덱스 연산자 리스트에서 제거
            tempList.remove(tempList.size()-1);
            // 해당 인덱스 연산자 사용안함으로 체크
            chk[i] = false;
        }
    }
}
