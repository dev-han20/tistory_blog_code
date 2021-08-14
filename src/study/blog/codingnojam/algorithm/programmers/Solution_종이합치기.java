package study.blog.codingnojam.algorithm.programmers;

// 종이합치기
class Solution_종이합치기 {

    String[] s = new String[7];
    boolean[] chk = new boolean[7];
    boolean[] era = new boolean[10000000];
    int result = 0;

    public int solution(String numbers) {

        era[0] = true;
        era[1] = true;

        for (int i = 2; i < era.length; i++) {
            if (era[i]) {
                continue;
            }
            for (int j = i + i; j < era.length; j = j + i) {
                if (era[j]) {
                    continue;
                } else {
                    era[j] = true;
                }
            }
        }

        String[] info = numbers.split("");

        for (int i = 1; i <= 7 ; i++) {
            recursion(0, info, i);
        }

        return result;
    }

    public void recursion(int index, String[] info, int limit) {

        if (index == limit) {
            String temp = "";
            for (int i = 0; i < index; i++) {
                if (i == 0 && s[i].equals("0")) {
                    continue;
                } else {
                    temp += s[i];
                }
            }

            if (temp.equals("")) {
                return;
            } else if (!era[Integer.parseInt(temp)]) {
                era[Integer.parseInt(temp)] = true;
                result++;
                return;
            }
        }

        for (int i = 0; i < info.length; i++) {

            if (chk[i]) {
                continue;
            }

            s[index] = info[i];
            chk[i] = true;
            recursion(index + 1, info, limit);
            chk[i] = false;

        }

    }
}