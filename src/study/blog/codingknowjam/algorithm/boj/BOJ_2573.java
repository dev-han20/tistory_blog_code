package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2573 {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Integer[] numberOfRowAndColumn;
	private static int[][] icebergs;
	private static int[] moveRow = {-1, 1, 0, 0};
	private static int[] moveColumn = {0, 0, -1, 1};
	private static int[][] simulationIcebergs;
	private static int yearCount = 0;

	public static void main(String[] args) throws IOException {
		// 입력데이터 읽어서 문제 내용에 받게 빙산 초기화
		numberOfRowAndColumn = readLineToIntegerArray();
		icebergs = new int[numberOfRowAndColumn[0]][numberOfRowAndColumn[1]];
		for (int i = 0; i < icebergs.length; i++) {
			Integer[] heightOfIcebergs = readLineToIntegerArray();
			for (int j = 0; j < heightOfIcebergs.length; j++) {
				icebergs[i][j] = heightOfIcebergs[j];
			}
		}
		while (true) {
			// 기존의 빙산이 녹아서 바다가 되는 경우는 계산에 포함하면 안되므로 새롭게 시뮬레이션 할 빙산들 생성
			simulationIcebergs = createCloneIcebergs(icebergs);
			// 빙산 녹는 시뮬레이션
			meltSimulation();
			// 시뮬레이션 결과를 기존의 빙산에 반영
			clone(icebergs, simulationIcebergs);
			// 빙산이 몇 덩이인지 BFS알고리즘으로 계산
			Queue<Location> bfsQueue = new LinkedList<>();
			int countOfIcebergs = 0; // 빙산 덩이 수
			boolean isFinish = false; // 2개 이상으로 분리 되었는가?
			outerLoop:
			for (int i = 0; i < simulationIcebergs.length; i++) {
				for (int j = 0; j < simulationIcebergs[i].length; j++) {
					// 바다면 다음 위치로 이동
					if (isSea(simulationIcebergs, i, j)) {
						continue;
					}
					// 바다 아니고 빙산이면 큐에 넣고 방문처리
					// 그리고 덩이 수 증가
					if (isThereIcebergs(simulationIcebergs, i, j)) {
						bfsQueue.offer(new Location(i, j));
						simulationIcebergs[i][j] = 0;
						countOfIcebergs++;
					}
					// 덩이 수가 2개 이상이면 BFS더 탐색하지 않고 바로 반복문 탈출
					if (countOfIcebergs >= 2) {
						isFinish = true;
						break outerLoop;
					}
					// 큐에 빙산의 위치가 있다면
					while (!bfsQueue.isEmpty()) {
						Location icebergLocation = bfsQueue.poll();
						// 상하좌우 이동가능한 빙산이 있는지 확인하고, 있으면 큐에 넣고 방문처리
						for (int k = 0; k < 4; k++) {
							int nextRow = icebergLocation.row + moveRow[k];
							int nextColumn = icebergLocation.column + moveColumn[k];
							if (isInRange(simulationIcebergs, nextRow, nextColumn) && isThereIcebergs(
								simulationIcebergs, nextRow, nextColumn)) {
								bfsQueue.offer(new Location(nextRow, nextColumn));
								simulationIcebergs[nextRow][nextColumn] = 0;
							}
						}
					}
				}
			}
			// 1년 경과
			yearCount++;
			// BFS순회가 끝나고 덩이 수가 증가하지 않았다면 전부 다 녹은 상태
			if (countOfIcebergs == 0) {
				System.out.println(0);
				return;
			}
			// 덩이 수가 2개 이상인 경우 경과 년수 출력
			if (isFinish) {
				System.out.println(yearCount);
				return;
			}
		}
	}

	/**
	 * 입력데이터 읽어서 배열로 전환
	 *
	 * @return 입력데이터로 들어온 정수를 배열로 변환
	 * @throws IOException
	 */
	private static Integer[] readLineToIntegerArray() throws IOException {
		return Arrays.stream(reader.readLine().split(" "))
			.map(Integer::valueOf)
			.toArray(Integer[]::new);
	}

	/**
	 * 바다인가?
	 *
	 * @param rowIndex
	 * @param columnIndex
	 * @return
	 */
	private static boolean isSea(int[][] icebergs, int rowIndex, int columnIndex) {
		return icebergs[rowIndex][columnIndex] == 0;
	}

	/**
	 * BFS순회 전 시뮬레이션의 결과를 저장하기 위해 사용
	 */
	private static void clone(int[][] icebergs, int[][] simulationIcebergs) {
		for (int i = 0; i < icebergs.length; i++) {
			for (int j = 0; j < icebergs[i].length; j++) {
				icebergs[i][j] = simulationIcebergs[i][j];
			}
		}
	}

	/**
	 * 빙산 녹는 시뮬레이션
	 */
	private static void meltSimulation() {
		for (int i = 0; i < icebergs.length; i++) {
			for (int j = 0; j < icebergs[i].length; j++) {
				if (isThereIcebergs(simulationIcebergs, i, j)) {
					meltIceberg(i, j);
				}
			}
		}
	}

	/**
	 * 빙산이 있나요?
	 *
	 * @param rowIndex
	 * @param columnIndex
	 * @return true:존재함, false:다 녹음
	 */
	private static boolean isThereIcebergs(int[][] icebergs, int rowIndex, int columnIndex) {
		return icebergs[rowIndex][columnIndex] > 0;
	}

	/**
	 * 주변에 있는 바다의 개수만큼 빙산이 녹는다
	 *
	 * @param rowIndex
	 * @param columnIndex
	 */
	private static void meltIceberg(int rowIndex, int columnIndex) {
		int countOfSea = 0;
		for (int k = 0; k < 4; k++) {
			int nextRow = rowIndex + moveRow[k];
			int nextColumn = columnIndex + moveColumn[k];
			if (isInRangeAndSea(icebergs, nextRow, nextColumn)) {
				countOfSea++;
			}
		}
		int result = simulationIcebergs[rowIndex][columnIndex] - countOfSea;
		simulationIcebergs[rowIndex][columnIndex] = result < 0 ? 0 : result;
	}

	/**
	 * 주변에 바다가 있나요?
	 *
	 * @return true:바다있음, false:바다없음
	 */
	private static boolean isInRangeAndSea(int[][] icebergs, int rowIndex, int columnIndex) {
		return rowIndex >= 0 && rowIndex < icebergs.length && columnIndex >= 0 && columnIndex < icebergs[0].length
			&& isSea(icebergs, rowIndex, columnIndex);
	}

	/**
	 * 범위안에 있는가요??
	 *
	 * @param rowIndex
	 * @param columnIndex
	 * @return true:범위 안에 있음, false:범위를 벗어남
	 */
	private static boolean isInRange(int[][] icebergs, int rowIndex, int columnIndex) {
		return rowIndex >= 0 && rowIndex < icebergs.length && columnIndex >= 0 && columnIndex < icebergs[0].length;
	}

	/**
	 * 파라미터로 받은 2차원 배열과 똑같은 2차원 배열 신규로 생성
	 *
	 * @param icebergs : 복사 할 2차원 배열
	 * @return
	 */
	private static int[][] createCloneIcebergs(int[][] icebergs) {
		int[][] simulationIcebergs = new int[icebergs.length][icebergs[0].length];
		for (int i = 0; i < icebergs.length; i++) {
			for (int j = 0; j < icebergs[i].length; j++) {
				simulationIcebergs[i][j] = icebergs[i][j];
			}
		}
		return simulationIcebergs;
	}

	/**
	 * 빙산의 위치를 저장할 클래스
	 */
	private static class Location {
		int row; // 행
		int column; // 열

		public Location(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
}
