package study.blog.codingknowjam.algorithm.programmers;

class Solution_대각선자르기 {
    public long solution(int w, int h) {
        int gcd = eucd(Math.max(w, h), Math.min(w, h));

        long answer = (w*1L) * (h*1L) - (w + h - gcd);
        return answer;
    }

    public int eucd(int bn, int sn) {
        int r = bn % sn;
        if (r == 0) {
            return sn;
        } else {
            return eucd(sn, r);
        }
    }
}