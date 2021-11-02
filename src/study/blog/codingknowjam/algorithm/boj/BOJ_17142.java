package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

// 백준온라인저지 17142번 연구소3 문제 풀이
public class BOJ_17142 {

	static int N;
	static int M;
	static int blankCount = 0;
	static int[][] map;
	static int[][] visited;
	static int[] target;
	static int[] moveRow = {-1, 1, 0, 0};
	static int[] moveCol = {0, 0, -1, 1};
	static ArrayList<Virus> virusList = new ArrayList<>();
	static TreeSet<Integer> result = new TreeSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		map = new int[N][N];
		visited = new int[N][N];
		target = new int[M];

		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j++) {
				int objectNumber = Integer.parseInt(temp[j]);
				if (objectNumber == 2) {
					virusList.add(new Virus(i, j, 0));
				}
				if (objectNumber == 0) {
					blankCount++;
				}
				map[i][j] = objectNumber;
			}
		}
		if (blankCount == 0) {
			System.out.println(0);
			return;
		}

		DFS(0, 0);
		if (result.contains(-1)) {
			if (result.size() == 1) {
				System.out.println(-1);
				return;
			} else {
				result.remove(-1);
			}
		}
		System.out.println(result.first());
	}

	private static void DFS(int lev, int index) {
		if (lev == M) {
			int caseResult = 0;
			int caseBlankCount = blankCount;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = map[i][j];
				}
			}
			Queue<Virus> q = new LinkedList<>();
			for (int i = 0; i < target.length; i++) {
				Virus virus = virusList.get(target[i]);
				q.offer(new Virus(virus.row, virus.col, virus.timeCount));
			}
			queueLoopOut:
			while (!q.isEmpty()) {
				Virus virus = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = virus.row + moveRow[i];
					int nc = virus.col + moveCol[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == 1|| visited[nr][nc] == 3) {
						continue;
					}
					if(visited[nr][nc] == 0){
						caseBlankCount--;
					}
					q.offer(new Virus(nr, nc, virus.timeCount + 1));
					visited[nr][nc] = 3;
					caseResult = Math.max(caseResult, virus.timeCount + 1);
					if (caseBlankCount == 0) {
						break queueLoopOut;
					}
				}
			}
			if (caseBlankCount != 0) {
				caseResult = -1;
			}
			result.add(caseResult);
			return;
		}

		for (int i = index; i < virusList.size(); i++) {
			target[lev] = i;
			DFS(lev + 1, i + 1);
		}
	}

	static class Virus {
		int row;
		int col;
		int timeCount = 0;

		public Virus(int row, int col, int timeCount) {
			this.row = row;
			this.col = col;
			this.timeCount = timeCount;
		}
	}
}
