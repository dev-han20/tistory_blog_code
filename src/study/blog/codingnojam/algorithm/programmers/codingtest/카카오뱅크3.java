package study.blog.codingnojam.algorithm.programmers.codingtest;

class 카카오뱅크3 {

    int result = 0;

    public int solution(int n, int[] price) {
        int stoneCount = 0;
        int money = 0;

        int index =0 ;
        for (int i = 0; i < price.length; i++) {
            if (price[i] == 0) {
                stoneCount++;
                index = i;
                break;
            }
        }

        recursion(n, price, index+1, money, stoneCount);

        return result;
    }

    public void recursion(int limit, int[] price, int index, int money, int stoneCount) {

        if (index == limit) {
            result = Math.max(result, money);
            return;
        }

        for (int i = 0; i < 3; i++) {
            // 0 : 산다 1넘어간다 2 판다
            if (i == 0) {
                if (price[index] <= money) {
                    money -= price[index];
                    stoneCount++;
                    recursion(limit, price, index + 1, money, stoneCount);
                }

            } else if (i == 1) {
                if (price[index] == 0) {
                    continue;
                } else {
                    recursion(limit, price, index + 1, money, stoneCount);
                }
            } else {
                if (price[index] != 0 & stoneCount > 0) {
                    money = money + (price[index] * stoneCount);
                        stoneCount =0;
                        recursion(limit, price, index + 1, money, stoneCount);
                }
            }


        }


    }
}