package study.blog.codingnojam.algorithm.programmers;

class Solution_KA5 {
    public int[] solution(int[] prices) {

        int[] result = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {

            for (int j = i-1; j >= 0 ; j--) {
                if (prices[i] > prices[j]) {
                    result[j]++;
                }
            }
        }
        return result;
    }
}