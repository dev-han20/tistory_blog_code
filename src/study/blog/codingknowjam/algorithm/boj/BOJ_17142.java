package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준온라인저지 17142번 연구소3 문제 풀이
public class BOJ_17142 {

	static int N;
	static int M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		map = new int[N][N];

		ArrayList<Virus> virusList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j++) {
				int objectNumber = Integer.parseInt(temp[j]);
				if (objectNumber == 2) {
					virusList.add(new Virus(i, j));
				}
				map[i][j] = objectNumber;
			}
		}









	}

	static class Virus {
		int row;
		int col;

		public Virus(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
