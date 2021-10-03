package study.blog.codingnojam.algorithm.programmers;


class X만큼_간격이있는_N개의_숫자 {

    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        for (long i = 1; i <= n; i++) {
            answer[(int)i-1] = i*x;
        }
        return answer;
    }
}