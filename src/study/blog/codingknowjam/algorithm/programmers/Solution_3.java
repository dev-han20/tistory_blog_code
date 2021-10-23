package study.blog.codingknowjam.algorithm.programmers;

// 프로그래머스 위클리 챌린지 1주차 부족한 금액 계산하기
public class Solution_3 {

    public long solution(int price, int money, int count) {
        long answer = 0;
        for (int i = 1; i <= count; i++) {
            answer = answer + price * i;
        }
        if (money - answer >= 0) {
            answer = 0;
        } else {
            answer = Math.abs(money - answer);
        }
        return answer;
    }

}
