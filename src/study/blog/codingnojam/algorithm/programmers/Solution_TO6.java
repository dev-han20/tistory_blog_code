package study.blog.codingnojam.algorithm.programmers;

class Solution_TO6 {

    long limit = 0;
    long count = 0;
    long[] step = {1, 2, 3};

    public long solution(int numOfStairs) {
        recursion(numOfStairs);

        return count;
    }

    public void recursion(int n) {

        if (limit >= n) {
            if (limit == n) {
                count++;
            }
            return;
        }

        for (int i = 0; i < step.length; i++) {
            limit += step[i];
            if (limit > n) {
                limit -= step[i];
                continue;
            }
            recursion(n);
            limit -= step[i];
        }
    }
}