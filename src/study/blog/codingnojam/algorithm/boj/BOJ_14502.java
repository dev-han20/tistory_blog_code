package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {

	//전체 영역을 나타날 배열
	static int[][] map;
	// 기둥을 몇개 심었는지 체크 할 변수
	static int count = 0;
	// 현재 존재하는 바이러스들
	static List<Virus> vl = new ArrayList<>();
	// 바이러스 퍼지는 경로 계산을 위한 배열
	static int[] moveR = {1,-1,0,0};
	static int[] moveC = {0,0,1,-1};
	// 안전한 영역의 개수를 담을 변수
	static int maxValue = -9;
	
	public static void main(String[] args) throws IOException {
		// 문제를 풀기 위한 입력 값 받기 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] mapSize = br.readLine().split(" ");
		map = new int[Integer.parseInt(mapSize[0])][Integer.parseInt(mapSize[1])];
		for (int i = 0; i < map.length; i++) {
			String[] sTemp = br.readLine().split(" ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(sTemp[j]);
				if(map[i][j] == 2) {
					// 영역 값 초기화하면서 현재 바이러스들 위치 체크
					vl.add(new Virus(i, j));
				}
			}
		}
		// 완전탐색 실행
		combination();
		// 값 출력
		System.out.println(maxValue);		
	}
	
	// 조합(nCr)
	static void combination() {
		// 기둥이 3개 이상 심어졌을 경우
		if(count >=3) {
			// 바이러스가 퍼지는 경로를 계산하기 위해 기존영역과 동일한 새로운 영역 생성 및 초기화
			int[][] copyMap = new int[map.length][map[0].length];
			for (int i = 0; i < copyMap.length; i++) {
				for (int j = 0; j < copyMap[i].length; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			
			// BFS를 수행항 Queue 생성
			Queue<Virus> q = new LinkedList<>();
			// List에서 바이러스 객체 가져와서 큐에 넣기
			for (Virus virus : vl) {
				q.offer(virus);
			}
			
			// Queue가 빌 때 까지 반복
			while(!q.isEmpty()) {
				// Queue에서 바이러스 꺼내기
				Virus v = q.poll();
				// 현재 바이러스 위치 저장(행, 열)
				int r = v.row;
				int c = v.column;
				// 바이러스가 퍼져나갈 경로 계산 (상하좌우이므로 총 4번 반복)
				for (int i = 0; i < moveR.length; i++) {
					// 바이러스가 다음에 이동 할 좌표 계산
					int mr = r + moveR[i];
					int mc = c + moveC[i];
					// 다음에 이동 할 위치가 영역을 벗어나거나, 벽이거나, 이미 바이러스가 퍼진곳이라면 좌표 다시 계산
					if(mr < 0 || mr >= copyMap.length || mc < 0 || mc >= copyMap[0].length || copyMap[mr][mc] == 1 || copyMap[mr][mc] == 2) {
						continue;
					}else {
						// 영역을 벗어나지 않고, 빈 칸이면서, 바이러스가 퍼진 곳이 아니면 해당 위치에 바이러스 전파 및 Queue에 바이러스 넣기
						q.offer(new Virus(mr, mc));
						copyMap[mr][mc] = 2;
					}
				}
			}
			
			// 안전한 영역을 계산하기 위한 임시변수
			int temp = 0;
			// 안전한 영역 개수 계산
			for (int i = 0; i < copyMap.length; i++) {
				for (int j = 0; j < copyMap[i].length; j++) {
					if(copyMap[i][j] == 0) {
						temp++;
					}
				}
			}
			
			// 안전한 영역의 개수가 최대일 때 갱신
			maxValue = Math.max(temp, maxValue);
			
			// 종료
			return;
		}
		
		// 기둥이 2개 이하로 심어졌을 때 전체영역을 탐색하면서 빈 곳에 기둥 심기
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					count++;
					combination();
					count--;
					map[i][j] = 0;
				}
			}
		}
	}

	// 바이러스
	static class Virus {
		int row;	// 행
		int column;	// 렬

		// 생성자를 통해서 바이러스 위치 저장
		Virus(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
}
