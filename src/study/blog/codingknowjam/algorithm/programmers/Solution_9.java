package study.blog.codingknowjam.algorithm.programmers;

// 2021 카카오 채용연계형 인턴십 숫자 문자열과 영단어 replace를 까먹다니..
class Solution_9 {
    public static void main(String[] args) {
        char[] arr = {'o', 'n'};
        System.out.println(arr[0] == 'o');
    }

    public int solution(String s) {

        int answer = 0;
        StringBuilder sb = new StringBuilder();

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'z') {
                i = i + 3;
                sb.append("0");
            } else if(arr[i] == 'o') {
                i = i + 2;
                sb.append("1");
            } else if(arr[i] == 't') {
                if (arr[i + 1] == 'w') {
                    i = i + 2;
                    sb.append("2");
                } else {
                    i = i + 4;
                    sb.append("3");
                }
            } else if(arr[i] == 'f') {
                if (arr[i + 1] == 'o') {
                    i = i + 3;
                    sb.append("4");
                } else {
                    i = i + 3;
                    sb.append("5");
                }
            } else if(arr[i] == 's') {
                if (arr[i + 1] == 'i') {
                    i = i + 2;
                    sb.append("6");
                } else {
                    i = i + 4;
                    sb.append("7");
                }
            } else if(arr[i] == 'e') {
                i = i + 4;
                sb.append("8");
            } else if(arr[i] == 'n') {
                i = i + 3;
                sb.append("9");
            } else {
                sb.append(String.valueOf(arr[i]));
            }
        }

        answer = Integer.parseInt(sb.toString());
        return answer;
    }
}