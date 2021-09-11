package study.blog.codingnojam.algorithm.ifrktw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Block[] blocks = new Block[N];

        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split(" ");
            blocks[i] = new Block(Integer.parseInt(info[0]),Integer.parseInt(info[1]),Integer.parseInt(info[2]));
        }

        Arrays.sort(blocks);

        int[] dp = new int[N];
        int result = 0;

        for (int i = 0; i < blocks.length; i++) {
            int max = 0;
            for (int j = i-1; j >=0 ; j--) {
                if (blocks[i].weight > blocks[j].weight) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + blocks[i].height;
            result = Math.max(dp[i], result);
        };
        System.out.println(result);



    }

    static class Block implements Comparable<Block>{
        int area;
        int height;
        int weight;

        public Block(int area, int height, int weight) {
            this.area = area;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Block o) {
            return this.area > o.area ? 1 : -1;
        }

    }

}
