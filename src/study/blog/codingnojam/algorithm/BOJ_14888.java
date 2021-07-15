package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준온라인저지 14888번 연산자 끼워넣기 문제풀이
public class BOJ_14888 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] arr = br.readLine().split(" ");

        String[] op = br.readLine().split(" ");

        List<Integer> opList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(op[i]);
            while (temp != 0) {
                opList.add(i);
                temp--;
            }
        }

        for (int i :
                opList) {
            System.out.println("i = " + i);
        }















    }

}
