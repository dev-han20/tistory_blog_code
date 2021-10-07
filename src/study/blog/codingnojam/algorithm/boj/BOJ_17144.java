package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


// 백준온라인저지 17144번 미세먼지 안녕! 문제 풀이
public class BOJ_17144 {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static int R, C, T;		// 집의 전체 행,열, 먼지확산 시간
	private static int[][] house;	// 집
	private static ArrayList<AirCleaner> airCleaners = new ArrayList<>();	// 공기청정기 좌표 저장 리스트
	private static Queue<Dust> dusts = new LinkedList<>();	// 먼지 좌표 저장 큐
	private static int[] moveRow = {1, 0, -1, 0};		// 좌표계산에 사용할 배열
	private static int[] moveCol = {0, 1, 0, -1};		// 좌표계산에 사용할 배열

	public static void main(String[] args) throws IOException {
		init();		// 주어진 입력정보 받아서 변수들 초기화

		// 시간이 0초가 될때까지 반복
		while (T > 0) {
			// 집안의 먼지들 큐에 저장
			offerDust();
			// 먼지들 확산
			diffuseDust();
			// 공기청정기 반시계방향 동작
			operateAirCleaner("counterclockwise");
			// 공기청정기 시계방향 동작
			operateAirCleaner("clockwise");
			// 시간 감소
			T--;
		}

		// 0초가 되면 집안의 남아있는 먼지량 모두 더해서 출력
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += house[i][j] == -1 ? 0 : house[i][j];
			}
		}
		System.out.println(result);
	}

	/**
	 * 입력정보 받아서 초기화
	 */
	private static void init() throws IOException {
		String[] info = reader.readLine().split(" ");
		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		T = Integer.parseInt(info[2]);
		house = new int[R][C];

		for (int i = 0; i < R; i++) {
			String[] rowInfo = reader.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				house[i][j] = Integer.parseInt(rowInfo[j]);
				// 공기청정기 좌표 리스트에 저장
				addAirCleaner(house[i][j], i, j);
			}
		}
	}

	/**
	 * 공기청정기 리스트에 추가
	 */
	private static void addAirCleaner(int value, int row, int col) {
		if (value == -1) {
			airCleaners.add(new AirCleaner(row, col));
		}
	}

	/**
	 * 먼지 큐에 추가
	 */
	private static void offerDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (house[i][j] > 0) {
					dusts.offer(new Dust(i, j, house[i][j]));
				}
			}
		}
	}

	/**
	 * 먼지 확산
	 */
	private static void diffuseDust() {
		// 큐가 빌 때까지 반복
		while (!dusts.isEmpty()) {
			// 큐에 있는 먼지 꺼내기
			Dust dust = dusts.poll();
			// 먼지가 확산된 양을 저장할 변수
			int sum = 0;
			// 상하좌우 좌표 계산
			for (int i = 0; i < 4; i++) {
				int nr = dust.row + moveRow[i];
				int nc = dust.col + moveCol[i];
				// 집을 벗어나거나 공기청정기가 다음 좌표면 확산 안함
				if (nr >= R || nr < 0 || nc >= C || nc < 0 || house[nr][nc] == -1) {
					continue;
				}
				// 계산된 좌표로 먼지 확산 5분의 1만큼
				house[nr][nc] += dust.quantity / 5;
				// 확산된 먼지 양만큼 합산
				sum += dust.quantity / 5;
			}
			// 한개의 먼지가 확산이 끝나면 확산된 양만큼 감소
			house[dust.row][dust.col] -= sum;
		}
	}

	/**
	 * 공기청정기 작동
	 * @param direction 시계방향, 반시계방향
	 */
	private static void operateAirCleaner(String direction) {
		// 반시계방향이면 위의 공기정청기, 시계방향이면 아래 공기청정기 꺼냄
		int index = direction.equals("clockwise") ? 1 : 0;
		AirCleaner airCleaner = airCleaners.get(index);
		// 공기청정기가 동작하는 첫 좌표 계산을 위한 삼항연산자
		int mr = direction.equals("clockwise") ? 1 : -1;
		// 현재 좌표
		int cr = airCleaner.row + mr;
		int cc = airCleaner.col;
		// 처음 좌표 먼지 없애기
		house[cr][cc] = 0;
		// 공기청정기 기준으로 2개의 방향으로 먼지를 흡입하므로 범위 지정
		int rowMaxRange = direction.equals("clockwise") ? R : airCleaner.row + 1;
		int rowMinRange = direction.equals("clockwise") ? airCleaner.row : 0;

		// 바람이 순회하는 방향의 역으로 좌표계산하면서 먼지흡입
		for (int i = 0; i < 4; i++) {
			while (true) {
				// 다음 좌표 계산
				int nr = cr + (moveRow[i] * mr);
				int nc = cc + moveCol[i];
				// 주어진 범위르 벗어나면 먼지 흡입 방향 변경
				if (nr >= rowMaxRange || nr < rowMinRange || nc >= C || nc < 0) {
					break;
				}
				// 순회하다가 다시 공기청정기로 되돌아오면 먼지가 이동했으므로 0값으로 변경
				house[cr][cc] = house[nr][nc] == -1 ? 0 : house[nr][nc];
				// 이동한 좌표의 값을 현재 좌표값으로 변경
				cr = nr;
				cc = nc;
			}
		}
	}

	// 공기청정기
	private static class AirCleaner {
		int row; 	// 행
		int col;	// 열

		public AirCleaner(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	// 먼지
	private static class Dust {
		int row;		// 행
		int col;		// 열
		int quantity;	// 먼지량

		public Dust(int row, int col, int quantity) {
			this.row = row;
			this.col = col;
			this.quantity = quantity;
		}
	}
}
