package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준온라인저지 13460번 구슬탈출2 문제풀이
// https://codingnojam.tistory.com/53
public class BOJ_13460 {

    // 상하좌우 구슬이 이동할 수 있도록 좌표계산에 사용할 배열
    static int[] moveRow = {-1, 1, 0, 0};
    static int[] moveColumn = {0, 0, -1, 1};

    // 구슬들의 방문여부를 판단 할 배열
    static boolean[][][][] visited = new boolean[10][10][10][10];

    public static void main(String[] args) throws IOException {

        // 입출력 정보 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 보드 객체 생성
        Board board = new Board(N, M);

        // 보드 객체가 가지고있는 변수들 초기화
        // 보드의 빨간구슬과 파란구슬의 위치를 R과 B로 정보가 주어지는데 그냥 빈칸인 .으로 바꿔서 저장
        // 이렇게 하는 이유는 차후에 구슬들이 이동하면서 좌표를 계산할 때 편리하게 구현하기 위함과
        // 어차피 구슬들의 위치는 변수로 저장하고 있기 떄문에 충돌했는지 여부는 판단이 가능하기 때문임
        for (int i = 0; i < board.map.length; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < board.map[i].length; j++) {
                if (temp[j].equals("R")) {
                    board.redBeadRow = i;
                    board.redBeadColumn = j;
                    board.map[i][j] = ".";
                } else if (temp[j].equals("B")) {
                    board.blueBeadRow = i;
                    board.blueBeadColumn = j;
                    board.map[i][j] = ".";
                } else {
                    board.map[i][j] = temp[j];
                }
            }
        }

        // BFS구현을 위해 사용할 큐
        // 보드판을 통채로 상하좌우로 움직이기 때문에 보드객체를 큐에 넣습니다.
        Queue<Board> bfs = new LinkedList<>();

        // 앞서 초기화 한 보드객체 큐에 넣기
        bfs.offer(board);

        // 큐에 넣은 보드객체가 가지고 있는 구슬들의 현 좌표를 방문처리
        visited[board.redBeadRow][board.redBeadColumn][board.blueBeadRow][board.blueBeadColumn] = true;

        // 문제에서 요구하는 결과를 저장할 변수
        int result = -1;

