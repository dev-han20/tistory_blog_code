package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 공통원소_구하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] nArr = new int[N];
        for (int i = 0; i < N; i++) {
            nArr[i] = sc.nextInt();
        }

        int M = sc.nextInt();
        int[] mArr = new int[M];
        for (int i = 0; i < M; i++) {
            mArr[i] = sc.nextInt();
        }
        Arrays.sort(nArr);
        Arrays.sort(mArr);
        int np = 0;
        int mp = 0;
        ArrayList<Integer> commonList = new ArrayList<>();
        while (np < N && mp < M) {
            if (nArr[np] == mArr[mp]) {
                commonList.add(nArr[np]);
                np++;
                mp++;
            } else if (nArr[np] < mArr[mp]) {
                np++;
            } else {
                mp++;
            }
        }
        for (Integer i : commonList) {
            System.out.print(i + " ");
        }

    }
}
