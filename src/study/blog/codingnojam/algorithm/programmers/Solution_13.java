package study.blog.codingnojam.algorithm.programmers;

import java.util.Arrays;

// 2021 카카오 블라인드 - 순위검색 효율성 다시풀어보와햠
class Solution_13 {
    public int[] solution(String[] info, String[] query) {

        int[] answer = new int[query.length];
        Man[] arr = new Man[info.length];

        for (int i = 0; i < info.length; i++) {
            String[] temp = info[i].split(" ");
            arr[i] = new Man(temp[0], temp[1], temp[2], temp[3], Integer.parseInt(temp[4]));
        }

        Arrays.sort(arr);

        for (int i = 0; i < query.length; i++) {
            int count = 0;
            String[] temp = query[i].replaceAll(" and ", " ").split(" ");

            int target = Integer.parseInt(temp[4]);
            int start = 0;
            int end = arr.length;
            int mid = 0;

            while (start < end) {
                mid = (start + end) / 2;
                if (target > arr[mid].score) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            for (int j = start; j < arr.length; j++) {
                if (temp[0].equals(arr[j].c1) || temp[0].equals("-")) {
                    if (temp[1].equals(arr[j].c2) || temp[1].equals("-")) {
                        if (temp[2].equals(arr[j].c3) || temp[2].equals("-")) {
                            if (temp[3].equals(arr[j].c4) || temp[3].equals("-")) {
                                count++;
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }

            answer[i] = count;
        }
        return answer;
    }

    class Man implements Comparable<Man> {
        String c1;
        String c2;
        String c3;
        String c4;
        int score;

        public Man(String c1, String c2, String c3, String c4, int score) {
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
            this.score = score;
        }

        @Override
        public int compareTo(Man m) {
            return this.score > m.score ? 1 : -1;
        }

        @Override
        public String toString() {
            return "Man{" +
                    "c1='" + c1 + '\'' +
                    ", c2='" + c2 + '\'' +
                    ", c3='" + c3 + '\'' +
                    ", c4='" + c4 + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}