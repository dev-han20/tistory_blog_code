package study.blog.codingknowjam.algorithm.programmers;


import java.util.ArrayList;
import java.util.Collections;

class Solution_6주차_복서정렬하기 {
    public int[] solution(int[] weights, String[] head2head) {

        ArrayList<Boxer> list = new ArrayList<>();

        for (int i = 0; i < head2head.length; i++) {
            String str = head2head[i];
            list.add(new Boxer(weights[i], i+1));
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'W') {
                    list.get(i).roundCount++;
                    if (weights[i] < weights[j]) {
                        list.get(i).winCount_Weight++;
                        list.get(i).winCount++;
                    }else
                        list.get(i).winCount++;
                } else if (str.charAt(j) == 'L') {
                    list.get(i).roundCount++;
                }
            }
            list.get(i).init();
        }

        Collections.sort(list);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).number;

        }
        return answer;
    }

    class Boxer implements  Comparable<Boxer>{
        int number;
        int roundCount;
        int winCount;
        int winCount_Weight;
        int weight;
        double winRate;

        public Boxer(int weight, int number) {
            this.weight = weight;
            this.number = number;
        }

        public void init() {
            if (this.roundCount != 0) {
                this.winRate = 100.0 * (double)winCount / (double)roundCount;
            }
        }

        @Override
        public int compareTo(Boxer o) {
            if (this.winRate < o.winRate) {
                return 1;
            } else if (this.winRate == o.winRate) {
                if (this.winCount_Weight < o.winCount_Weight) {
                    return 1;
                } else if (this.winCount_Weight == o.winCount_Weight) {
                    if (this.weight < o.weight) {
                        return 1;
                    } else if (this.weight == o.weight) {
                        if (this.number > o.number) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }
}