        // 큐가 빌 때까지 반복
        while (!bfs.isEmpty()) {
            // 큐에서 보드객체를 꺼냄
            Board pollBoard = bfs.poll();

            // 꺼낸 보드객체가 여태까지 움직인 횟수가 10번 이상이라면 결과값 출력 후 종료
            if (pollBoard.count >= 10) {
                System.out.println(result);
                return;
            }

            // 상하좌우 이동을 위해 4번 반복문 실행
            for (int i = 0; i < 4; i++) {
                // 큐에 넣기 위해 새로운 보드객체 생성
                Board tempBoard = new Board(N, M);

                // 새로 생성한 보드객체 변수 초기화
                tempBoard.redBeadRow = pollBoard.redBeadRow;
                tempBoard.redBeadColumn = pollBoard.redBeadColumn;
                tempBoard.blueBeadRow = pollBoard.blueBeadRow;
                tempBoard.blueBeadColumn = pollBoard.blueBeadColumn;
                tempBoard.count = pollBoard.count;
                tempBoard.map = pollBoard.map;

                // 상하좌우 중 한곳으로 보드판이 기울어질 것이므로 카운트 1 증가
                tempBoard.count++;

                // 각각의 반복마다 얻어지는 결과를 임시로 저장할 변수
                int tempResult = 0;

                switch (i) {
                    case 0: //위로 기울어질 때 (상)

                        // 현재 보드판에서 빨간구슬이 파란구슬보다 더 위에 위치할 경우
                        if (tempBoard.redBeadRow <= tempBoard.blueBeadRow) {
                            // 빨간구슬이 먼저 움직이고 그 다음 파란구슬이 움직이는 메서드 실행
                            // 메서드가 종료 되면 결과값 변수에 저장
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            // 현재 보드판에서 파란구슬이 빨간구슬보다 더 위에 위치할 경우
                            // 파란구슬이 먼저 움직이고 그 다음 빨간구슬이 움직이는 메서드 실행
                            // 메서드가 종료 되면 결과값 변수에 저장
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        // 빨간,파란구슬이 탈출하지 못하고 이동만 한 경우
                        // (0:구슬들이 이동만 함, -1:파란구슬이 탈출함, 1이상:빨간구슬이 탈출함)
                        if (tempResult == 0) {
                            // 현재 보드판에 있는 구슬들의 좌표가 이전에 방문한 적이 없는 경우 (반대의 경우는 큐에 넣지 않음)
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                // 큐에 보드객체를 넣는다
                                bfs.offer(tempBoard);
                                // 현재 보드판에 있는 구슬들의 좌표를 방문처리
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            // 빨간,파란구슬 둘 중 하나라도 탈출 한 경우 보드판이 움직인 횟수 변수에 저장
                            result = Math.max(result, tempResult);
                        }

                        break;
                    case 1: // 아래로 기울어질 때 (하)

                        // 현재 보드판에서 빨간구슬이 파란구슬보다 더 아래에 위치할 경우
                        // 나머지 코드는 case 0 에서 설명했던 부분과 동일합니다.
                        if (tempBoard.redBeadRow >= tempBoard.blueBeadRow) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            // 현재 보드판에서 파란구슬이 빨간구슬보다 더 아래에 위치할 경우
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                    case 2: // 왼쪽으로 기울어질 때 (좌)

                        // 현재 보드판에서 빨간구슬이 파란구슬보다 더 왼쪽에 위치할 경우
                        // 나머지 코드는 case 0 에서 설명했던 부분과 동일합니다.
                        if (tempBoard.redBeadColumn <= tempBoard.blueBeadColumn) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            // 현재 보드판에서 파란구슬이 빨간구슬보다 더 왼쪽에 위치할 경우
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                    case 3: // 오른쪽로 기울어질 때 (우)

                        // 현재 보드판에서 빨간구슬이 파란구슬보다 더 오른쪽에 위치할 경우
                        // 나머지 코드는 case 0 에서 설명했던 부분과 동일합니다.
                        if (tempBoard.redBeadColumn >= tempBoard.blueBeadColumn) {
                            tempResult = firstRedSecondBlue(tempBoard, i);
                        } else {
                            // 현재 보드판에서 파란구슬이 빨간구슬보다 더 오른쪽에 위치할 경우
                            tempResult = firstBlueSecondRed(tempBoard, i);
                        }

                        if (tempResult == 0) {
                            if (!visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]) {
                                bfs.offer(tempBoard);
                                visited[tempBoard.redBeadRow][tempBoard.redBeadColumn][tempBoard.blueBeadRow][tempBoard.blueBeadColumn]= true;
                            }
                        } else {
                            result = Math.max(result, tempResult);
                        }

                        break;
                }
            }

            // 상하좌우 각각의 이동이 끝났을 때, 결과변수가 양수라면 빨간구슬이 탈출한 것이므로 횟수 출력 후 종료
            if (result > 0) {
                System.out.println(result);
                return;
            }
        }

