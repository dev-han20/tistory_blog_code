package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 온라인저지 16234번 인구이동 문제 풀이
 **/
public class BOJ_16234 {
    public static void main(String[] args) throws IOException {

        // 입력을 받기 위한 리더객체
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // N L R 입력받기
        String[] info = reader.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int L = Integer.parseInt(info[1]);
        int R = Integer.parseInt(info[2]);

        // 국가들 인구수 표현할 2차원 배열 맵변수 선언
        int[][] map = new int[N][N];

        // 2차원 배열 맵 초기화
        for (int i = 0; i < N; i++) {
            String[] temp = reader.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Queue<Country> Q = new LinkedList<>();  // BFS에 사용할 큐
        int[] moveRow = {-1, 1, 0, 0};          // 좌표 계산에 사용할 배열
        int[] moveCol = {0, 0, -1, 1};          // 좌표 계산에 사용할 배열
        int result = 0;                         // 최종 결과값을 저장할 변수


        while (true) {
            int[][] chk = new int[N][N];    // BFS 방문처리에 사용할 배열
            boolean flag = false;           // 인구이동이 일어났는지 아닌지 체크하기 위한 변수
            // 맵 전체 순회하면서 BFS 진행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 한번의 BFS에서 연합이 된 국가를 저장할 리스트
                    ArrayList<Country> list = new ArrayList<>();
                    // 연합의 인구 총 합
                    int sum = 0;
                    // 한번도 방문하지 않은 국가인 경우
                    if (chk[i][j] == 0) {
                        // 큐에 넣기
                        Q.offer(new Country(i, j));
                        // 리스트에 넣기
                        list.add(new Country(i, j));
                        // 해당 국가 인구수 더해서 갱신
                        sum += map[i][j];
                        // 해당 국가 방문처리
                        chk[i][j] = 1;
                    } else {
                        // 방문한 국가는 건너뛰고 다음 국가로 이동
                        continue;
                    }

                    // 큐가 비어있지 않으면
                    while (!Q.isEmpty()) {
                        // 큐에서 국가 꺼내기
                        Country country = Q.poll();

                        for (int k = 0; k < 4; k++) {
                            // 상 하 좌 우 이동을 위한 좌표 계산
                            int nextRow = country.row + moveRow[k];
                            int nextCol = country.col + moveCol[k];

                            // 맵을 벗어나거나 방문한 적이 있으면 다음 좌표로 이동
                            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || chk[nextRow][nextCol] == 1) {
                                continue;
                            }

                            // 국가간 인구수 차이 계산
                            int diff = Math.abs(map[country.row][country.col] - map[nextRow][nextCol]);
                            // 방문하지 않았어도 국가간의 인구수 차이가 L이상 R이하가 아니라면 다음 좌표로 이동
                            if( diff < L || diff > R){
                                continue;
                            }

                            // 큐에 해당 국가 넣기
                            Q.offer(new Country(nextRow, nextCol));
                            // 리스트에 해당 국가 넣기
                            list.add(new Country(nextRow, nextCol));
                            // 해당 국가의 인구수를 더해서 sum변수 갱신
                            sum += map[nextRow][nextCol];
                            // 해당 국가 방문처리
                            chk[nextRow][nextCol] = 1;
                        }
                    }

                    // while문을 통과하면서 list에 2개 이상의 국가가 존재한다면 연합이 생긴 걸로 판단
                    // 그렇지 않으면 연합이 아니므로 인구이동은 일어나지 않음
                    if (list.size() >= 2) {
                        // 연합국가의 인구수 = 연합국가 인구수 총합 / 연합국가 수
                        int population = sum / list.size();
                        for (Country country : list) {
                            // 연합국가들의 인구수 갱신
                            map[country.row][country.col] = population;
                        }
                        // 인구이동이 발생한 것으로 체크
                        flag = true;
                    }
                }
            }

            // 전체 맵에서 BFS순회가 종료되고 인구이동이 발생했다면
            if (flag) {
                // 결과 값 1 증가
                result++;
            } else {
                // 아니라면 인구이동이 종료되야하므로 결과값 출력 후 종료
                System.out.println(result);
                return;
            }
        }

    }

    // 국가를 표현할 클래스
    static class Country{
        int row;    // 행
        int col;    // 열

        public Country(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
