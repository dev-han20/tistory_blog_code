package study.blog.codingnojam.algorithm.programmers;

class Solution_7 {

    // 2021 웹 백엔드 상반기 데브매칭 로또의 최고순위와 최저순위
    public int[] solution(int[] lottos, int[] win_nums) {

        int[] answer = new int[2];
        int zeroCount = 0;
        int sameCount = 0;

        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zeroCount++;
                continue;
            }
            for (int j = 0; j < win_nums.length; j++) {
                if (lottos[i] == win_nums[j]) {
                    sameCount++;
                    break;
                }
            }
        }

        answer[0] = 7 - (sameCount + zeroCount) >= 6 ? 6 : 7 - (sameCount + zeroCount);
        answer[1] = 7 - sameCount >= 6 ? 6 : 7 - sameCount;

        return answer;
    }
}