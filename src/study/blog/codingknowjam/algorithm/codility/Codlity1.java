package study.blog.codingknowjam.algorithm.codility;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.ArrayList;
import java.util.List;

class Codlity1 {
    public int solution(int N) {
        int result = 0;
        String b = Integer.toBinaryString(N);
        String[] arr = b.split("");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("1")) {
                list.add(i);
            }
        }

        for (int i = 0; i < list.size()-1; i++) {
            result = Math.max(result, list.get(i + 1) - list.get(i) -1);
        }
        return result;
    }
}
