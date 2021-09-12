package study.blog.codingnojam.algorithm.programmers;

import java.util.LinkedList;

class Solution_5주차_모음사전 {
    String[] words = {"A", "E", "I", "O", "U"};
    int result = 0;
    int count =0;
    LinkedList<String> list = new LinkedList<>();

    public int solution(String word) {
        recursion(0, word);
        return result;
    }

    public void recursion(int index, String word){
        if (index == 5) return;
        for (int i = 0; i < words.length ; i++) {
            list.add(words[i]);
            count++;
            if (String.join("",list).equals(word)) result = count;
            recursion(index + 1, word);
            list.removeLast();
        }
    }
}