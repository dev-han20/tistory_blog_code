package study.blog.codingknowjam.algorithm.programmers;

// 2017 팁스타운 - 예상 대진표
class Solution_예상대진표
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        a = a + n-1;
        b = b + n-1;

        while (a != b) {
            a = a / 2;
            b = b / 2;
            answer++;
        }
        return answer;

    }
}