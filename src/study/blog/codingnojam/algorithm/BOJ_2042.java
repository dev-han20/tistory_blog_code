package study.blog.codingnojam.algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2042 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = br.readLine().split(" ");
        // 0인덱스는 사용안하기 위해 배열길이 1 추가
        long[] arr = new long[Integer.parseInt(info[0])+1];
        for (int i = 1; i <= Integer.parseInt(info[0]); i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree st = new SegmentTree(arr.length);




    }

    static class SegmentTree{
        private long treeNodeCount;
        private long[] tree;

        // 생성자에서 세그먼트트리의 전체노드개수 계산
        SegmentTree(int arrLength) {
            double treeHeight = Math.log(arrLength)/Math.log(2)+1;
            treeNodeCount = Math.round(Math.pow(2, treeHeight)-1);
            tree = new long[(int)treeNodeCount + 1];
        }

        // 트리 생성을 위한 초기화 메서드
        long init(long[] arr, int node, int start, int end ){
            if (start == end) {
                tree[node] = arr[start];
                return tree[node];
            }else{
                return tree[node] = init(arr, node*2, start, (start+end)/2) + init(arr, node*2+1, (start+end)/2+1, end);
            }
        }

        long sum(int node, int start, int end, int left, int right) {
            if (end < left || right < start) {
                return 0;
            } else if (left <= start && end <= right) {
                return tree[node];
            } else {
                return sum(node*2, start, (start+end)/2, left, right) + sum(node*2+1, (start+end)/2+1, end, left, right);
            }
        }




    }


}

