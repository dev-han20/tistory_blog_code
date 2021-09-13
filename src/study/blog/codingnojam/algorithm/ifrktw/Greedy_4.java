package study.blog.codingnojam.algorithm.ifrktw;

import java.util.*;

public class Greedy_4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int max = 0;
        List<Lecture> lectures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int m = sc.nextInt();
            int d = sc.nextInt();
            max = Math.max(max, d);
            lectures.add(new Lecture(m, d));
        }

        Collections.sort(lectures);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int result = 0;
        int j = 0;
        for (int i = max; i >= 1; i--) {
            for (int k = j; k <lectures.size() ; k++) {
                if (i > lectures.get(k).date) {
                    j = k;
                    break;
                }
                pq.offer(lectures.get(k).money);
            }
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        System.out.println(result);

    }

    static class Lecture implements Comparable<Lecture>{
        int money;
        int date;

        public Lecture(int money, int date) {
            this.money = money;
            this.date = date;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.date - this.date;
        }
    }
}
