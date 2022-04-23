package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2644 {
	// 입력 받기 위한 reader
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static int numberOfPeople;
	private static int[][] graphs;
	private static int numberOfEdge;
	private static Integer[] startAndEndLocation;
	private static int[] visited;

	public static void main(String[] args) throws IOException {
		// 전체 사람의 수
		numberOfPeople = readLineAndChangeInteger();
		// BFS 순회를 위한 2차원 배열 (간선의 길이는 1이고, 길이가 곧 촌수를 의미)
		graphs = new int[numberOfPeople + 1][numberOfPeople + 1];
		// 촌수를 구할 두 사람 입력 받기
		startAndEndLocation = readLineAndChangeIntegerArray(" ");
		// 촌수를 표현할 간선의 개수
		numberOfEdge = readLineAndChangeInteger();
		// 간선의 개수만큼 반복문을 돌며, 2차원 배열에 서로간의 관계를 설정
		initGraphs();
		// BFS 순회하면서 방문여부를 체크할 배열
		visited = new int[numberOfPeople + 1];
		// BFS 순회에 사용할 큐
		Queue<Integer> queue = new LinkedList<>();
		// 두 사람간의 촌수를 구하기 위해 큐에 첫번 째 사람을 넣음(사람을 노드로 생각하는 겁니다)
		queue.offer(startAndEndLocation[0]);
		// 사람을 하나의 노드로 보고 해당 노드 방문처리
		visited[startAndEndLocation[0]] = 1;
		// 두 사람간의 촌수를 저장할 변수
		int distance = 0;
		// BFS 순회
		while (!queue.isEmpty()) {
			// 큐에 들어있는 만큼 반복 (동일한 level의 노드들만 순회하기 위함)
			int numberOfRepeat = queue.size();
			for (int i = 0; i < numberOfRepeat; i++) {
				// 2차원 배열을 순회할 때, 특정 행의 열 값이 1이면 친척
				// 이때 친척인데 방문하지 않은 사람만 골라서 큐에 넣으면서 순회
				int node = queue.poll();
				for (int j = 0; j < graphs[node].length; j++) {
					if (graphs[node][j] == 1 && visited[j] == 0) {
						// 현재 순회하는 노드가 촌수를 구할 두 사람중 남은 사람이라면 현재까지 구한 촌수+1 후 출력
						if (j == startAndEndLocation[1]) {
							System.out.println(distance + 1);
							return;
						}
						// 아니라면 다음 level순회 때 찾아야 하므로 큐에 넣고 방문처리
						queue.offer(j);
						visited[j] = 1;
					}
				}
			}
			// 한번의 level 순회가 끝나면 촌수 증가
			distance++;
		}
		// 순회가 끝났다는건 서로 친척관계가 아니므로 -1 출력
		System.out.println(-1);
	}
	/**
	 * BFS 순회를 위해 사람간의 관계를 그래프 형태로 초기화
	 */
	private static void initGraphs() throws IOException {
		for (int i = 0; i < numberOfEdge; i++) {
			Integer[] edge = readLineAndChangeIntegerArray(" ");
			graphs[edge[0]][edge[1]] = 1;
			graphs[edge[1]][edge[0]] = 1;
		}
	}
	/**
	 * 문자열 1줄을 읽어서 정수로 반환
	 */
	private static Integer readLineAndChangeInteger() throws IOException {
		return Integer.valueOf(reader.readLine());
	}
	/**
	 * 문자열 1줄을 읽어서 정수형 배열로 반환
	 * @param delimiter : 문자열을 자르는 기준이 되는 구분자
	 */
	private static Integer[] readLineAndChangeIntegerArray(String delimiter) throws IOException {
		return Arrays.stream(reader.readLine().split(delimiter))
			.map(Integer::valueOf)
			.toArray(Integer[]::new);
	}
}
