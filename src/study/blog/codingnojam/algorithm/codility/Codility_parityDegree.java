package study.blog.codingnojam.algorithm.codility;

class Codility_parityDegree {

    public int solution(int N) {
        int result = 0;
        double[] arr = new double[31];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.pow(2, i);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (N >= arr[i] && (N % arr[i]) == 0) {
                result = i;
                break;
            }

        }
        return result;
    }
}
