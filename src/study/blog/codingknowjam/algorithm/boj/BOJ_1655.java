package study.blog.codingknowjam.algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_1655 {
	private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());        // 최대힙
	private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();                                // 최소힙

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));            // 입력받기용
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            // 출력하기용

		// 정수의 총 개수
		int N = Integer.parseInt(br.readLine());

		// 정수의 개수만큼 하나씩 정수를 가져온다.
		for (int i = 1; i <= N; i++) {
			// 정수 가져옴
			int number = Integer.valueOf(br.readLine());
			// 힙에 넣기
			offerHeap(i, number);
			// 최대힙 최소힙 비교해서 스왑
			ifMaxHeapGreaterThanMinHeapSwap();
			// 중간 값 추출
			bw.write(String.valueOf(maxHeap.peek()));
			bw.newLine();
		}
		// 출력
		bw.flush();
	}

	/**
	 * 최대힙의 root값이 최소힙의 root값보다 크면 root값을 교환한다.
	 */
	private static void ifMaxHeapGreaterThanMinHeapSwap() {
		// 최대힙의 root가 최소힙의 root보다 크면
		if (isMaxHeapGreaterThanMinHeap()) {
			// 힙에서 root값을 빼서 교환
			int maxHeapRoot = maxHeap.poll();
			int minHeapRoot = minHeap.poll();
			maxHeap.offer(minHeapRoot);
			minHeap.offer(maxHeapRoot);
		}
	}

	/**
	 * 최대힙과 최소힙에 노드가 존재하고, 최대힙의 root가 최소힙의 root보다 큰지 판단
	 * @return true:최대힙의 root값이 크다, false:최소힙의 root값이 크거나 같다.
	 */
	private static boolean isMaxHeapGreaterThanMinHeap() {
		return !maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek();
	}

	/**
	 * 힙에 정수를 넣는다
	 * 홀수면 최대힙에, 짝수면 최소힙에 추가
	 * @param totalNumberOfInteger : 현재까지 정수의 총 개수
	 * @param number			   : 힙에 추가 할 정수
	 */
	private static void offerHeap(int totalNumberOfInteger, int number) {
		if (oddCheck(totalNumberOfInteger)) { // true : odd, false: even
			maxHeap.offer(number);
			return;
		}
		minHeap.offer(number);
	}

	/**
	 * 홀수인지 아닌지 체크한다.
	 * @param totalNumberOfInteger : 현재까지 정수의 총 개수
	 * @return true:홀수, false:짝수
	 */
	private static boolean oddCheck(int totalNumberOfInteger) {
		return totalNumberOfInteger % 2 != 0;
	}
}
