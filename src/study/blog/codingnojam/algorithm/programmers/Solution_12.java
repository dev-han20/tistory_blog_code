package study.blog.codingnojam.algorithm.programmers;

// 월간 코드 챌린지 시즌 2 음양 더하기
public class Solution_12 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0; i < absolutes.length; i++) {
            answer += signs[i] ? absolutes[i] : -absolutes[i];;
        }
        return answer;
    }

}
