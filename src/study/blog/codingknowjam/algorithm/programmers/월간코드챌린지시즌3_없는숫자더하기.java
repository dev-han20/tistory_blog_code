package study.blog.codingknowjam.algorithm.programmers;

import java.io.IOException;

class 월간코드챌린지시즌3_없는숫자더하기 {

    public static void main(String[] args) throws IOException {
        월간코드챌린지시즌3_없는숫자더하기 ㅆ = new 월간코드챌린지시즌3_없는숫자더하기();

    }

    class Solution {
        public int solution(int[] numbers) {
            int[] chk = new int[10];
            for (int i = 0; i < numbers.length; i++) {
                chk[numbers[i]] = 1;
            }
            int answer = 0;
            for (int i = 0; i < chk.length; i++) {
                if (chk[i] == 0) {
                    answer += chk[i];
                }
            }
            return answer;
        }
    }
}