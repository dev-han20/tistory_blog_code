package study.blog.codingnojam.algorithm.programmers;

class Solution {
    public int solution(int n) {

        int[] dp = new int[n + 1];
        dp[2] = 3;
        for (int i = 3; i < dp.length; i++) {
            if (i % 2 == 0) {
                dp[i] = (dp[i-2] * 3 + 2) % 1000000007;
            }
        }
        return dp[n];
    }
}