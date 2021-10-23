package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class 연속된_자연수의_합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int lt = 1;
		int sum = 1;
		int result = 0;
		for (int rt = 2; rt <= N / 2 + 2; rt++) {
			while (sum > N) {
				sum -= lt;
				lt++;
			}
			if (sum == N) {
				result++;
			}
			sum += rt;
		}
		System.out.println(result);
	}
}
