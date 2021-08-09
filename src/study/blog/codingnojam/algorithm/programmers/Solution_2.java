package study.blog.codingnojam.algorithm.programmers;

// 프로그래머스 위클리챌린지 2주차 상호평가 문제 풀이
public class Solution_2 {

    public String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.length; i++) {
            int now = scores[i][i];
            int up = 0;
            int down = 0;
            int result = 0;
            for (int j = 0; j < scores.length; j++) {

                if (scores[j][i] > now) {
                    down++;
                }else if (scores[j][i] < now) {
                    up++;
                }
                result = result + scores[j][i];

            }
            String grade;
            double avg = 0;
            if (down == scores.length - 1 || up == scores.length - 1) {
                result = result - now;
                avg = (double)result / (scores.length - 1);
            } else {
                avg = (double)result / scores.length;
            }

            if (avg >= 90) { grade = "A"; }
            else if (avg >= 80) { grade = "B"; }
            else if (avg >= 70) { grade = "C"; }
            else if (avg >= 50) { grade = "D"; }
            else { grade = "F"; }

            sb.append(grade);
        }

        return sb.toString();
    }

}
