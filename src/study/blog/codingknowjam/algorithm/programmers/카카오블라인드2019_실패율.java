package study.blog.codingknowjam.algorithm.programmers;

import java.io.IOException;
import java.util.HashMap;

class 카카오블라인드2019_실패율 {

    public static void main(String[] args) throws IOException {
        카카오블라인드2019_실패율 T = new 카카오블라인드2019_실패율();

    }

    class Solution {
        public int[] solution(int N, int[] stages) {
            int playerCount = stages.length;
            HashMap<Integer, Integer> sMap = new HashMap<>();

            for (int i = 0; i < stages.length; i++) {
                sMap.put(stages[i], sMap.getOrDefault(stages[i], 0) + 1);
            }



            int[] answer = {};
            return answer;
        }

        class Stage{
            int index;
            double failRate;

            public Stage(int index, double failRate) {
                this.index = index;
                this.failRate = failRate;
            }
        }
    }
}