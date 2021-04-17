package study.blog.codingnojam;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7562 {

    public static void main(String[] args) throws IOException {
        // 입출력을 담당할 BufferedReader, BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트케이스 개수 저장
        int testCaseCount = Integer.parseInt(br.readLine());

        // 나이트이 이동 좌표를 계산하기 위한 배열
        int[] moveX = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] moveY = {-1, 1, -1, 1, -2, 2, -2, 2};

        // 테스트케이스 개수 만큼 반복
        for(int i=0; i<testCaseCount; i++){
            // BFS를 위해 사용할 Queue
            Queue<Node> q = new LinkedList<>();

            // 체스판의 크기 값 저장
            int mapSize = Integer.parseInt(br.readLine());

            // 나이트가 방문했는지 여부 체크를 위한 2차원 배열
            boolean[][] map = new boolean[mapSize][mapSize];

            // 나이트가 처음에 서있는 위치 저장
            String[] start = br.readLine().split(" ");

            // 나이트가 도착해야 할 위치 저장
            String[] end = br.readLine().split(" ");

            // 처음 시작 지점의 좌표와 이동횟수 정보를 가지는 Node객체 생성
            Node startNode = new Node(Integer.parseInt(start[0]), Integer.parseInt(start[1]), 0);

            // Queue에 시작지점의 객체 넣기
            q.offer(startNode);

            // 나이트가 서있는 시작지점 방문처리
            map[startNode.x][startNode.y] = true;

            // Queue가 빌 때가지 반복
            while(!q.isEmpty()){

                // Queue에서 Node 객체 꺼내기
                Node node = q.poll();

                // 꺼낸 Node의 현재 좌표 값 변수에 저장
                int x = node.x;
                int y = node.y;

                // Queue에서 꺼낸 Node의 현재 위치가 도착할 지점과 같다면, 현재까지 이동횟수 bw에 저장
                if (x == Integer.parseInt(end[0]) && y == Integer.parseInt(end[1])) {
                    bw.write(String.valueOf(node.count));
                    bw.newLine();
                    break;
                }

                // 다음 위치로 이동하기 위한 계산시작
                for (int j=0; j<moveX.length; j++){
                    // 현재 Node객체가 있는 좌표 값을 기준으로 다음 x,y좌표 계산
                    int movex = node.x + moveX[j];
                    int movey = node.y + moveY[j];

                    // 만약에 다음에 이동 할 x,y좌표가 체스판을 벗어나거나, 방문했던 적이 있다면 좌표값 다시 계산
                    if(movex < 0 || movex >= mapSize || movey < 0 || movey >= mapSize || map[movex][movey]){
                        continue;
                    }else{
                        // 아니라면 해당 좌표값의 정보를 가지는 객체 생성 (이동 횟수 + 1)
                        q.offer(new Node(movex, movey, node.count + 1));

                        // 다음에 이동 할 위치의 좌표값 방문처리
                        map[movex][movey] = true;
                    }
                }
            }
        }
        // 최소이동횟수 출력
        bw.flush();

    }

    static class Node{
        int x;          // x좌표
        int y;          // y좌표
        int count;      // 최소 이동횟수를 구할 변수

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}
