package study.blog.codingnojam.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int L;
    static int passwordCount = 0;
    static int vowelCount = 0;
    static int consCount = 0;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split(" ");
        boolean[] chk = new boolean[arr.length];
        Arrays.sort(arr);
        passwordSearch(arr, chk);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void passwordSearch(String[] arr, boolean[] chk) throws IOException {
        if(passwordCount >= L){
            if (vowelCount >= 1 && consCount >= 2) {
                bw.write(sb.toString());
                bw.newLine();
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (chk[i]) {
                continue;
            }else{
                if (sb.toString().length() >= 1) {
                    if (sb.toString().charAt(passwordCount - 1) > arr[i].charAt(0)) {
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
            passwordCount++;
            chk[i] = true;
            passwordSearch(arr, chk);
            sb.deleteCharAt(passwordCount - 1);
            if (arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                vowelCount--;
            } else {
                consCount--;
            }
            passwordCount--;
            chk[i] = false;
        }
    }

}
