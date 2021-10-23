package study.blog.codingknowjam.algorithm.programmers;

import java.util.*;

// 2021 카카오 블라인드 - 메뉴리뉴얼
class Solution_메뉴리뉴얼 {

    public static void main(String[] args) {
        char[] dd = {'H', 'Y', 'W', 'B'};
        Arrays.sort(dd);
        for (int i = 0; i < dd.length; i++) {
            System.out.println(dd[i]);
        }

        String[] s = {"XYZ", "XWY", "WXA"};
        int[] c = {2, 3, 4};
        solution(s, c);
    }

    static HashMap<String, Integer> menu = new HashMap<>();
    static HashSet<String> set = new HashSet<>();
    static List<String> list = new ArrayList<>();

    public static String[] solution(String[] orders, int[] course) {

        for (int i = 0; i < course.length; i++) {
            char[] chars = new char[course[i]];
            int result = 0;

            for (int j = 0; j < orders.length; j++) {
                boolean[] chk = new boolean[orders[j].length()];

                recursion(0, orders[j], course[i], chars, chk);

                for (String s : set) {
                    int count = menu.getOrDefault(s, 0)+1;
                    result = Math.max(result, count);
                    menu.put(s ,count);
                }
                set.clear();
            }

            for (Map.Entry<String, Integer> et : menu.entrySet()) {
                if (et.getValue() == result && result >=2) {
                    list.add(et.getKey());
                }
            }

            menu.clear();
        }

        Collections.sort(list);
        String[] answer = list.toArray(new String[list.size()]);
        return answer;
    }

    public static void recursion(int index, String orders, int limit, char[] ch, boolean[] chk) {

        if (index == limit) {
            StringBuilder sb = new StringBuilder();
            char[] copy = new char[ch.length];
            for (int i = 0; i < ch.length; i++) {
                copy[i] =ch[i];
            }
            Arrays.sort(copy);
            for (int i = 0; i < copy.length; i++) {
                sb.append(String.valueOf(copy[i]));
            }

            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < orders.length(); i++) {
            if (chk[i]) {
                continue;
            }

            ch[index] = orders.charAt(i);
            chk[i] = true;
            recursion(index + 1, orders, limit, ch, chk);
            chk[i] = false;

            if (index == 0) {
                chk[i] = true;
            }
        }


    }
}