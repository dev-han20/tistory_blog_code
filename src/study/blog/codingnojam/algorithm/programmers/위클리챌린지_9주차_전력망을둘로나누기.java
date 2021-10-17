package study.blog.codingnojam.algorithm.programmers;

class 위클리챌린지_9주차_전력망을둘로나누기 {
	static int[] chk;
	static int[][] tree;
	static int count = 1;
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] arr = {{3, 4}, {1, 2}, {2, 3}};
		int[][] arr1 = {{1, 4}, {6, 3}, {2, 5}, {5, 1}, {5, 3}};
		// s.solution(6, arr1);
	}

	public int solution(int n, int[][] wires) {
		tree = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			tree[wires[i][0]][wires[i][1]] = 1;
			tree[wires[i][1]][wires[i][0]] = 1;
		}
		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < wires.length; i++) {
			chk = new int[n + 1];
			chk[1] = 1;
			tree[wires[i][0]][wires[i][1]] = 0;
			tree[wires[i][1]][wires[i][0]] = 0;
			DFS(1);
			tree[wires[i][0]][wires[i][1]] = 1;
			tree[wires[i][1]][wires[i][0]] = 1;

			int oCount = n - count;
			answer = Math.min(answer, Math.abs(count - oCount));
			count = 1;
		}



		return answer;
	}

	void DFS(int index) {
		for (int i = 0; i < tree[index].length; i++) {
			if (chk[tree[index][i]] == 1) {
				continue;
			}
			if (tree[index][i] == 1) {
				chk[tree[index][i]] = 1;
				count++;
				DFS(tree[index][i]);
			}
		}
	}
}