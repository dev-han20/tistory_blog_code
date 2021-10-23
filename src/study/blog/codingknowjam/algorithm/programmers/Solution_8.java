package study.blog.codingknowjam.algorithm.programmers;

// 프로그래머스 섬머/윈터 코딩 2018 소수 만들기
class Solution_8 {

    static int count = 0;
    static int result = 0;
    static boolean[] chk = new boolean[3001];
    static int answer = 0;

    public int solution(int[] nums) {
        int answer = -1;

        chk[0] = true;
        chk[1] = true;

        for (int i = 2; i < chk.length; i++) {
            if (chk[i]) {
                continue;
            }
            for (int j = i+i; j < chk.length; j = j + i) {
                if (!chk[j]) {
                    chk[j] = true;
                }
            }
        }

        recursion(nums, 0);

        return answer;
    }

    public void recursion(int[] nums, int index) {

        if (count >= 3) {
            if (!chk[result]) {
                answer++;
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            result += nums[i];
            count++;
            recursion(nums, i+1);
            count--;
            result -= nums[i];
        }
    }
}