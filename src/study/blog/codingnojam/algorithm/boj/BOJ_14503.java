package study.blog.codingnojam.algorithm.boj;

import java.io.*;

public class BOJ_14503 {

	public static void main(String[] args) throws IOException {
		// 입력을 받기 위한 객체
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		//11 ~20까지는 문제풀이를 위한 입력정보 초기화입니다. 
		String[] mapSize = br.readLine().split(" ");
		int[][] map = new int[Integer.parseInt(mapSize[0])][Integer.parseInt(mapSize[1])];
		String[] info = br.readLine().split(" ");
		for (int i = 0; i < map.length; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}

		// 로봇 객체 생성 (초기 위치와 바라보는 방향 초기화)
		Robot robot = new Robot(Integer.parseInt(info[0]), 
				Integer.parseInt(info[1]), Integer.parseInt(info[2]));
		
		// 청소영역 카운트변수
		int count=0;
		
		// 바라보는 방향으로 한칸 씩 이동하므로 방향 값을 배열의 인덱스로 사용해서 계산
		// 예를들어 북쪽이 0이므로, moveR[0], moveC[0]을 로봇의 위치에 더하면 북쪽으로 1칸 이동
		int[] moveR = {-1, 0, 1, 0};
		int[] moveC = {0, 1, 0, -1};
		
		// 문제에서 뒤로 이동하는 경우도 있으므로 위의 배열과 반대의 값을 가지도록 함
		// 바라보는 방향의 반대편으로 한칸 씩 이동하므로 방향 값을 배열의 인덱스로 사용해서 계산
		// 예를들어 북쪽이 0이므로, moveR[0], moveC[0]을 로봇의 위치에 더하면 서쪽으로 1칸 이동
		int[] backMoveR = {1, 0, -1, 0};
		int[] backMoveC = {0, -1, 0, 1};
		
		// 로봇청소기 동작 여부
		boolean power = true;
		
		// 뒤로 이동해야 하는지 여부
		boolean back = false;
		
		// 청소기 ON
		while(power) {
			
			// 앞으로 전진한 경우에만 청소
			if(!back) {
				// 청소하면 map의 값을 2로 바꿈
				map[robot.r][robot.c] = 2;
				count ++;
			}
			
			// 동서남북 총 4개의 방향 체크
			for(int i=0; i<4; i++) {
				// 현재 바라보는 방향의 왼쪽방향 값 계산
				int leftD = robot.direction-1 < 0 ? 3 : robot.direction-1;
				// 계산된 방향으로 이동 했을 때의 좌표 값 계산
				int leftR = robot.r + moveR[leftD];
				int leftC = robot.c + moveC[leftD];

				// 이동해야 할 위치가 청소를 했거나 혹은 벽인지 체크
				if(map[leftR][leftC]==2 || map[leftR][leftC]==1) {
					// 청소했거나 벽이면 방향만 왼쪽으로 회전, 전진하지는 않음
					robot.direction = leftD;
					// 뒤로 이동 (i가 3일때만 true로 해줘도 됩니다.)
					back = true;
					continue;
				}else { 
					// 왼쪽으로 회전
					robot.direction = leftD;
					
					// 앞으로 1칸 전진
					robot.r = leftR;
					robot.c = leftC;
					
					// 뒤로 이동하지 않음
					back = false;
					break;
				}
			}
			
			// 뒤로 이동
			if(back) {
				// 현재 바라보는 방향 뒤로 1칸 이동을 위해 좌표 값 계산
				int backR = robot.r + backMoveR[robot.direction];
				int backC = robot.c + backMoveC[robot.direction];
				
				// 뒤로 이동해야 하는 위치에 벽이 있는지 체크
				if(map[backR][backC]==1) {
					// 벽이 있으면 로봇 OFF
					power = false;
					back = false;
				}else {
					// 벽이 없으면 뒤로 1칸 이동
					robot.r = backR;
					robot.c = backC;
				}
			}
		}
		// 청소 횟수 출력
		System.out.println(count);
	}
	
	// main 메서드 안에서 사용할 거라 static
	static class Robot{
		int r;				// 로봇의 위치한 행
		int c;				// 로봇의 위치한 열
		int direction;		// 로봇이 바라보는 방향

		Robot(int r, int c, int direction) {
			this.r = r;
			this.c = c;
			this.direction = direction;
		}
	}
}