        // 큐가 비었다면 빨간구슬이 탈출가능한 경우가 없다는 것이기 때문에 초기값 -1 출력
        System.out.println(result);
    }

    // 보드판의 상태를 나타낼 보드클래스
    static class Board {
        String[][] map;         // 보드판을 구현할 2차원 배열
        int redBeadRow;         // 보드판의 빨간구슬 행
        int redBeadColumn;      // 보드판의 빨간구슬 열
        int blueBeadRow;        // 보드판의 파란구슬 행
        int blueBeadColumn;     // 보드판의 빨간구슬 열
        int count;              // 보드판을 움직인 횟수

        public Board(int N, int M) {
            this.map = new String[N][M];
        }
    }

    // 빨간구슬이 먼저 움직이고 그 다음 파란구슬이 움직이는 경우에 사용하는 메서드
    static int firstRedSecondBlue(Board tempBoard, int i) {
        // 보드판을 기울인 방향으로 구슬이 1칸 이동한 좌표 계산
        int moveR = tempBoard.redBeadRow + moveRow[i];
        int moveC = tempBoard.redBeadColumn + moveColumn[i];
        // 결과값 저장 할 변수
        int result = 0;
        // 빨간구슬이 탈출여부를 판단할 변수
        boolean redEscChk = false;

        // 구슬이 1칸 이동한 곳이 빈칸이면 빈칸이 아닐 때까지 기울인 방향으로 계속 이동
        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        // 구슬이 다음에 이동할 좌표가 벽인 경우
        if (tempBoard.map[moveR][moveC].equals("#")) {
            // 현재 좌표는 벽이고 그 전 좌표가 빈칸이므로 좌를 다시 계산
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            // 계산한 좌표를 빨간구슬의 좌표변수에 저장
            tempBoard.redBeadRow = moveR;
            tempBoard.redBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            // 구슬이 다음에 이동할 좌표가 탈출구인 경우
            // 체크변수 true로 저장
            redEscChk = true;
            // 보드판이 지금까지 움직인 횟수 결과값에 저장
            result = tempBoard.count;
        }

        // 빨간구슬의 이동이 끝나고 파란구슬의 이동을 위해 좌표 계산
        moveR = tempBoard.blueBeadRow + moveRow[i];
        moveC = tempBoard.blueBeadColumn + moveColumn[i];

        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        // 파란구슬이 다음에 이동할 좌표에 벽이 있는 경우
        if (tempBoard.map[moveR][moveC].equals("#")) {
            // 현재 좌표는 벽이므로 좌표를 다시 계산
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            // 다시 계산한 좌표에 만약에 앞서 먼저 움직였던 빨간구슬이 존재하는 경우
            if (moveR == tempBoard.redBeadRow && moveC == tempBoard.redBeadColumn) {
                // 빨간구슬과 같은 좌표에 존재할 수 없으므로 좌표를 다시 계산
                moveR = moveR - moveRow[i];
                moveC = moveC - moveColumn[i];
            }
            // 파란구슬의 좌표 저장
            tempBoard.blueBeadRow = moveR;
            tempBoard.blueBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            // 파란구슬이 다음에 이동할 좌표가 탈출구인 경우 -1 리턴
            return -1;
        }

        // 빨간 구슬이 탈출했으면 보드판이 움직인 횟수 리턴
        if (redEscChk) {
            return result;
        } else {
            // 탈출하지 못했으면 0 리턴
            return 0;
        }
    }

    // 파란구슬이 먼저 움직이고 그 다음 빨간구슬이 움직이는 경우에 사용하는 메서드
    static int firstBlueSecondRed(Board tempBoard, int i) {
        // 파란구슬이 이동할 좌표 계산
        int moveR = tempBoard.blueBeadRow + moveRow[i];
        int moveC = tempBoard.blueBeadColumn + moveColumn[i];

        // 이동할 좌표가 빈칸인경우 계속 기울인 방향으로 이동
        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        // 파란구슬이 다음에 이동할 좌표에 벽이 있는 경우
        if (tempBoard.map[moveR][moveC].equals("#")) {
            // 현재 좌표는 벽이므로 좌표를 다시 계산
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            // 계산한 파란구슬의 좌표 값 변수에 저장
            tempBoard.blueBeadRow = moveR;
            tempBoard.blueBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            // 파란구슬이 다음에 이동할 좌표가 탈출구인 경우 -1 리턴
            return -1;
        }

        // 파란구슬의 이동이 끝나고, 빨간구슬이 이동해야하므로 좌표 계산
        moveR = tempBoard.redBeadRow + moveRow[i];
        moveC = tempBoard.redBeadColumn + moveColumn[i];

        // 빨간구슬이 다음에 이동할 좌표가 빈칸인경우 기울인 방향으로 계속 이동
        while (tempBoard.map[moveR][moveC].equals(".")) {
            moveR = moveR + moveRow[i];
            moveC = moveC + moveColumn[i];
        }

        // 빨간구슬이 다음에 이동할 좌표에 벽이 있는 경우
        if (tempBoard.map[moveR][moveC].equals("#")) {
            // 현재 좌표는 벽이므로 좌표를 다시 계산
            moveR = moveR - moveRow[i];
            moveC = moveC - moveColumn[i];
            // 앞서 파란구슬이 먼저 움직였기 때문에 다시 계산한 좌표에 파란구슬이 존재하는지 체크해야함
            // 만약에 파란구슬이 다시 계산한 좌표에 존재하면 동일한 좌표에 같이 존재할 수 없으므로 좌표 다시 계산
            if (moveR == tempBoard.blueBeadRow && moveC == tempBoard.blueBeadColumn) {
                moveR = moveR - moveRow[i];
                moveC = moveC - moveColumn[i];
            }
            // 빨간구슬의 좌표를 변수에 저장
            tempBoard.redBeadRow = moveR;
            tempBoard.redBeadColumn = moveC;
        } else if (tempBoard.map[moveR][moveC].equals("O")) {
            // 빨간구슬이 다음에 이동할 좌표가 탈출구인 경우 현재까지 보드판을 움직인 횟수 리턴
            return tempBoard.count;
        }

        // 구슬들의 이동만 한 경우 0 리턴
        return 0;
    }

}
