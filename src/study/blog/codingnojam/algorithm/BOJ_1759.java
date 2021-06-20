package study.blog.codingnojam.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int count = 0;
    static int L;
    static StringBuilder sb = new StringBuilder();
    static int vowelCount = 0;
    static int consCount = 0;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split(" ");
        boolean[] chk = new boolean[arr.length];
        Arrays.sort(arr);

//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("arr[i] = " + arr[i]);
//        }

        permutation(arr, chk);

        br.close();
        bw.flush();
        bw.close();


    }

    public static void permutation(String[] arr, boolean[] chk) throws IOException {
        if(count >= L){
            if (vowelCount >= 1 && consCount >= 2) {
//                for (char c = 0; c < sb.toString().length()-1; c++) {
//                    if (sb.toString().charAt(c) > sb.toString().charAt(c + 1)) {
//                        return;
//                    }
//                }
                bw.write(sb.toString());
                bw.newLine();
                return;
            } else {
                return;
            }

        }

        for (int i = 0; i < arr.length; i++) {
            if (chk[i]) {
                continue;
            }else{
                if (sb.toString().length() >= 1) {
                    if (sb.toString().charAt(count - 1) > arr[i].charAt(0)) {
                        continue;
                    }
                }
            }
            sb.append(arr[i]);
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                vowelCount++;
            } else {
                consCount++;
            }
            count++;
            chk[i] = true;
            permutation(arr, chk);
            sb.deleteCharAt(count - 1);
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                vowelCount--;
            } else {
                consCount--;
            }
            count--;
            chk[i] = false;
        }
    }

}
