package study.blog.codingnojam.algorithm.boj;

import java.io.*;

public class BOJ_14499 {

    // 백준온라인저지 14499번 주사위 굴리기 Java로 문제 풀이
    public static void main(String[] args) throws IOException {

        // 입력정보 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);      // 지도 세로 사이즈
        int M = Integer.parseInt(info[1]);      // 지도 가로 사이즈
        int R = Integer.parseInt(info[2]);      // 지도에 위치한 주사위 좌표(세로)
        int C = Integer.parseInt(info[3]);      // 지도에 위치한 주사위 좌표(가로)
        int K = Integer.parseInt(info[4]);      // 주사위 굴리는 명령 개수

        // 주사위 생성후 좌표 초기화
        Dice dice = new Dice();
        dice.row = R;
        dice.column = C;

        // 지도 생성 후 주어진 정보에 따라 초기화
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 주사위 굴리는 순서 받기
        String[] command = br.readLine().split(" ");

        // 주사위 좌표 이동할 때 사용할 배열
        int[] moveRow = {0, 0, -1, 1};
        int[] moveColumn = {1, -1, 0, 0};

        // 명령 개수만큼 반복문 실행
        for (int i = 0; i < K; i++) {
            // 동서남북 중 이동해야할 좌표 계산
            int direct = Integer.parseInt(command[i]);
            int mr = dice.row + moveRow[direct-1];
            int mc = dice.column + moveColumn[direct-1];

            // 계산 된 좌표가 지도를 벗어나면 명령 무시
            if (mr < 0 || mr >= N || mc < 0 || mc >= M) {
                continue;
            } else {
                // 계산 된 좌표로 주사위 이동
                dice.row = mr;
                dice.column = mc;
                int temp = 0;
                switch (direct) {
                    case 1 :    // 동쪽으로 이동
                        // 주사위가 구르면 각각의 면들의 값이 바뀌므로 값 갱신
                        temp = dice.top;
                        dice.top = dice.left;
                        dice.left = dice.bottom;
                        dice.bottom = dice.right;
                        dice.right = temp;
                        // 이동한 좌표값이 0이면 주사위 바닥의 값을 복사
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            // 이동한 좌표값이 1이상이면 좌표값을 주사위 바닥면에 복사
                            dice.bottom = map[mr][mc];
                            // 복사 후 해당 좌표에 저장 된 값은 0으로 바뀜
                            map[mr][mc] = 0;
                        }
                        break;
                    case 2 : // 서쪽으로 이동
                        // case 1과 갱신되는 값들의 차이만 있고 나머지 로직 모두 동일
                        temp = dice.top;
                        dice.top = dice.right;
                        dice.right = dice.bottom;
                        dice.bottom = dice.left;
                        dice.left = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                    case 3 : // 북쪽으로 이동
                        // case 1과 갱신되는 값들의 차이만 있고 나머지 로직 모두 동일
                        temp = dice.top;
                        dice.top = dice.back;
                        dice.back = dice.bottom;
                        dice.bottom = dice.front;
                        dice.front = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                    case 4 : // 남쪽으로 이동
                        // case 1과 갱신되는 값들의 차이만 있고 나머지 로직 모두 동일
                        temp = dice.top;
                        dice.top = dice.front;
                        dice.front = dice.bottom;
                        dice.bottom = dice.back;
                        dice.back = temp;
                        if (map[mr][mc] == 0) {
                            map[mr][mc] = dice.bottom;
                        } else {
                            dice.bottom = map[mr][mc];
                            map[mr][mc] = 0;
                        }
                        break;
                }
                // 주사위의 이동이 종료되면 윗면의 값을 출력
                bw.write(String.valueOf(dice.top));
                // 주어진 출력대로 하기위해 줄바꿈 추가
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }

    // 주사위 클래스
    static class Dice {
        // 윗면, 아랫면, 왼쪽면, 오른쪽면, 앞면, 뒷면 변수
        int top, bottom, left, right, front, back;
        // 주사위가 위치한 좌표 (세로, 가로)
        int row, column;
    }
}
