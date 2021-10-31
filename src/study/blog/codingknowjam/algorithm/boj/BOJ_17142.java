package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준온라인저지 17142번 연구소3 문제 풀이
public class BOJ_17142 {

	static int N;
	static int M;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}






	}
}
