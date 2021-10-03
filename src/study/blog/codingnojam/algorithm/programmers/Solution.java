package study.blog.codingnojam.algorithm.programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    public static void main(String[] args) throws IOException {
        Solution s = new Solution();

        StringBuilder sb = new StringBuilder();
        sb.toString();
//        String q = " ABCDEF";
        String q = " ABCDEFG";

        ArrayList<String> list = new ArrayList<>();
//        LinkedList<String> list = new LinkedList<>();

        String[] tree = q.split("");
        System.out.println(tree[3]);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("ee", map.getOrDefault("ee",0)+1);
//        int[][] mo = {{10, 4}, {300, 1}, {90, 4},{260, 4}};
//        s.solution(mo);

        DFS(1, tree, sb);
        System.out.println(sb.toString());


    }

    public static void DFS(int index,String[] tree, StringBuilder sb) {
        if ((index * 2) > tree.length) {
            sb.append(tree[index]);
            return;
        }

        DFS(index * 2, tree, sb);
        if (index * 2 + 1 < tree.length) {
            DFS(index * 2 + 1, tree, sb);
        }
        sb.append(tree[index]);
    }

    public int solution(int[][] money) {

        Arrays.sort(money, (o1,o2) -> o2[0] - o1[0]);

        System.out.println("---");
        for (int[] ints : money) {
            System.out.println(ints[0]);
        }
        return 1;
    }
}