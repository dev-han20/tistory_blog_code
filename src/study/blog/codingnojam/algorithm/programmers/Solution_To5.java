package study.blog.codingnojam.algorithm.programmers;

import java.util.*;

class Solution_To5 {
    public int[] solution(int[] fruitWeights, int k) {

        int[] range = new int[2];

        int end = k-1;
        int start = 0;
        int result = 0;
        int maxIndex = 0;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i <= end; i++) {
            if (result < fruitWeights[i]) {
                result = fruitWeights[i];
                maxIndex = i;
            }
        }
        set.add(result);
        end++;
        start++;

        while (end != fruitWeights.length) {

            if (end >= maxIndex && start <= maxIndex) {
                if (fruitWeights[end] >= result) {
                    result = fruitWeights[end];
                    maxIndex = end;
                    set.add(result);
                }
            } else {
                result = 0;
                for (int i = start; i <= end; i++) {
                    if (result < fruitWeights[i]) {
                        result = fruitWeights[i];
                        maxIndex = i;
                    }
                }
                set.add(result);
            }
            end++;
            start++;
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list,Collections.reverseOrder());
        int[] answer = new int[list.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }


        return answer;
    }
}