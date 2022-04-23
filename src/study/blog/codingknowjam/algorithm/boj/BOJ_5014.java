package study.blog.codingknowjam.algorithm.boj;

import java.util.*;

public class BOJ_5014 {
	private static Scanner scanner = new Scanner(System.in);
	private static int numberOfTotalFloor;
	private static int currentFloor;
	private static int destinationFloor;
	private static List<Integer> upDownValues;
	private static int[] visitedFloor;

	public static void main(String[] args) {
		// 문제에서 주어진 데이터 입력 받기
		Integer[] inputData = readLineAndChangeIntegerArray(" ");
		numberOfTotalFloor = inputData[0]; // 전체 층의 개수
		currentFloor = inputData[1]; // 현재 내가 있는 층
		destinationFloor = inputData[2]; // 내가 이동해야 하는 층(스타트링크)
		upDownValues = List.of(inputData[3], -inputData[4]); // 엘리베이터로 이동할 수 있는 층의 개수(up, down)를 배열로 저장
		// 현재 있는 층이 스타트링크면 0(이동 횟수) 출력
		if (currentFloor == destinationFloor) {
			System.out.println(0);
			return;
		}
		// BFS를 위한 큐
		Queue<Integer> queue = new LinkedList<>();
		// 방문처리를 저장할 배열 (1:방문, 0:미방문)
		visitedFloor = new int[numberOfTotalFloor + 1];
		// 현재 층을 큐에 담고 방문처리
		queue.offer(currentFloor);
		visitedFloor[currentFloor] = 1;
		// 엘리베이터 타고 이동한 횟수(BFS 횟수)
		int countOfMove = 0;
		// 순회 시작
		while (!queue.isEmpty()) {
			// 동일하게 이동한 거리에서 방문할 수 있는 층의 개수만큼만 반복(level 순회하기 위함)
			int numberOfRepeat = queue.size();
			for (int i = 0; i < numberOfRepeat; i++) {
				Integer floor = queue.poll(); // 현재 층
				for (int j = 0; j < upDownValues.size(); j++) {
					int nextFloor = floor + upDownValues.get(j);
					// 현재 층에서 엘리베이터 타고 이동가능(전체 층을 벗어날 수 없음)하면서 방문하지 않은 층인가?
					if (isPossibleToMove(nextFloor)) {
						// 목표 층에 도달했으면 현재까지 이동한 횟수+1 출력
						if (destinationFloor == nextFloor) {
							System.out.println(countOfMove + 1);
							return;
						}
						// 아니라면 다음 level 순회를 위해 큐에 넣고 방문처리
						queue.offer(nextFloor);
						visitedFloor[nextFloor] = 1;
					}
				}
			}
			// 동일한 이동거리에서 방문가능한 층(동일 level)을 모두 순회하면 이동한 횟수 1 증가
			countOfMove++;
		}
		// BFS 순회가 종료되면 이동 불가능하므로 문구 출력
		System.out.println("use the stairs");
	}

	/**
	 * 이동 가능한 층인지 확인
	 * @param nextFloor : 다음에 이동할 층
	 */
	private static boolean isPossibleToMove(int nextFloor) {
		return nextFloor <= numberOfTotalFloor && nextFloor >= 1 && visitedFloor[nextFloor] == 0;
	}

	/**
	 * 문자열 1줄을 읽어서 정수형 배열로 변환
	 * @param delimiter : 문자열을 자르기 위한 구분자
	 */
	private static Integer[] readLineAndChangeIntegerArray(String delimiter) {
		return Arrays.stream(scanner.nextLine().split(" "))
			.map(Integer::valueOf)
			.toArray(Integer[]::new);
	}
}
