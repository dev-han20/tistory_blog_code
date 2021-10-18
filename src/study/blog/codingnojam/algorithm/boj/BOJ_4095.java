package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 백준온라인저지 4095번 최대 정사각형 문제풀이
public class BOJ_4095 {
	public static void main(String[] args) throws IOException {
		// 입출력을 위한 객체
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 테스트케이스 만큼 반복
		while (true) {
			// 2차원 배열 행과 열 길이 받기
			String[] NM = br.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			int[][] map = new int[N][M];

			// 테스트케이스가 끝나면 반복문 빠져나오기
			if (N == 0 && M == 0) {
				break;
			}

			// 주어진 2차원 배열에 1이 있는지 확인을 위한 변수
			boolean oneCount = false;
			// map변수에 값을 채워나가면서 1이 있는지 체크
			for (int i = 0; i < N; i++) {
				String[] mapInfo = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(mapInfo[j]);
					if (map[i][j] == 1) {
						oneCount = true;
					}
				}
			}

			// 1이 있다면 최소 정사각형의 값은 1, 없다면 0
			// 테스트케이스 별로 결과값을 저장하기 위한 변수
			int result = oneCount ? 1 : 0;

			// 2차원 배열 순회하면서 DP를 통해 값 도출
			// DP 점화식 : map[i][j] = map[i-1][j-1], map[i-1][j], map[i][j-1] 셋 중의 최소 값 + 1
			for (int i = 1; i < N; i++) {
				for (int j = 1; j < M; j++) {
					if (map[i][j] == 1) {
						map[i][j] = getMinValue(map[i - 1][j - 1], map[i - 1][j], map[i][j - 1]);
						// 최대 정사각형의 길이를 result변수에 저장
						result = Math.max(result, map[i][j]);
					}
				}
			}
			// 출력을 위해 결과 값 저장
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		// 결과값 출력
		bw.flush();
	}

	/**
	 * 입력받은 3개의 변수 중 최솟값을 반환하는 메서드
	 */
	public static int getMinValue(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
}
