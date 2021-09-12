package study.blog.codingnojam.algorithm.ifrktw;

import java.util.Scanner;

public class CheatNote {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "cheatNote";
        char[] charArr = str.toCharArray(); // 스트링 캐릭터 배열로
        char c = 'c';
        c = (char)(c - 32);     // 대문자 소문자 아스키는 32차이 | (소문자-32 = 대문자)
        Character.isLowerCase(c); // 캐릭터 소문자 판단여부
        Character.isUpperCase(c); // 캐릭터 대문자 판단여부





    }
}
