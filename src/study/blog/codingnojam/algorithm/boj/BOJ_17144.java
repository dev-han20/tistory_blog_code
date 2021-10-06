package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17144 {
	private static int R, C, T;
	private static int[][] room;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static ArrayList<AirCleaner> airCleaners = new ArrayList<>();
	private static Queue<Dust> dustQueue = new LinkedList<>();
	private static int[] moveRow = {1, 0, -1, 0};
	private static int[] moveCol = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		init();
		while (T > 0) {
			checkDust();
			diffuseDust();
			operateAirCleaner("counterclockwise");
			operateAirCleaner("clockwise");
			T--;
		}

		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += room[i][j] == -1 ? 0 : room[i][j];
			}
		}
		System.out.println(result);
	}

	private static void operateAirCleaner(String direction) {
		int index = direction.equals("clockwise") ? 1 : 0;
		int mr = direction.equals("clockwise") ? 1 : -1;
		AirCleaner airCleaner = airCleaners.get(index);
		int cr = airCleaner.row + mr;
		int cc = airCleaner.col;
		room[cr][cc] = 0;
		int rowMaxRange = direction.equals("clockwise") ? R : airCleaner.row + 1;
		int rowMinRange = direction.equals("clockwise") ? airCleaner.row : 0;

		for (int i = 0; i < 4; i++) {
			while (true) {
				int nr = cr + (moveRow[i] * mr);
				int nc = cc + moveCol[i];
				if (nr >= rowMaxRange || nr < rowMinRange || nc >= C || nc < 0) {
					break;
				}
				room[cr][cc] = room[nr][nc] == -1 ? 0 : room[nr][nc];
				cr = nr;
				cc = nc;
			}
		}
	}

	private static void diffuseDust() {
		while (!dustQueue.isEmpty()) {
			Dust dust = dustQueue.poll();
			int sum = 0;
			for (int i = 0; i < 4; i++) {
				int nr = dust.row + moveRow[i];
				int nc = dust.col + moveCol[i];
				if (nr >= R || nr < 0 || nc >= C || nc < 0 || room[nr][nc] == -1) {
					continue;
				}
				room[nr][nc] += dust.quantity / 5;
				sum += dust.quantity / 5;
			}
			room[dust.row][dust.col] -= sum;
		}
	}

	private static void init() throws IOException {
		String[] info = reader.readLine().split(" ");
		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		T = Integer.parseInt(info[2]);
		room = new int[R][C];

		for (int i = 0; i < R; i++) {
			String[] rowInfo = reader.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(rowInfo[j]);
				checkAirCleaner(room[i][j], i, j);
			}
		}
	}

	private static void checkDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {
					dustQueue.offer(new Dust(i, j, room[i][j]));
				}
			}
		}
	}

	private static void checkAirCleaner(int value, int row, int col) {
		if (value == -1) {
			airCleaners.add(new AirCleaner(row, col));
		}
	}

	private static class AirCleaner {
		int row;
		int col;

		public AirCleaner(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static class Dust {
		int row;
		int col;
		int quantity;

		public Dust(int row, int col, int quantity) {
			this.row = row;
			this.col = col;
			this.quantity = quantity;
		}
	}
}
