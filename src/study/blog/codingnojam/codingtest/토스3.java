package study.blog.codingnojam.codingtest;

class 토스3 {

    public boolean solution(String amountText) {
        boolean answer = true;
        String temp = amountText.replaceAll("[^0-9,]", "");

        if (amountText.length() == temp.length()) {
            if (temp.charAt(0) != '0') {
                if (temp.indexOf(",") == -1) {
                    answer = true;
                } else {
                    if (temp.indexOf(",") == 0 || temp.lastIndexOf(",") == temp.length()-1) {
                        answer = false;
                    } else {
                        int chk = temp.length()-4;
                        for (int i = temp.length()-1; i >= 0; i--) {
                            if (i != chk) {
                                if (temp.charAt(i) == ',') {
                                    answer = false;
                                    break;
                                }
                            }else{
                                chk = chk - 4;
                                if (temp.charAt(i) != ',') {
                                    answer = false;
                                    break;
                                }
                            }
                            if (i == 0) {
                                answer = true;
                            }
                        }
                    }
                }
            } else {
                if (temp.length() == 1) {
                    answer = true;
                } else {
                    answer = false;
                }

            }
        } else {
            answer = false;
        }
        return answer;
    }
}