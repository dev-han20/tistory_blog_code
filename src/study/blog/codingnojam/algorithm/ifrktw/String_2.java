package study.blog.codingnojam.algorithm.ifrktw;

import java.util.Scanner;

public class String_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        System.out.println(sb.toString());
    }
}
