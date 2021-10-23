package study.blog.codingknowjam.algorithm.programmers;

public class 위클리_챌린지_8주차_최소직사각형 {
    public static void main(String[] args) {
    }

    private class Solution {
        public int solution(int[][] sizes) {
            int wMax = 0;
            int hMax = 0;
            for(int i=0; i<sizes.length; i++){
                int w = sizes[i][0];
                int h = sizes[i][1];
                if(w < h){
                    sizes[i][0] = h;
                    sizes[i][1] = w;
                }

                wMax = Math.max(sizes[i][0], wMax);
                hMax = Math.max(sizes[i][1], hMax);
            }

            int answer = wMax * hMax;
            return answer;
        }
    }
}
