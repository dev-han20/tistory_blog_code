package study.blog.codingnojam.algorithm.boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2309 {

	static int[] arr = new int[2];
	static int[] mans = new int[9];
	static boolean[] chk = new boolean[9];
	static int sum = 0;
	static boolean end = false;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			mans[i] = scanner.nextInt();
			sum += mans[i];
		}

		DFS(0);

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < mans.length; i++) {
			boolean flag = false;
			for (int j = 0; j < arr.length; j++) {
				if (mans[i] == arr[j]) {
					flag = true;
				}
			}
			if (!flag) {
				result.add(mans[i]);
			}
		}
		Collections.sort(result);
		for (Integer i : result) {
			System.out.println(i);
		}
	}

	public static void DFS(int index) {

		if (index == 2) {
			int tempSum = sum;
			for (int i = 0; i < arr.length; i++) {
				tempSum -= arr[i];

			}
			if (tempSum == 100) {
				end = true;
			}
			return;
		} else {
			for (int i = 0; i < mans.length; i++) {
				if (chk[i]) {
					continue;
				}
				arr[index] = mans[i];
				chk[i] = true;
				DFS(index + 1);
				if (end) {
					return;
				}
				chk[i] = false;
			}

		}

	}

}
