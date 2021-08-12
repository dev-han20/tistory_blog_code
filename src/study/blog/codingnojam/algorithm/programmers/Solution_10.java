package study.blog.codingnojam.algorithm.programmers;

// 월간 코드 챌린지 시즌 1 내적
class Solution_10 {


    public int solution(int[] a, int[] b) {
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer = answer + (a[i] * b[i]);
        }
        return answer;
    }
}