package study.blog.codingknowjam.algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

// 2018 카카오 블라인드 리쿠르팅 -[1차] 뉴스클러스터링
class Solution_news {
    public int solution(String str1, String str2) {

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        str1 = str1.replaceAll("[^a-zA-Z]", "-").toUpperCase();
        str2 = str2.replaceAll("[^a-zA-Z]", "-").toUpperCase();

        for (int i = 0; i < str1.length()-1; i++) {
            if (!(str1.charAt(i) == '-' || str1.charAt(i + 1) == '-')) {
                String temp = String.valueOf(str1.charAt(i)) + String.valueOf(str1.charAt(i+1));
                list1.add(temp);
            }
        }

        for (int i = 0; i < str2.length()-1; i++) {
            if (!(str2.charAt(i) == '-' || str2.charAt(i + 1) == '-')) {
                String temp = String.valueOf(str2.charAt(i)) + String.valueOf(str2.charAt(i+1));
                list2.add(temp);
            }
        }
        boolean[] chk1 = new boolean[list1.size()];
        int chk1Count = 0;
        boolean[] chk2 = new boolean[list2.size()];
        int chk2Count = 0;
        List<String> sumList = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (chk2[j]) {
                    continue;
                }
                if (list1.get(i).equals(list2.get(j))) {
                    chk1[i] = true;
                    chk1Count++;
                    chk2[j] = true;
                    chk2Count++;
                    sumList.add(list1.get(i));
                    break;
                }
            }
        }

        double dup = sumList.size();

        double sum = dup + chk1.length - chk1Count + chk2.length - chk2Count;

        int answer = 0;
        if (sum == 0) {
            answer = 1 * 65536;
        } else {
            double jaka = Math.floor(dup / sum * 65536);
            answer = (int)jaka;
        }

        return answer;
    }
}