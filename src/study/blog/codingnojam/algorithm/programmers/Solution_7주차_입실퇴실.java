package study.blog.codingnojam.algorithm.programmers;

class Solution_7주차_입실퇴실 {
    public int[] solution(int[] enter, int[] leave) {
        int[] stay = new int[enter.length + 1];
        int stayCount = 0;
        int j = 0;
        int[] result = new int[enter.length];
        for (int i = 0; i < enter.length; i++) {
            stay[enter[i]] = 1;
            stayCount++;
            if (stayCount >= 2) {
                for (int k = 0; k < stay.length; k++) {
                    if(stay[k] == 1){
                        if (result[k - 1] == 0) {
                            result[k - 1] = stayCount - 1;
                        } else {
                            result[k - 1]++;
                        }
                    }
                }
            }

            while (j < leave.length && stay[leave[j]] == 1 ) {
                stay[leave[j]] = 0;
                stayCount--;
                j++;
            }
        }

        return result;
    }
}