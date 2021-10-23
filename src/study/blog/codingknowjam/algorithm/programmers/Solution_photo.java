package study.blog.codingknowjam.algorithm.programmers;

// 2021 카카오 코드 본선 - 사진찍기
class Solution_photo {
    static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static boolean[] useChk = new boolean[friends.length];
    static int count = 0;
//    List<String> result = new ArrayList<>();
    static String[] result = new String[8];

    public static void main(String[] args) {
        String[] s = {"N~F=0", "R~T>2"};
        System.out.println(solution(2,s));
    }


    public static int solution(int n, String[] data) {

        recursion(data, 0);

        return count;
    }

    public static void recursion(String[] data, int index) {

        if (index == 8) {
            boolean chk = false;
            for (int i = 0; i < data.length; i++) {
                System.out.println(i);
                String[] info = data[i].split("");
                int index1 = -1;
                int index2 = -1;

                String temp ="";
                for (String s : result) {
                    temp += s;
                }


                index1 = temp.indexOf(data[i].charAt(0));
                index2 = temp.indexOf(data[i].charAt(2));
//                System.out.println("index1 = " + index1);
//                System.out.println("index1 = " + index2);

//                for (int j = 0; j < result.length; j++) {
//
////                    if (info[0].equals(result[j])) {
////                        index1 = j;
////                    }else if (info[2].equals(result[j])) {
////                        index2 = j;
////                    }
//
//                    if (index1 >= 0 && index2 >= 0) {
//                        break;
//                    }
//                }
                int distance = Math.abs(index1 - index2) - 1;
//                int d = Integer.parseInt(info[4]);
                int d = data[i].charAt(4)-48;
                System.out.println(d);
                if (data[i].charAt(3) == '=') {
                    chk = distance == d ? true : false;
                } else if (data[i].charAt(3) == '>') {
                    chk = distance > d ? true : false;
                } else {
                    chk = distance < d ? true : false;
                }

                if (!chk) {
                    return;
                }
            }

            count++;
            return;
        }

        for (int i = 0; i < friends.length; i++) {
            if (useChk[i]) {
                continue;
            } else {
                result[index] = friends[i];
                useChk[i] = true;
                recursion(data, index + 1);
                useChk[i] = false;
            }
//            result.remove(result.size() - 1);
        }
    }
}