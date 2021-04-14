package study.algorithm.boj;

import java.io.*;

public class BOJ_14503 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] mapSize = br.readLine().split(" ");
		int[][] map = new int[Integer.parseInt(mapSize[0])][Integer.parseInt(mapSize[1])];
		String[] info = br.readLine().split(" ");
		
		Robot robot = new Robot(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
		for (int i = 0; i < map.length; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}

		int count=0;
		int[] moveR = {-1, 0, 1, 0};
		int[] moveC = {0, 1, 0, -1};
		
		int[] backMoveR = {1, 0, -1, 0};
		int[] backMoveC = {0, -1, 0, 1};
		
		boolean power = true;
		boolean back = false;
		
		while(power) {
			// 현재 위치 청소
			if(!back) {
				map[robot.r][robot.c] = 2;
				count ++;
			}
			
			for(int i=0; i<4; i++) {
				// 왼쪽방향으로 방향값, 좌표 계산
				int leftD = robot.direction-1 < 0 ? 3 : robot.direction-1;
				int leftR = robot.r + moveR[leftD];
				int leftC = robot.c + moveC[leftD];

				if(map[leftR][leftC]==2 || map[leftR][leftC]==1) {
					robot.direction = leftD;
					back = true;
					continue;
				}else {
					robot.direction = leftD;
					robot.r = leftR;
					robot.c = leftC;
					back = false;
					break;
				}
			}
			
			if(back) {
				int backR = robot.r + backMoveR[robot.direction];
				int backC = robot.c + backMoveC[robot.direction];
				if(map[backR][backC]==1) {
					power = false;
					back = false;
				}else {
					robot.r = backR;
					robot.c = backC;
				}
			}
		}
		
		System.out.println(count);
		
	}
	
	static class Robot{
		int r;
		int c;
		int direction;

		Robot(int r, int c, int direction) {
			this.r = r;
			this.c = c;
			this.direction = direction;
		}
	}
}
