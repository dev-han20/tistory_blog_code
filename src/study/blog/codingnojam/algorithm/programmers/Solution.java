package study.blog.codingnojam.algorithm.programmers;

import java.util.*;

class Solution {

    HashMap<String, ArrayList<String>> db = new HashMap<>();


    public int[] solution(String[] numstrs, String[] words) {
        init();
        int[] answer  = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            String[] word = words[i].split("");
            ArrayList<String> list = new ArrayList<>();
            boolean[] chk = new boolean[word.length];
            String[] result  = new String[word.length];
            pro(word, result, 0, list, chk);

            for (int j = 0; j < numstrs.length; j++) {
                for (String s : list) {
                    if (numstrs[j].equals(s)) {
                        answer[i]++;
                    }
                }

            }
        }
        return answer;
    }

    public void pro(String[] word, String[] result, int index, ArrayList<String> list, boolean[] chk) {

        if (index == word.length) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < result.length; i++) {
                sb.append(result[i]);
            }
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < word.length; i++) {

            if (chk[i]) {
                continue;
            }

            ArrayList<String> dbList = db.get(word[i]);
            for (int j = 0; j < dbList.size(); j++) {
                result[index] = dbList.get(j);
                chk[i] = true;
                pro(word, result, index + 1, list, chk);
                chk[i] = false;
            }

        }
    }

    public void init() {
        db.put("0", new ArrayList<>());
        db.get("0").add("O");
        db.get("0").add("()");
        db.put("1", new ArrayList<>());
        db.get("1").add("I");
        db.put("2", new ArrayList<>());
        db.get("2").add("Z");
        db.get("2").add("S");
        db.get("2").add("7_");
        db.put("3", new ArrayList<>());
        db.get("3").add("E");
        db.get("3").add("B");
        db.put("4", new ArrayList<>());
        db.get("4").add("A");
        db.put("5", new ArrayList<>());
        db.get("5").add("Z");
        db.get("5").add("S");
        db.put("6", new ArrayList<>());
        db.get("6").add("b");
        db.get("6").add("G");
        db.put("7", new ArrayList<>());
        db.get("7").add("T");
        db.get("7").add("Y");
        db.put("8", new ArrayList<>());
        db.get("8").add("B");
        db.get("8").add("E3");
        db.put("9", new ArrayList<>());
        db.get("9").add("g");
        db.get("9").add("q");
    }
}