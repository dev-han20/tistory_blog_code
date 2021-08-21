package study.blog.codingnojam.algorithm.programmers.codingtest;

class 카카오뱅크1 {

    public int solution(String[] deposits) {
        int answer = 0;

        int[] redate = new int[13];
        redate[1] = 1;
        redate[2] = 32;
        redate[3] = 60;
        redate[4] = 91;
        redate[5] = 121;
        redate[6] = 152;
        redate[7] = 182;
        redate[8] = 213;
        redate[9] = 244;
        redate[10] = 274;
        redate[11] = 305;
        redate[12] = 335;

        int money = 0;
        int R =0;

        for (int i = 0; i < deposits.length; i++) {
            String[] info = deposits[i].split(" ");
            double r = Double.parseDouble(info[1])/100;
            double m = Double.parseDouble(info[2]);
            int rday = redate[Integer.parseInt(info[0].substring(0,2))];
            int day = Integer.parseInt(info[0].substring(3,5));


            money += m;
            R += (int)((m * r) * (365 - (double)rday - (double)day + 1) / 365.0);

        }
        answer = money + R;
        return answer;
    }
}