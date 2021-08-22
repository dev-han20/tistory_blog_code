package study.blog.codingnojam.algorithm.programmers;

import java.util.HashMap;

class Solution {
    public boolean[] solution(String[] infos, String[] actions) {

        boolean[] answer = new boolean[actions.length];
        String loginChk = "NO";
        boolean basketChk = false;
        HashMap<String, String> users = new HashMap<>();

        for (int i = 0; i < infos.length; i++) {
            String[] temp = infos[i].split(" ");
            users.put(temp[0], temp[1]);
        }

        for (int i = 0; i < actions.length; i++) {
            String[] temp = actions[i].split(" ");
            if (temp[0].equals("ADD")) {
                if (loginChk.equals("NO")) {
                    answer[i] = false;
                } else {
                    answer[i] = true;
                    basketChk = true;
                }
            } else if (temp[0].equals("LOGIN")) {
                if (loginChk.equals("NO")) {
                    if (users.get(temp[1]).equals(temp[2])) {
                        answer[i] = true;
                        loginChk = "YES";
                    } else {
                        answer[i] = false;
                    }
                } else {
                    answer[i] = false;
                }
            } else {
                if (basketChk) {
                    answer[i] = true;
                    basketChk = false;
                } else {
                    answer[i] = false;
                }
            }
        }

        return answer;
    }
}