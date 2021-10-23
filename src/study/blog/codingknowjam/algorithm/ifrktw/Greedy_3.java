package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Greedy_3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Time> times = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int st = sc.nextInt();
            int et = sc.nextInt();
            times.add(new Time(st, "S"));
            times.add(new Time(et, "E"));
        }

        Collections.sort(times, (t1, t2) -> {
            if (t1.hour == t2.hour) {
                return t1.state.compareTo(t2.state);
            } else {
                return t1.hour - t2.hour;
            }
        });

        int result = 0;
        int tempReulst = 0;

        for (int i = 0; i < times.size(); i++) {
            if (times.get(i).state.equals("S")) {
                tempReulst++;
                result = Math.max(result, tempReulst);
            } else {
                tempReulst--;
            }
        }
        System.out.println(result);
    }

    static class Time{
        int hour;
        String state;

        public Time(int h, String s){
            this.hour = h;
            this.state = s;
        }

    }
}
