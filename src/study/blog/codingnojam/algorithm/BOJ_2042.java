package study.blog.codingnojam.algorithm;

import java.io.*;

public class BOJ_2042 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = br.readLine().split(" ");
        // 0인덱스는 사용안하기 위해 배열길이 1 추가
        int[] arr = new int[Integer.parseInt(info[0])+1];
        for (int i = 1; i <= Integer.parseInt(info[0]); i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree st = new SegmentTree(10);
    }

    static class SegmentTree{
        private long treeNodeCount;

        // 생성자에서 트리의 전체노드개수 계산
        SegmentTree(int arrLength) {
            double treeHeight = Math.log(arrLength)/Math.log(2)+1;
            treeNodeCount = Math.round(Math.pow(2, treeHeight)-1);
        }



    }


}

