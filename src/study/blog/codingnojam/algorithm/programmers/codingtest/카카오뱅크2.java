package study.blog.codingnojam.algorithm.programmers.codingtest;

class 카카오뱅크2 {

    int result = 0;
    public  int solution(int[][] needs, int r) {
        int answer = 0;

        int[] robot = new int[r];

        int n = needs[0].length;
        boolean[] chk = new boolean[n];

        recursion(0, r, n, chk, robot, needs);

        return result;
    }
    public   void recursion(int index, int limit, int n, boolean[] chk, int[] robot, int[][] needs) {
        if (index == limit) {
            int temp = 0;
            for (int i = 0; i < needs.length; i++) {
                boolean countChk = false;
                for (int j = 0; j < needs[i].length; j++) {
                    if (needs[i][j] == 1) {
                        boolean zeroChk = false;
                        for (int k = 0; k < robot.length; k++) {
                            if (robot[k] == j) {
                                zeroChk = true;
                                break;
                            }
                        }
                        if (!zeroChk) {
                            countChk = true;
                            break;
                        }
                    }
                }
                if (!countChk) {
                    temp++;
                }
            }
            result = Math.max(result, temp);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (chk[i]) {
                continue;
            }

            robot[index] = i;
            chk[i] = true;
            recursion(index + 1, limit, n, chk, robot, needs);
            chk[i] = false;

        }

    }
}