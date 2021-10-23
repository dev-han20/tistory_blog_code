package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14890 {
	
	public static void main(String[] args) throws IOException {
		// 입력정보 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		
		// 지도로 사용 할 배열 초기화
		int[][] map = new int[Integer.parseInt(info[0])][Integer.parseInt(info[0])];
		// 지도를 세로로 지나가야하는 길도 계산해야하는데 편하게 계산하기위해 지도를 90도 돌렸습니다. 
		int[][] rotateMap = new int[Integer.parseInt(info[0])][Integer.parseInt(info[0])];
		// 경사로의 길이
		int L = Integer.parseInt(info[1]);
		
		// 지도 배열 초기화
		for (int i = 0; i < map.length; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		// 90도 돌린 지도 배열 초기화
		for (int i = 0; i < rotateMap.length; i++) {
			for (int j = 0; j < rotateMap[i].length; j++) {
				rotateMap[i][j] = map[Integer.parseInt(info[0])-1-j][0+i];
			}
		}
		
		// 지나갈 수 있는 길의 개수를 저장 할 변수
		int value = 0;
		// 가로로 지나가는 길 계산 (초기 지도)
		value = value + roadCount(L, map);
		// 가로로 지나가는 길 계산 (90도 돌린 지도)
		value = value + roadCount(L, rotateMap);
		
		// 출력
		System.out.println(value);
	}
	
	//가로로 지나가는 길을 계산하기 위한 메서드
	private static int roadCount(int slopeLength, int[][] map) {
		// 길 개수 저장 변수
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			// 경사로를 설치 했는지 여부 체크를 위한 배열
			boolean[] slopeChk = new boolean[map.length];
			// 현재 행이 지나갈 수 있는 길인지 체크를 위한 변수
			boolean roadChk = false;
			// 현재 칸과 다음 칸을 비교해가면서 진행할 것이기 때문에
			// 전체 반복횟수는 배열의 길이-1 입니다.
			for (int j = 0; j < map[i].length-1; j++) {
				// 현재 칸과 다음 칸의 높이가 동일 할 경우 현재 행은 지나갈 수 있음
				if(map[i][j] == map[i][j+1]) {
					roadChk = true;
				}
				// 현재 칸보다 다음 칸의 높이가 1만큼 큰 경우
				else if(map[i][j] - map[i][j+1] == -1) {
					// 경사로를 설치했을 때 영역을 벗어나지 않아야 함
					if(j - slopeLength+1 >= 0) {
						// for문 내부에서 이동 가능 여부 체크를 위한 임시변수
						boolean tempChk = false;
						// 경사로 설치를 위해 길이만큼 높이 체크
						for(int k=0; k<slopeLength; k++) {
							// 현재 칸에서 경사로 길이만큼 뒤로 이동하면서 경사로를 설치함
							// 이 때 해당 위치에 경사로는 설치되어 있지 않아야 함
							if(map[i][j-k] == map[i][j] && !slopeChk[j-k]) {
								slopeChk[j-k] = true;
							}else {
								// 경사로가 설치되있거나, 높이가 일정하지 않으면 지나갈 수 없음
								tempChk = true;
							}
						}
						// 임시체크변수가 true면 지나갈 수 없는 행이므로 다음 행 이동
						if(tempChk) {
							roadChk = false;
							break;
						}else {
							// false면 경사로 설치 완료
							roadChk = true;
						}
					}else {
						// 경사로를 설치했는데 영역을 벗어나면 현재 행은 못 지나가므로 다음 행으로 이동
						roadChk = false;
						break;
					}
				}
				// 현재 칸이 다음 칸보다 1만큼 더 큰 경우
				else if(map[i][j] - map[i][j+1] == 1) {
					// 경사로를 설치할 때 영역을 벗어나지 않아야 함
					if(j + slopeLength < map.length) {
						// 내부 for문에서 지나감 여부를 체크하기 위한 변수
						boolean tempChk = false;
						// 현재 칸이 더 크기 때문에 경사로를 다음 칸부터 설치해야 함
						// 그러므로 경사로가 설치 된 마지막 위치부터 다시 높이 비교를 시작해야 하므로
						// 경사로가 설치된 마지막 위치의 인덱스 저장을 위한 변수
						int tempIndex =0;
						// 경사로 설치를 위해 길이만큼 높이 체크
						for(int k=1; k<=slopeLength; k++) {
							// 1칸씩 뒤로 이동하면서 높이가 같은지 체크
							if(map[i][j+k] == map[i][j+1]) {
								// 높이가 같으면 경사로 설치
								slopeChk[j+k] = true;
								// 설치한 경사로의 마지막 위치 인덱스 저장
								tempIndex = j+k;
							}else {
								// 높이가 같지 않으면 경사로를 설치할 수 없으므로 못 지나감
								tempChk = true;
							}
						}
						// 임시체크변수가 true면 현재 행은 못 지나가므로 다음 행 이동
						if(tempChk) {
							roadChk = false;
							break;
						}else {
							// false면 경사로 설치 완료
							roadChk = true;
							// 경사로가 설치된 마지막 위치부터 다시 높이 비교를 시작하기 위해
							// 인덱스 변경 (-1을 해준 이유는 for문이 반복될 때 j+1이 되기 때문)
							j = tempIndex-1;
						}
					}else {
						// 경사로를 설치했더니 영역을 벗어나면 현재 행은 못 지나감, 다음 행으로 이동
						roadChk = false;
						break;
					}
				}else {
					// 현재 칸과 다음 칸의 차이가 -1, 0, 1이 아닌 경우 지나갈 수 없으므로 다음 행으로 이동
					roadChk = false;
					break;
				}
			}
			// 현재 행이 지나갈 수 있는 길이라면 카운트변수 1 증가
			if(roadChk) {
				count++;
			}
		}
		return count;
	}
}
