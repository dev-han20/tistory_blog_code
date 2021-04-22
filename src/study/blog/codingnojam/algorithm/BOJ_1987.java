package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1987 {
	
	static int[] moveR = {1, -1, 0, 0};
	static int[] moveC = {0, 0, 1, -1};
	static int maxValue=1;
	static char[][] board; 
	static Map<Character, Integer> map = new HashMap<>();
	static int count=1;

	public static void main(String[] args) throws IOException {

		for (int i=65; i <=90; i++) {
			char key = (char)i;
			map.put(key, 1);
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] info = br.readLine().split(" ");
		board = new char[Integer.parseInt(info[0])][Integer.parseInt(info[1])];
		
		for (int i = 0; i < board.length; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = temp[j].charAt(0);
			}
		}
		
		dfs(0,0);
		System.out.println(maxValue);
	}
	
	static void dfs(int r, int c) {
		maxValue = Math.max(maxValue, count);
		map.computeIfPresent(board[r][c], (k,v) -> v-1);
		
		for(int i=0; i<moveR.length; i++) {
			int moveRow = r + moveR[i];
			int moveColumn = c + moveC[i];
			
			if(moveRow < 0 || moveRow >=board.length || moveColumn < 0 || moveColumn >= board[0].length) {
				continue;
			}else {
				int mapValue = map.get(board[moveRow][moveColumn]);
				if(mapValue == 0) {
					continue;
				}else {
					count++;
					dfs(moveRow, moveColumn);
					map.computeIfPresent(board[moveRow][moveColumn], (k,v) -> v+1);
					count--;
				}
			}
		}
	}

}
