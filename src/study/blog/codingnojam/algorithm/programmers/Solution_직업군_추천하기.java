package study.blog.codingnojam.algorithm.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_직업군_추천하기 {


    public static void main(String[] args) {
        String s = "dfdf df qwqwe";
        String q = s.substring(0, s.indexOf(" "));
        System.out.println(q);
        Solution_직업군_추천하기 test = new Solution_직업군_추천하기();
        System.out.println(test.solution());

    }
    public String solution(String[] table, String[] languages, int[] preference) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        for (int i = 0; i < table.length; i++) {
            String[] temp = table[i].split(" ");
            HashMap<String, Integer> scores = new HashMap<>();
            for (int j = 1; j < temp.length; j++) {
                scores.put(temp[j], 6 - j);
            }
            map.put(temp[0], scores);
        }
        String name ="";
        int max = -1;

        for (int j = 0; j < table.length; j++) {
            int result = 0;
            String job = table[j].substring(0, table[j].indexOf(" "));
            for (int i = 0; i < languages.length; i++) {
                HashMap<String, Integer> scores = map.get(job);
                if (scores.get(languages[i]) != null) {
                    result = result + (scores.get(languages[i]) * preference[i]);
                }
            }
            if (max < result) {
                max = result;
                name = job;
            } else if (max == result) {
                if (table[j].substring(0, table[j].indexOf(" ")).compareTo(name) < 1) {
                    name = job;
                }
            }
        }

        return name;
    }

}