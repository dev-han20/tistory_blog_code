package study.blog.codingnojam.algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static int[][] map = new int[7][7];

	public String[] solution(int[][] macaron) {

		for (int i = 0; i < macaron.length; i++) {
			input(macaron[i][0], macaron[i][1]);
			isBomb();
		}
		String[] answer = {};
		return answer;
	}

	private void isBomb() {
		Queue<Integer> q = new LinkedList<>();
		// int[][] chk
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				// map[i][j]
			}
		}

	}

	void input(int index, int color) {
		for (int i = 6; i > 0; i--) {
			if (map[i][index] == 0) {
				map[i][index] = color;
				break;
			}
		}
	}
}