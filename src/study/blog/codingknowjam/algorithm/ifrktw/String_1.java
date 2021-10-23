package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class String_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int result = 0;
        String[] target = sc.nextLine().toUpperCase().split("");
        String str  = sc.nextLine().toUpperCase();
        for (int i = 0; i < target.length; i++) {
            if (target[i].equals(str)) {
                result++;
            }
        }
        System.out.println(result);
    }
}
