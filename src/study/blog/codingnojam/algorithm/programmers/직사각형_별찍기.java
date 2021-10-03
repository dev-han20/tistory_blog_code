package study.blog.codingnojam.algorithm.programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class 직사각형_별찍기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}