package study.blog.codingnojam.algorithm.programmers.codingtest;

class 토스1 {

    public static void main(String[] args) {
        System.out.println(Math.ceil(12.9));
    }
    public long solution(long orderAmount, long taxFreeAmount, long serviceFee) {
        // orderAmount : 주문금액
        // taxFreeAmount : 비과세금액
        // serviceFee : 봉사료
        long answer = 0;

        //부가가치세 = 과세금액 * 10%
        // 과세금액 = 공급가액 - 비과세 금액(부가가치세가 부과되지 안흔 ㄴ금액)
        // 공급가액 + 부가가치세 = 공급대가

        long supply = 0;

        // 봉사료가 없다면 공급대가 = 주무금액
        if (serviceFee == 0) {
            supply = orderAmount;
        } else {
            // 봉사료가 있다면 주문금액 - 봉사료 =  공급대가
            supply = orderAmount - serviceFee;
        }

        if (supply - taxFreeAmount == 1) {
            answer = 0;
        } else {
            double temp = ((double)supply - (double)taxFreeAmount) * 0.1 ;
            answer = (long)Math.ceil(temp);
        }
        return answer;
    }
}