package study.blog.codingnojam.algorithm.ifrktw;

import java.util.LinkedList;
import java.util.Scanner;

public class DFS_1 {
    static LinkedList<Integer> list = new LinkedList<>();
    static int half =0;
    static boolean chk;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            half += arr[i];
        }
        if (half % 2 != 0) {
            System.out.println("NO");
        } else {
            half = half / 2;
            recursion(0, arr);
            System.out.println(chk ? "YES" : "NO");
        }

    }

    public static void recursion(int index, int[] arr) {
        if(index >= arr.length || chk){
            return;
        }

        for (int i = index; i < arr.length; i++) {
            list.add(arr[i]);
            int sum = 0;
            for (int num : list) {
                sum += num;
            }
            if(sum == half){
                chk = true;
                return;
            }
            recursion(i + 1, arr);
            list.removeLast();
        }

    }
}
