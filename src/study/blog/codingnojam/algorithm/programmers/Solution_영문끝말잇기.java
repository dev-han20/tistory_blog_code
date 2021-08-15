package study.blog.codingnojam.algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

// 프로그래머스 영문 끝말잇기
class Solution_영문끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        Map<String, Integer> db = new HashMap<>();
        char startSpelling = words[0].charAt(words[0].length() - 1);
        db.put(words[0], 1);

        for (int i = 1; i < words.length; i++) {
            if (words[i].charAt(0) == startSpelling) {
                if (!db.containsKey(words[i])) {
                    db.put(words[i], 1);
                    startSpelling = words[i].charAt(words[i].length() - 1);
                } else {
                    int q = (i + 1) / n;
                    int r = (i + 1) % n;
                    if (r == 0) {
                        r = n;
                    } else {
                        q++;
                    }
                    answer[0] = r;
                    answer[1] = q;
                    break;
                }

            } else {
                int q = (i + 1) / n;
                int r = (i + 1) % n;
                if (r == 0) {
                    r = n;
                } else {
                    q++;
                }
                answer[0] = r;
                answer[1] = q;
                break;
            }
        }


        return answer;
    }
}