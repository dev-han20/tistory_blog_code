package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 백준온라인저지 17135번 캐슬디펜스 문제 Java풀이
public class BOJ_17135 {
    static int N;       // 맵의 전체 행
    static int M;       // 맵의 전체 열
    static int D;       // 궁수 공격가능거리
    static int[][] map; // 격자판

    // 배치 할 궁수 3명
    static List<Archer> archers = new ArrayList<>();

    // 경우의 수 별로 저장할 결과 값
    static int count = 0;

    // 최종 결과 값
    static int result = 0;

    public static void main(String[] args) throws IOException {
        // 입력을 받기위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 행, 열, 궁수 공격거리 값 받아서 변수 초기화
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        D = Integer.parseInt(info[2]);

        // 격자판 생성 및 초기화
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        // 제거할 수 있는 적의 최대수 찾기
        search(0);

        // 결과 값 출력
        System.out.println(result);
    }

    /**
     * 궁수를 배치하는 경우의 수를 구하기 위한 재귀메서드
     * @param index : 궁수 배치할 위치 인덱스
     */
    static void search(int index) {

        // 궁수 3명의 배치가 끝난 경우
        if (archers.size() == 3) {

            // 경우의 수마다 시뮬레이션을 해야하므로 맵을 복사
            int[][] testMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    testMap[i][j] = map[i][j];
                }
            }

            // 남은 적의 수 카운트를 위한 변수
            // 초기값은 그냥 99로 줌
            int enemy = 99;

            // 적이 1명 이상이면 게임 계속 진행
            while (enemy > 0) {
                // 배치한 궁수 3명 하나씩 꺼내기
                for (Archer archer : archers) {

                    // 궁수가 공격할 대상 위치 초기화
                    archer.targetRow = -1;
                    archer.targetColumn = -1;

                    // 궁수와 가장 가까운 적을 찾기 위해 사용할 변수
                    int minDistance = Integer.MAX_VALUE;

                    // 가장 왼쪽 열부터 오른쪽 열로
                    for (int j = 0; j < M; j++) {
                        // 성과 가까운 행부터 가장 먼 행으로
                        for (int i = N-1; i >= 0; i--) {
                            // 해당 위치에 적이 있으면
                            if (testMap[i][j] == 1) {
                                // 적과 궁수의 거리 계산
                                int temp = Math.abs(i-archer.row) + Math.abs(j- archer.column);

                                // 계산한 값이 궁수공격가능거리보다 멀면 다음 위치탐색
                                if (temp > D) {
                                    break;
                                }

                                // 현재까지 저장된 가장 가까운 적과의 거리보다 위에서 계산한 거리 값이 작으면
                                if (minDistance > temp) {
                                    // 위에서 계산한 거리 값으로 변수 값 새로 저장
                                    minDistance = temp;
                                    // 궁수가 공격할 타켓의 위치를 현재 적의 좌표로 갱신
                                    archer.targetRow = i;
                                    archer.targetColumn = j;
                                }
                            }
                        }
                    }
                }

                // 궁수 하나씩 꺼내기
                for (Archer archer : archers) {
                    // 궁수가 공격할 타켓이 정해졌다면
                    if (archer.targetRow != -1) {
                        // 적을 공격해서 제거 (저장된 타켓의 좌표 값을 0으로 갱신)
                        if (testMap[archer.targetRow][archer.targetColumn] == 1) {
                            testMap[archer.targetRow][archer.targetColumn] = 0;

                            // 적을 제거한 수 1 증가
                            count++;
                        }
                    }
                }

                // 남아있는 적의 수를 카운트 하기 위해 0으로 초기화
                enemy = 0;

                // 테스트 맵 전체 탐색
                for (int j = 0; j < M; j++) {
                    for (int i = N - 1; i >= 0; i--) {
                        // 해당 좌표에 적이 있으면
                        if (testMap[i][j] == 1) {
                            // 아래로 한칸 이동하기 위해 좌표 값 계산
                            int moveR = i + 1;

                            // 아래로 한칸 이동 했을 때 맵을 벗어나면
                            if (moveR >= N) {
                                // 맵을 벗어나서 적은 사라짐 (해당 좌표 빈칸으로 갱신)
                                testMap[i][j] = 0;
                            } else {
                                // 맵을 벗어나지 않으면 적은 아래로 한칸 이동
                                // (해당 좌표 빈칸으로 갱신, 한칸 아래 좌표 1로 갱신)
                                testMap[i][j] = 0;
                                testMap[moveR][j] = 1;

                                // 적이 남아있으므로 카운트변수 1 증가
                                enemy++;
                            }
                        }
                    }
                }
            }
            // 테스트가 종료되고 적을 처치한 수가 여태까지 저장된 값보다 크면 값 갱신
            result = Math.max(result, count);

            // 새로운 테스트를 위해 카운트 변수 0으로 초기화
            count = 0;

            // 메서드 종료
            return;
        }

        // 완전탐색을 위한 반복문
        for (int i = index; i < M; i++) {
            // 해당 위치에 궁수 배치
            archers.add(new Archer(N, i));
            // 다음 위치로 이동
            search(i + 1);
            // 해당 위치의 궁수 제거
            archers.remove(archers.size()-1);
        }
    }

    // 궁수 클래스
    static class Archer {
        int row;            // 궁수가 있는 행
        int column;         // 궁수가 있는 열
        int targetRow;      // 궁수가 공격할 타켓의 행
        int targetColumn;   // 궁수가 공격할 타켓의 열

        // 궁수 생성자 (초기좌표 값 필요)
        public Archer(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
