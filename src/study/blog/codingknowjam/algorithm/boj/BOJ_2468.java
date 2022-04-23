package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2468 {
	private static final int FLOODING = 2; // 홍수남
	private static final int VISITED = 1; // 방문함
	private static final int NON_FLOODING = 0; // 홍수나지 않고 방문하지 않음
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Set<Integer> heightsOfRain = new TreeSet<>(); // 내리는 비의 높이들
	private static int maxSafeZone = 1; // 최대 안전영역 개수
	private static int[][] lands; // 땅을 표현할 2차원 배열
	private static int[][] floodedLands; // 홍수난 땅을 표현할 2차원 배열
	private static int[] moveRow = {-1, 0, 1, 0}; // 상하좌우 좌표 이동에 사용
	private static int[] moveColumn = {0, 1, 0, -1}; // 상하좌우 좌표 이동에 사용

	public static void main(String[] args) throws IOException {
		// 전체 땅의 가로 세로 크기를 받아서 2차원 배열로 생성
		int N = Integer.valueOf(reader.readLine());
		lands = new int[N][N];
		floodedLands = new int[N][N];
		// 문제에서 주어진 높이로 땅의 값 초기화
		initLands(N);
		// 내리는 비의 높이마다 안전영역 확인
		for (Integer heightOfRain : heightsOfRain) {
			// 비가 내림
			doFlooding(heightOfRain);
			// 최대 안전영역 개수 확인
			maxSafeZone = Math.max(maxSafeZone, countSafeZone());
		}
		// 값 출력
		System.out.println(maxSafeZone);
	}

	/**
	 * 현재 내린 비의 높이(강수량)에서 안전한 영역을 계산
	 * @return 안전한 영역의 개수를 리턴
	 */
	private static int countSafeZone() {
		int numberOfSafeZone = 0; // 안전한 영역을 저장할 변수
		for (int i = 0; i < floodedLands.length; i++) {
			for (int j = 0; j < floodedLands[i].length; j++) {
				// 땅이 홍수났거나, 이전에 방문했다면 다음 땅으로 이동
				if (isFlooding(i, j) || isVisited(i, j)) {
					continue;
				}
				// BFS에 사용할 큐
				Queue<LandLocation> safeZones = new LinkedList<>();
				// 안전한 땅을 큐에 넣고 방문처리
				safeZones.offer(new LandLocation(i, j));
				floodedLands[i][j] = VISITED;
				// 큐가 비어있지 않으면
				while (!safeZones.isEmpty()) {
					int numberOfRepeat = safeZones.size();
					for (int k = 0; k < numberOfRepeat; k++) { // BFS의 level을 구분하기 위해 현재 큐의 size만큼 반복문 수행
						LandLocation currentLocation = safeZones.poll(); // 큐에서 안전한 땅의 위치 꺼냄
						// 상우하좌 순으로 이동해가며 안전한 영역인지 확인
						for (int m = 0; m < 4; m++) {
							int nextRowIndex = currentLocation.rowIndex + moveRow[m];
							int nextColumnIndex = currentLocation.columnIndex + moveColumn[m];
							// 안전한 영역이면 큐에 넣고 방문처리
							if (isSafeZone(nextRowIndex, nextColumnIndex)) {
								safeZones.offer(new LandLocation(nextRowIndex, nextColumnIndex));
								floodedLands[nextRowIndex][nextColumnIndex] = VISITED;
							}
						}
					}
				}
				numberOfSafeZone++; // 안전한 영역 개수 증가
			}
		}
		return numberOfSafeZone;
	}

	/**
	 * BFS순회 과정에서 안전한 영역에 해당하는지 확인
	 * @param rowIndex : 땅의 행
	 * @param columnIndex : 땅의 열
	 * @return true(안전), false(안전하지 않음)
	 */
	private static boolean isSafeZone(int rowIndex, int columnIndex) {
		return rowIndex >= 0 && rowIndex < floodedLands.length
			&& columnIndex >= 0 && columnIndex < floodedLands.length
			&& isNonFlooding(rowIndex, columnIndex);
	}

	/**
	 * 땅이 물에 잠기지 않았는가?
	 */
	private static boolean isNonFlooding(int rowIndex, int columnIndex) {
		return floodedLands[rowIndex][columnIndex] == NON_FLOODING;
	}

	/**
	 * BFS순회중 해당 땅에 방문했는가?
	 */
	private static boolean isVisited(int rowIndex, int columnIndex) {
		return floodedLands[rowIndex][columnIndex] == VISITED;
	}

	/**
	 * 땅이 잠겼는가?
	 */
	private static boolean isFlooding(int rowIndex, int columnIndex) {
		return floodedLands[rowIndex][columnIndex] == FLOODING;
	}

	/**
	 * 비가 내려서 홍수나는 땅들에 대해서 확인
	 * @param heightOfRain : 내리는 비의 높이(강수량)
	 */
	private static void doFlooding(Integer heightOfRain) {
		for (int i = 0; i < lands.length; i++) {
			for (int j = 0; j < lands[i].length; j++) {
				if (lands[i][j] <= heightOfRain) {
					// 홍수 남
					floodedLands[i][j] = FLOODING;
				}
				// 이전에 방문한 땅이면 방문안한 안전한 땅으로 변경
				changeVisitedToNonFlooding(i, j);
			}
		}
	}

	/**
	 * 2차원 배열 하나로 모든 순회를 하므로, 이전에 순회했던 이력을 지우기 위해 사용 (방문함 -> 방문안함)
	 * @param rowIndex : 땅의 행
	 * @param columnIndex : 땅의 열
	 */
	private static void changeVisitedToNonFlooding(int rowIndex, int columnIndex) {
		if (floodedLands[rowIndex][columnIndex] == VISITED) {
			floodedLands[rowIndex][columnIndex] = NON_FLOODING;
		}
	}

	/**
	 * 땅의 높이 초기화
	 */
	private static void initLands(int N) throws IOException {
		for (int i = 0; i < N; i++) {
			Integer[] heights = Arrays.stream(reader.readLine().split(" "))
				.map(Integer::valueOf)
				.toArray(Integer[]::new);
			for (int j = 0; j < N; j++) {
				lands[i][j] = heights[j];
				heightsOfRain.add(heights[j]);
			}
		}
	}

	private static class LandLocation {
		int rowIndex;
		int columnIndex;

		public LandLocation(int rowIndex, int columnIndex) {
			this.rowIndex = rowIndex;
			this.columnIndex = columnIndex;
		}
	}
}
