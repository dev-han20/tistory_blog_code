package study.blog.codingnojam.algorithm.ifrktw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 최대길이_연속부분수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] arr = new int[N];
		Queue<Integer> lts = new LinkedList<>();
		int length = 0;
		int result = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			if (arr[i] == 1) {
				length++;
				result = Math.max(result, length);
				continue;
			}
			if (K > 0) {
				K--;
				length++;
				lts.offer(i);
				result = Math.max(result, length);
			} else if (K == 0) {
				if (!lts.isEmpty()) {
					lts.offer(i);
					int lt = lts.poll();
					length = i - lt;
					result = Math.max(result, length);
				}
			}
		}
		System.out.println(result);
	}
}
