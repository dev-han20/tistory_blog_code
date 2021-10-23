package study.blog.codingknowjam.algorithm.boj;

import java.util.Scanner;

// 백준온라인 저지 10972번 다음순열 문제 풀이
public class BOJ_10972 {
	private static int[] info;
	private static int[] arr;
	private static boolean[] chk;
	private static boolean print;
	private static boolean result;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		info = new int[N];
		arr = new int[N];
		chk = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			info[i] = scanner.nextInt();
		}
		
		DFS(0);

		if (!result) {
			System.out.println(-1);
		}

	}

	private static void DFS(int index) {
		if (index == arr.length) {
			boolean isEquals = true;
			for (int i = 0; i < arr.length; i++) {
				if (info[i] != arr[i]) {
					isEquals = false;
				}
			}
			if (isEquals) {
				print = true;
				return;
			}
			if (print) {
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + " ");
				}
				result = true;
				return;
			}
		}

		for (int i = 0; i < info.length; i++) {
			if (result) {
				return;
			}
			if (chk[i]) {
				continue;
			}

			arr[index] = i +1;
			chk[i] = true;
			DFS(index + 1);
			chk[i] = false;
		}
	}
}
