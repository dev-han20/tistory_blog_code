package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Arrays;
import java.util.Scanner;

public class Greedy_2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Meeting[] meets = new Meeting[N];
        for (int i = 0; i < N; i++) {
            int st = sc.nextInt();
            int et = sc.nextInt();
            meets[i] = new Meeting(st,et);
        }

        Arrays.sort(meets);

        int max = 0;
        int result = 0;
        for (int i = 0; i < meets.length; i++) {
            if (meets[i].st >= max) {
                result++;
                max = meets[i].et;
            }
        }
        System.out.println(result);
    }

    static class Meeting implements Comparable<Meeting> {
        int st;
        int et;

        public Meeting(int st, int et) {
            this.st = st;
            this.et = et;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.et == o.et) {
                return Integer.compare(this.st, o.st);
            } else {
                return Integer.compare(this.et, o.et);
            }
        }
    }
}
