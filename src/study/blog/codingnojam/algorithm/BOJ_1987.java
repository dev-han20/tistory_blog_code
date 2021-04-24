package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1987 {
	// 상하좌우 좌표 계산을 위해 사용할 배열
	static int[] moveR = {1, -1, 0, 0};
	static int[] moveC = {0, 0, 1, -1};
	// 결과 값 계산을 위한 카운트 변수
	static int maxValue=1;
	static int count=1;
	// 보드
	static char[][] board; 
	// 중복 알파벳 체크를 위해 사용
	static Map<Character, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		// 각 알파벳을 키로 값은 1로 초기화
		for (int i=65; i <=90; i++) {
			char key = (char)i;
			map.put(key, 1);
		}

		// 입력 정보 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		board = new char[Integer.parseInt(info[0])][Integer.parseInt(info[1])];
		for (int i = 0; i < board.length; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = temp[j].charAt(0);
			}
		}
		
		// dfs 수행
		dfs(0,0);
		// 결과 값 출력
		System.out.println(maxValue);
	}
	
	// r과 c는 각각 행과열을 의미
	static void dfs(int r, int c) {
		// 최대 값 갱신
		maxValue = Math.max(maxValue, count);
		// 현재 좌표의 알파벳의 값을 0으로수정
		// 알파벳의 값이 0이면 한번 지난 칸, 1이면 아직 지나지 않은 칸
		map.computeIfPresent(board[r][c], (k,v) -> v-1);
		
		// 상하좌우 이동
		for(int i=0; i<moveR.length; i++) {
			
			// 이동할 좌표 값 계산
			int moveRow = r + moveR[i];
			int moveColumn = c + moveC[i];
			
			// 계산된 좌표의 값이 영역을 벗어나면 다시 계산
			if(moveRow < 0 || moveRow >=board.length || moveColumn < 0 || moveColumn >= board[0].length) {
				continue;
			}else {
				// 벗어나지 않으면 해당 좌표에 있는 알파벳의 값을 확인
				int mapValue = map.get(board[moveRow][moveColumn]);
				if(mapValue == 0) {
					// 값이 0이면 이미 지난 칸이므로 이동할 좌표 다시계산
					continue;
				}else {
					// 값이 0이 아니면(1이면) 이동칸수 1증가
					count++;
					// 해당 좌표 값으로 이동 후 다시 dfs메서드 호출 (재귀)
					dfs(moveRow, moveColumn);
					// dfs 메서드가 종료되면 이전에 방문했던 좌표를 다른 위치에서 재방문 할 수도 있으므로 알파벳의 값 증가
					// 재 방문를 못하게 방문처리를 하게 되면 모든 경우의 수를 탐색 할 수 없습니다.
					map.computeIfPresent(board[moveRow][moveColumn], (k,v) -> v+1);
					// dfs메서드가 종료되면 그 이전 좌표로 다시 돌아온것이기 때문에 이동칸수 1감소
					count--;
				}
			}
		}
	}

}
