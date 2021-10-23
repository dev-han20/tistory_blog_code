package study.blog.codingknowjam.algorithm.boj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// 백준온라인저지 4963번 섬의 개수 문제풀이
public class BOJ_4963 {
    public static void main(String[] args) throws IOException {

        // 입출력을 위한 객체
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스가 여러개일 수 있으므로 계속 반복
        while (true) {
            // 전체 맵의 행과 열 정보 받기
            String[] info = reader.readLine().split(" ");
            int R = Integer.parseInt(info[1]);
            int C = Integer.parseInt(info[0]);

            // 0 0이 들어오면 결과 값을 출력하고 종료
            if (R == 0 && C == 0) {
                writer.flush();
                return;
            }

            // 맵을 표현할 2차원 배열
            int[][] map = new int[R][C];
            // 맵 초기화 (섬과 바다 정보 받아오기)
            for (int i = 0; i < map.length; i++) {
                String[] temp = reader.readLine().split(" ");
                for (int j = 0; j < temp.length; j++) {
                    map[i][j] = Integer.parseInt(temp[j]);
                }
            }

            // BFS에 사용할 큐
            Queue<Land> q = new LinkedList<>();
            // 테스트케이스마다 섬의 개수를 저장할 변수
            int result = 0;

            // 전체 맵 순회 시작
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    // 섬을 발견하면 BFS시작
                    if (map[i][j] == 1) {
                        // 큐에 섬넣기
                        q.offer(new Land(i,j));
                        // 섬의 개수 1증가
                        // 연결된 섬의 개수를 체크하는 것이기 때문에 초기에만 1증가하고
                        // BFS가 진행되는 동안에는 개수체크하지 않음
                        result++;
                        
                        // 다음에 이동할 좌표 계산에 사용할 배열
                        int[] moveRow = {-1, -1, 0, 1, 1, 1, 0, -1};
                        int[] moveCol = {0, 1, 1, 1, 0, -1, -1, -1};

                        // 큐가 비어있지 않다면
                        while (!q.isEmpty()) {
                            // 큐에서 섬 꺼내기
                            Land land = q.poll();
                            // 상하좌우 대각선 모두 체크
                            for (int k = 0; k < moveRow.length; k++) {
                                // 다음에 이동할 행과열 좌표 계산
                                int nextRow = land.row + moveRow[k];
                                int nextCol = land.col + moveCol[k];

                                // 맵을 벗어나거나 섬이 아니라면 건너뛰기
                                if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C
                                        || map[nextRow][nextCol] == 0) {
                                    continue;
                                }

                                // 큐에 섬 넣기
                                q.offer(new Land(nextRow, nextCol));
                                // 방문처리를 따로 배열을 사용하지 않고 
                                // 섬을 바다로 바꿔서 방문처리
                                map[nextRow][nextCol] = 0;
                            }
                        }
                    }
                }
            }
            // 하나의 테스트케이스가 끝나면 해당 케이스의 결과 값 출력하기 위해 저장
            writer.write(String.valueOf(result));
            writer.newLine();
        }
    }

    // 섬을 표현할 클래스
    static class Land{
        int row;    // 행
        int col;    // 열

        public Land(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
