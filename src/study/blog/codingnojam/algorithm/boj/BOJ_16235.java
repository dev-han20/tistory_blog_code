package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

// 백준 온라인 저지 16235 나무재테크 문제 풀이
public class BOJ_16235 {
	static int N;
	static int M;
	static int K;
	static int[][] foods;
	static int[][] lands;
	static ArrayList<Tree> trees = new ArrayList<>();
	static Deque<Integer> deadTrees = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] N_M_K = reader.readLine().split(" ");
		N = Integer.parseInt(N_M_K[0]);
		M = Integer.parseInt(N_M_K[1]);
		K = Integer.parseInt(N_M_K[2]);
		foods = new int[N][N];
		lands = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] food = reader.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				foods[i][j] = Integer.parseInt(food[j]);
				lands[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			String[] tree = reader.readLine().split(" ");
			trees.add(new Tree(tree));
		}

		Collections.sort(trees, (t1, t2) -> t1.age - t2.age);
		while (K != 0) {
			spring();
			summer();
			fall();
			winter();
			K--;
		}

		System.out.println(trees.size());
	}

	public static void spring() {
		for (int i = 0; i < trees.size(); i++) {
			Tree tree = trees.get(i);
			if (lands[tree.row][tree.col] < tree.age) {
				deadTrees.add(i);
				continue;
			}
			lands[tree.row][tree.col] -= tree.age;
			tree.age++;
		}
	}

	public static void summer() {
		while (!deadTrees.isEmpty()) {
			int deadTreeIndex = deadTrees.pollLast();
			Tree deadTree = trees.get(deadTreeIndex);
			int food = deadTree.age / 2;
			lands[deadTree.row][deadTree.col] += food;
			// trees.remove(deadTreeIndex);
			deadTree.dead = true;
		}
	}

	public static void fall() {
		int[] moveRow = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] moveCol = {-1, 0, 1, -1, 1, -1, 0, 1};
		ArrayList<Tree> newTrees = new ArrayList<>();
		for (int p = 0; p < trees.size(); p++) {
			Tree tree = trees.get(p);
			if (tree.dead) {
				continue;
			}
			if (tree.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nr = tree.row + moveRow[i];
					int nc = tree.col + moveCol[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					newTrees.add(new Tree(nr, nc, 1));
				}
			}
		}
		for (Tree tree : trees) {
			if (!tree.dead) {
				newTrees.add(tree);
			}
		}
		trees = newTrees;
	}

	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				lands[i][j] += foods[i][j];
			}
		}
	}

	static class Tree {
		int row;
		int col;
		int age;
		boolean dead;

		public Tree(String[] tree) {
			this.row = Integer.parseInt(tree[0]) - 1;
			this.col = Integer.parseInt(tree[1]) - 1;
			this.age = Integer.parseInt(tree[2]);
		}

		public Tree(int row, int col, int age) {
			this.row = row;
			this.col = col;
			this.age = age;
		}
	}

}
