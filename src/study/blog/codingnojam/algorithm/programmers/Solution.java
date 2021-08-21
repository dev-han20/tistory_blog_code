package study.blog.codingnojam.algorithm.programmers;

class Solution {
    public static void main(String[] args) {
        int[] aa = {1, 3, 5, 7, 6, 8, 9, 5, 1};
        solution(aa);
        System.out.println();
    }
    public static int solution(int[] arr) {
        int answer = 0;
        int startIndex = 0;
        int min = Integer.MAX_VALUE;

        boolean endChk = false;
        while (!endChk) {

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 0) {
                    min = Math.min(min, arr[i]);
                } else {
                    if (i - 1 < 0 || min == Integer.MAX_VALUE) {
                        startIndex = i + 1;
                        continue;
                    } else {
                        for (int j = startIndex; j < i; j++) {
                            arr[j] = arr[j] - min;
                        }
                        answer++;
                        startIndex = i + 1;
                        min = Integer.MAX_VALUE;
                        continue;
                    }
                }

                if (i == arr.length - 1) {
                    for (int j = startIndex; j < arr.length; j++) {
                        arr[j] = arr[j] - min;
                    }
                    answer++;
                    startIndex = 0;
                    min = Integer.MAX_VALUE;
                }
            }


            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 0) {
                    endChk = false;
                    break;
                } else {
                    endChk = true;
                }
            }
        }

        return answer;
    }
}