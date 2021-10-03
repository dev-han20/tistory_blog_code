package study.blog.codingnojam.algorithm.programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class 카카오블라인드2019_실패율 {

    public static void main(String[] args) throws IOException {
        카카오블라인드2019_실패율 T = new 카카오블라인드2019_실패율();

    }

    class Solution {
        public int[] solution(int N, int[] stages) {
            int playerCount = stages.length;
            ArrayList<Stage> sList = new ArrayList<>();
            ArrayList<Integer> iList = new ArrayList<>();

            for (int i = 0; i < stages.length; i++) {
                iList.add(stages[i]);
            }
            Collections.sort(iList, Collections.reverseOrder());


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