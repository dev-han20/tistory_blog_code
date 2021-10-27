package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14500 {
	private static int result = 0;
	private static int otherResult = 0;
	private static int R = 0;
	private static int C = 0;
	private static int[][] map;
	private static int[] arr = new int[6];

	public static void main(String[] args) throws IOException {
		// 입력을 빋는 객체
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 행과 열 정보 입력받고 2차원 배열 생성
		String[] NM = br.readLine().split(" ");
		R = Integer.parseInt(NM[0]);
		C = Integer.parseInt(NM[1]);
		map = new int[R][C];

		// 2차원 배열 맵 초기화
		initMap(br);

		// 행1 열4 형태로 구성된 테트로미노 값 연산
		R1C4();
		// 행4 열1 형태로 구성된 테트로미노 값 연산
		R4C1();
		// 행2 열2 형태로 구성된 테트로미노 값 연산
		R2C2();
		// 행2 열3 형태로 구성된 테트로미노 값 연산
		R2C3();
		// 행3 열2 형태로 구성된 테트로미노 값 연산
		R3C2();

		// 결과값 출력
		System.out.println(result);
	}

	/**
	 * 2차원 배열 초기화
	 */
	private static void initMap(BufferedReader br) throws IOException {
		for (int i = 0; i < R; i++) {
			String[] tempString = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(tempString[j]);
			}
		}
	}

	/**
	 * 결과 값 갱신 메서드
	 * otherResult와 result중 큰 값으로 갱신
	 */
	private static void updateResult(int otherResult) {
		result = Math.max(result, otherResult);
	}

	/**
	 * 파라미터 인덱스를 제외한 배열의 합 구하기
	 * 6개의 칸을 가진 테트로미노들에서 사용
	 */
	private static int getSumTetromino(int firstIndex, int secondIndex, int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (firstIndex == i || secondIndex == i) {
				continue;
			}
			sum += arr[i];
		}
		return sum;
	}

	/**
	 * □□□□ 구하기
	 */
	private static void R1C4() {
		for (int i = 0; i < R; i++) {
			int lt = 0;
			otherResult = map[i][0] + map[i][1] + map[i][2] + map[i][3];
			updateResult(otherResult);
			for (int rt = 4; rt < C; rt++) {
				otherResult = otherResult + map[i][rt] - map[i][lt];
				updateResult(otherResult);
				lt++;
			}
		}
	}

	/**
	 * □ 구하기
	 * □
	 * □
	 * □
	 */
	private static void R4C1() {
		for (int i = 0; i < C; i++) {
			int lt = 0;
			otherResult = map[0][i] + map[1][i] + map[2][i] + map[3][i];
			updateResult(otherResult);
			for (int rt = 4; rt < R; rt++) {
				otherResult = otherResult + map[rt][i] - map[lt][i];
				updateResult(otherResult);
				lt++;
			}
		}
	}

	/**
	 * □□ 구하기
	 * □□
	 */
	private static void R2C2() {
		for (int i = 0; i < R - 1; i++) {
			int lt = 0;
			otherResult = map[i][0] + map[i][1] + map[i + 1][0] + map[i + 1][1];
			updateResult(otherResult);
			for (int rt = 2; rt < C; rt++) {
				otherResult = otherResult + map[i][rt] + map[i + 1][rt]
					- map[i][lt] - map[i + 1][lt];
				updateResult(otherResult);
				lt++;
			}
		}
	}

	/**
	 * □□□ 에 포함되는 테트로미노들 구하기
	 * □□□
	 */
	private static void R2C3() {
		for (int i = 0; i < R - 1; i++) {
			for (int j = 0; j < C - 2; j++) {
				arr[0] = map[i][j];
				arr[1] = map[i][j + 1];
				arr[2] = map[i][j + 2];
				arr[3] = map[i + 1][j];
				arr[4] = map[i + 1][j + 1];
				arr[5] = map[i + 1][j + 2];

				otherResult = getSumTetromino(0, 2, arr);    //  □
				updateResult(otherResult);                              		// □□□

				otherResult = getSumTetromino(3, 5, arr);    //  □
				updateResult(otherResult);                              		// □□□

				otherResult = getSumTetromino(2, 3, arr);    // □□
				updateResult(otherResult);                              		//  □□

				otherResult = getSumTetromino(0, 5, arr);    //  □□
				updateResult(otherResult);                              		// □□

				otherResult = getSumTetromino(0, 1, arr);    //   □
				updateResult(otherResult);                              		// □□□

				otherResult = getSumTetromino(1, 2, arr);    // □
				updateResult(otherResult);                              		// □□□

				otherResult = getSumTetromino(4, 5, arr);    // □□□
				updateResult(otherResult);                              		// □

				otherResult = getSumTetromino(3, 4, arr);    // □□□
				updateResult(otherResult);                              		//   □
			}
		}
	}

	/**
	 * □□ 에 포함되는 테트로미노들 구하기
	 * □□
	 * □□
	 */
	private static void R3C2() {
		for (int i = 0; i < R - 2; i++) {
			for (int j = 0; j < C - 1; j++) {
				arr[0] = map[i][j];
				arr[1] = map[i][j+1];
				arr[2] = map[i+1][j];
				arr[3] = map[i+1][j+1];
				arr[4] = map[i+2][j];
				arr[5] = map[i+2][j+1];

				otherResult = getSumTetromino(0, 4, arr);    //  □
				updateResult(otherResult);                              		// □□
																				//  □

				otherResult = getSumTetromino(1, 5, arr);    // □
				updateResult(otherResult);                              		// □□
																				// □

				otherResult = getSumTetromino(1, 4, arr);    // □
				updateResult(otherResult);                              		// □□
																				//  □

				otherResult = getSumTetromino(0, 5, arr);    //  □
				updateResult(otherResult);                              		// □□
																				// □

				otherResult = getSumTetromino(0, 2, arr);    //  □
				updateResult(otherResult);                              		//  □
																				// □□

				otherResult = getSumTetromino(1, 3, arr);    // □
				updateResult(otherResult);                              		// □
																				// □□

				otherResult = getSumTetromino(2, 4, arr);    // □□
				updateResult(otherResult);                              		//  □
																				//  □

				otherResult = getSumTetromino(3, 5, arr);    // □□
				updateResult(otherResult);                              		// □
																				// □
			}
		}
	}
}
