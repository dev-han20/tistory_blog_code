package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

// 백준온라인저지 3190번 뱀문제 Java 문제풀이
public class BOJ_3190 {

    public static void main(String[] args) throws IOException {

        // 입력을 받기 위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드판의 사이즈 받기
        int boardSize = Integer.parseInt(br.readLine());

        // 보드판을 표현할 배열 생성
        String[][] board = new String[boardSize][boardSize];

        // 보드판 배열이 String타입이므로 초기값인 null을 제거하기 위해 빈문자열로 초기화
        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(board[i], "");
        }

        // 사과 개수 받기
        int appleCount = Integer.parseInt(br.readLine());

        // 사과들의 위치정보를 받아서 보드판 배열에 저장
        for (int i = 0; i < appleCount; i++) {
            String[] appleLocation = br.readLine().split(" ");
            int row = Integer.parseInt(appleLocation[0]) -1 ;
            int col = Integer.parseInt(appleLocation[1]) -1 ;
            board[row][col] = "apple";
        }

        // 방향전환 명령 개수 받기
        int commandCount = Integer.parseInt(br.readLine());

        // 방향전환 명령을 저장 할 List생성 후 List에 명령 저장
        LinkedList<String[]> commands = new LinkedList<>();
        for (int i = 0; i < commandCount; i++) {
            commands.add(br.readLine().split(" "));
        }

        // 시간을 표현할 변수
        int time = 0;

        // 보드판 배열에서의 상하좌우 움직임에 사용할 배열
        int[] moveR = {0, 1, 0, -1};
        int[] moveC = {1, 0, -1, 0};

        // 뱀의 머리 위치를 저장 할 변수
        Location head = new Location(0, 0, 0);

        // 뱀의 꼬리 위치를 저장 할 변수
        Location tail = new Location(0, 0, 0);

        // 꼬리가 방향전환을 할 시점을 저장 한 List 생성
        LinkedList<Location> tailDirections = new LinkedList<>();

        // 뱀의 처음 시작위치 X로 초기화 (뱀의 이동경로는 모두 X로 표기할 예정)
        board[0][0] = "X";

        // 게임 시작
        while (true) {
            // 시간 1초 증가
            time++;

            // 뱀이 사과를 먹었는지를 표현할 변수
            boolean appleEat = false;

            // 현재 뱀이 바라보고 있는 방향으로 1칸 이동한 위치 계산
            head.row = head.row + moveR[head.direction];
            head.column = head.column + moveC[head.direction];

            // 계산된 위치가 보드판을 벗어나거나 뱀의 몸통에 닿았는지 체크 후 해당하면 게임 종료
            if (head.row >= boardSize || head.row < 0
                    || head.column >= boardSize || head.column < 0
                    || board[head.row][head.column].equals("X")) {
                break;
            }

            // 계산된 위치에 사과가 있다면 사과를 먹은 것을 표현하기 위해 true로 값 변경
            if (board[head.row][head.column].equals("apple")) {
                appleEat = true;
            }

            // 계산된 위치로 머리 1칸 이동
            board[head.row][head.column] = "X";

            // 방향전환 명령을 저장한 List가 비어있지 않으면
            if (commands.size() != 0) {
                // 제일 처음에 받은 명령 꺼내기
                String[] command = commands.getFirst();

                // 명령을 실행할 시간(초)가 현재 시간(초)와 같다면
                if (command[0].equals(String.valueOf(time))) {
                    // 명령이 시계방향 회전이라면
                    if (command[1].equals("D")) {
                        // 뱀의 머리가 바라보는 방향 시계방향으로 90도 회전
                        head.direction = head.direction + 1 == 4 ? 0 : head.direction + 1;
                    }
                    // 명령이 반시계방향 회전이라면
                    else {
                        // 뱀의 머리가 바라보는 방향 반시계방향으로 90도 회전
                        head.direction = head.direction - 1 == -1 ? 3 : head.direction - 1;
                    }

                    // 머리가 회전한 위치를 추후에 꼬리가 따라와서 그 해당 위치에서 방향전환을 해야하므로
                    // 해당 위치를 꼬리가 탐색할 방향전환 List에 추가
                    tailDirections.add(new Location(head.row, head.column, head.direction));

                    // 현재 수행한 방향전환 명령을 List에서 제거
                    commands.removeFirst();
                }
            }

            // 뱀이 사과를 먹었으면 꼬리는 가만히 있어야하므로, 사과를 먹지 않았을 때만 로직수행
            if (!appleEat) {

                // 현재 보드판에서 꼬리가 위치한 곳 빈문자열로 값 변경
                board[tail.row][tail.column] = "";

                // 꼬리가 바라보는 방향으로 꼬리를 1칸 이동
                tail.row = tail.row + moveR[tail.direction];
                tail.column = tail.column + moveC[tail.direction];

                // 머리가 방향전환을 한적이 있다면
                if (tailDirections.size() != 0) {
                    // 시간순서대로 List에 저장되므로 List에서 제일 앞의 값 가져오기
                    Location temp = tailDirections.getFirst();

                    // 머리가 방향전환을 한 위치가 현재 꼬리가 이동한 위치와 같다면
                    if (tail.row == temp.row && tail.column == temp.column) {
                        // 꼬리도 머리와 같은 방향으로 방향회전
                        tail.direction = temp.direction;
                        // 방향전환을 했으므로 List에서 해당 명령 제거
                        tailDirections.removeFirst();
                    }
                }
            }
        }

        // 게임이 종료되었을 때의 시간(초) 출력
        System.out.println(time);
    }

    // 머리와 꼬리의 위치를 표현할 클래스
    static class Location {
        // 행
        int row;
        // 열
        int column;
        // 현재 바라보는 방향
        int direction;

        public Location(int row, int column, int direction) {
            this.row = row;
            this.column = column;
            this.direction = direction;
        }
    }
}
