package study.blog.codingknowjam.algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

// 찾아라 프로그래밍 마에스터 -  게임 맵 최단거리
class Solution_게임맵최단거리 {
    public int solution(int[][] maps) {
        int answer = 0;

        int[] moveR = {-1, 1, 0, 0};
        int[] moveC = {0, 0, -1, 1};
        boolean[][] chk = new boolean[maps.length][maps[0].length];

        Queue<Location> q = new LinkedList<>();

        q.offer(new Location(0,0,1));
        chk[0][0] = true;

        while(!q.isEmpty()){
            Location now = q.poll();

            if(now.row == maps.length-1 && now.col == maps[0].length-1){
                return now.result;
            }

            for(int i=0; i < 4; i++){
                int nextR = now.row + moveR[i];
                int nextC = now.col + moveC[i];

                if(nextR >= maps.length || nextR < 0
                        || nextC < 0 || nextC >= maps[0].length
                        || chk[nextR][nextC] || maps[nextR][nextC] == 0){
                    continue;
                }else{
                    q.offer(new Location(nextR, nextC, now.result + 1));
                    chk[nextR][nextC] = true;

                }

            }

        }

        return -1;
    }

    class Location{
        int row;
        int col;
        int result;

        public Location (int row, int col, int result){
            this.row = row;
            this.col = col;
            this.result = result;
        }
    }
}