package study.blog.codingnojam.algorithm;

import java.io.*;

public class BOJ_2457 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = br.readLine().split(" ");

        int n = Integer.parseInt(info[0]);
        int m = Integer.parseInt(info[1]);

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        double treeHeight = Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeNodeCount = Math.toIntExact(Math.round(Math.pow(2, treeHeight)));
        int[] minSegmentTree = new int[treeNodeCount];
        int[] maxSegmentTree = new int[treeNodeCount];

        init(arr, minSegmentTree, 1, 0, n - 1, "min");
        init(arr, maxSegmentTree, 1, 0, n - 1, "max");

        for (int i = 0; i < minSegmentTree.length; i++) {
            System.out.print("min["+ i + "] = " + minSegmentTree[i]);
        }

            System.out.println();
        for (int i = 0; i < minSegmentTree.length; i++) {
            System.out.print("max["+ i + "] = " + maxSegmentTree[i]);
        }
//
//        for (int i = 0; i < m; i++) {
//            String[] range = br.readLine().split(" ");
//            int left = Integer.parseInt(range[0])-1;
//            int right = Integer.parseInt(range[1])-1;
//            int minValue = getValue(minSegmentTree, 1, 0, n - 1, left, right, "min");
//            int maxValue = getValue(maxSegmentTree, 1, 0, n - 1, left, right, "max");
//            bw.write(String.valueOf(minValue) + " " + String.valueOf(maxValue));
//            bw.newLine();
//        }
//
//        br.close();
//        bw.flush();
//        bw.close();
    }

    public static int init(int[] arr, int[] segmentTree, int node, int start, int end, String minOrMax) {
        if (start == end) {
            return segmentTree[node] = arr[start];
        } else {
            int leftNodeValue = init(arr, segmentTree, node * 2, start, (start + end) / 2, minOrMax);
            int rightNodeValue = init(arr, segmentTree, (node * 2) + 1, (start + end) / 2 + 1, end, minOrMax);
            if (minOrMax.equals("min")) {
                return Math.min(leftNodeValue, rightNodeValue);
            } else {
                return Math.max(leftNodeValue, rightNodeValue);
            }
        }
    }

    public static int getValue(int[] segmentTree, int node, int start, int end, int left, int right, String minOrMax) {
        if (end < left || right < start) {
            if (minOrMax.equals("min")) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (left <= start && end <= right) {
            return segmentTree[node];
        } else {
            int leftNodeValue = getValue(segmentTree, node * 2, start, (start + end) / 2, left, right, minOrMax);
            int rightNodeValue = getValue(segmentTree, (node * 2) + 1, (start + end) / 2 + 1, end, left, right, minOrMax);
            if (minOrMax.equals("min")) {
                return Math.min(leftNodeValue, rightNodeValue);
            } else {
                return Math.max(leftNodeValue, rightNodeValue);
            }
        }
    }


}
