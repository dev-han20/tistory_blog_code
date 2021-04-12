package study.algorithm.boj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Boj7562 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(br.readLine());
        int[] moveX = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] moveY = {-1, 1, -1, 1, -2, 2, -2, 2};

        for(int i=0; i<testCaseCount; i++){
            Queue<Node> q = new LinkedList<>();
            int mapSize = Integer.parseInt(br.readLine());
            boolean[][] map = new boolean[mapSize][mapSize];
            String[] start = br.readLine().split(" ");
            String[] end = br.readLine().split(" ");

            Node startNode = new Node(Integer.parseInt(start[0]), Integer.parseInt(start[1]), 0);
            q.offer(startNode);
            map[startNode.x][startNode.y] = true;
            while(!q.isEmpty()){
                Node node = q.poll();
                int x = node.x;
                int y = node.y;
                if (x == Integer.parseInt(end[0]) && y == Integer.parseInt(end[1])) {
                    bw.write(String.valueOf(node.count));
                    bw.newLine();
                    break;
                }

                for (int j=0; j<moveX.length; j++){
                    int movex = node.x + moveX[j];
                    int movey = node.y + moveY[j];

                    if(movex < 0 || movex >= mapSize || movey < 0 || movey >= mapSize || map[movex][movey]){
                        continue;
                    }else{
                        q.offer(new Node(movex, movey, node.count + 1));
                        map[movex][movey] = true;
                    }

                }
            }
        }
        bw.flush();

    }

    static class Node{
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}
