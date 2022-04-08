package study.blog.codingknowjam.algorithm.boj;

import java.io.*;
import java.util.Objects;

public class BOJ_5639 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 입력받기용
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));    // 출력하기용
	private static Node binarySearchTree;

	public static void main(String[] args) throws IOException {
		int number = Integer.valueOf(br.readLine());    // 정수 받기
		binarySearchTree = new Node(number);            // 이진검색트리 생성

		// 노드의 개수가 10,000개 이하이므로 최대 개수만큼 반복문 수행
		for (int i = 0; i < 10000; i++) {
			// 주어진 정수의 개수가 10,000개 이하일 경우 exception이 발생하므로 catch로 처리
			try {
				// 정수 입력받기
				number = Integer.valueOf(br.readLine());
				// 정수 이진검색트리에 저장
				save(binarySearchTree, number);
			} catch (Exception e) {
				break;
			}
		}

		// 이진검색트리 후위순회
		DFS(binarySearchTree);
		bw.flush();
	}

	/**
	 * 좌측 자식노드 -> 우측 자식노드 -> 부모노드 순으로 후위순회
	 * @param node : 부모노드
	 */
	private static void DFS(Node node) throws IOException {
		if (node == null) {
			return;
		}
		DFS(node.leftNode);        // 좌측 자식노드 이동
		DFS(node.rightNode);    // 우측 자식노드 이동
		bw.write(String.valueOf(node.root));    // 부모노드 값 출력
		bw.newLine();
	}

	/**
	 * 1번 인자로 받은 노드의 자식노드에 2번 인자로 받은 정수를 저장한다.
	 * @param binarySearchTree : 이진검색트리
	 * @param number           : 저장할 정수
	 */
	private static void save(Node binarySearchTree, int number) {
		// 이진검색트리의 root값이 저장할려는 정수보다 크면
		if (binarySearchTree.root > number) {
			// 왼쪽 자식노드가 없으면 생성하고 있으면 이동
			ifLeftNodeIsNullCreateNodeOrMoveNextNode(binarySearchTree, number);
			return;
		}
		// 이진검색트리의 root값이 저장할려는 정수보다 작으면
		// 오른쪽 자식노드가 없으면 생성하고 있으면 이동
		ifRightNodeIsNullCreateNodeOrMoveNextNode(binarySearchTree, number);
		return;
	}

	/**
	 * 왼쪽 자식노드가 없으면 생성하고 있으면 이동
	 * @param node   : 부모 노드
	 * @param number : 저장할 정수
	 */
	private static void ifLeftNodeIsNullCreateNodeOrMoveNextNode(Node node, int number) {
		if (Objects.isNull(node.leftNode)) {
			node.leftNode = new Node(number);
			return;
		}
		save(node.leftNode, number);
	}

	/**
	 * 오른쪽 자식노드가 없으면 생성하고 있으면 이동
	 * @param node   : 부모 노드
	 * @param number : 저장할 정수
	 */
	private static void ifRightNodeIsNullCreateNodeOrMoveNextNode(Node node, int number) {
		if (Objects.isNull(node.rightNode)) {
			node.rightNode = new Node(number);
			return;
		}
		save(node.rightNode, number);
	}

	/**
	 * 노드 클래스
	 */
	private static class Node {
		// 왼쪽 자식노드
		Node leftNode = null;
		// 오른쪽 자식노드
		Node rightNode = null;
		// 노드의 값
		int root;

		public Node(int root) {
			this.root = root;
		}
	}

}
