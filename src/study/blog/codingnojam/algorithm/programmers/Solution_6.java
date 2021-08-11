package study.blog.codingnojam.algorithm.programmers;

import java.util.HashMap;

// 2021 상반기 웹 백앤드 데브매칭 다단계 칫솔 판매
class Solution_6 {

    static HashMap<String, Man> db = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        db.put("center", new Man(null));
        for (int i = 0; i < enroll.length; i++) {
            db.put(enroll[i], new Man(referral[i].equals("-") ? "center" : referral[i]));
        }
        for (int i = 0; i < seller.length; i++) {
            int salePrice = amount[i] * 100;
            recursion(seller[i], salePrice);
        }
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = db.get(enroll[i]).profit;
        }
        return answer;
    }

    public void recursion(String name, int profit) {
        if (name.equals("center")) {
            Man center = db.get(name);
            center.profit += profit;
            return;
        }
        int parentProfit = profit / 10;
        int sellerProfit = profit - parentProfit;
        Man seller = db.get(name);
        seller.profit += sellerProfit;
        if (parentProfit > 0) {
            recursion(seller.parent, parentProfit);
        } else {
            return;
        }
    }

    class Man {
        String parent;
        int profit;

        public Man(String parent) {
            this.parent = parent;
        }
    }
}