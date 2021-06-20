package study.blog.codingnojam.algorithm;

import java.io.*;

public class BOJ_2357 {

    public static void main(String[] args) throws IOException {

        // 입출력을 위한 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 문제풀이를 위한 정보 입력 받기
        String[] info = br.readLine().split(" ");
        // 정수의 개수
        int n = Integer.parseInt(info[0]);
        // 최솟,최댓값 찾는 횟수
        int m = Integer.parseInt(info[1]);

        // 배열 생성
        int[] arr = new int[n];

        // 배열 초기화
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 세그먼트 트리 전체 노드 수 계산
        // 세그먼트 트리의 높이 = logN + 1 (밑이 2인 로그)
        double treeHeight = Math.ceil(Math.log(n) / Math.log(2)) + 1;
        // 세그먼트 트리의 전체 노드 수 = 2^트리의 높이
        int treeNodeCount = Math.toIntExact(Math.round(Math.pow(2, treeHeight)));

        // 최솟 값 세그먼트 트리 생성
        int[] minSegmentTree = new int[treeNodeCount];
        // 최댓 값 세그먼트 트리 생성
        int[] maxSegmentTree = new int[treeNodeCount];

        // 최솟 값 세그먼트 트리 초기화
        init(arr, minSegmentTree, 1, 0, n - 1, "min");
        // 최댓 값 세그먼트 트리 초기화
        init(arr, maxSegmentTree, 1, 0, n - 1, "max");

        // 주어진 범위 안에서 최솟,최댓값 구하기
        for (int i = 0; i < m; i++) {
            // 문제에서 주어지는 범위 받기
            String[] range = br.readLine().split(" ");
            // 범위의 시작 인덱스
            int left = Integer.parseInt(range[0])-1;
            // 범위의 끝 인덱스
            int right = Integer.parseInt(range[1])-1;

            // 최솟 값 세그먼트 트리에서 주어진 범위안에서의 최솟 값 얻기
            int minValue = getValue(minSegmentTree, 1, 0, n - 1, left, right, "min");
            // 최댓 값 세그먼트 트리에서 주어진 범위안에서의 최댓 값 얻기
            int maxValue = getValue(maxSegmentTree, 1, 0, n - 1, left, right, "max");

            // 최솟, 최댓 값 출력
            bw.write(String.valueOf(minValue) + " " + String.valueOf(maxValue));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    // 세그먼트 트리 초기화 메서드
    public static int init(int[] arr, int[] segmentTree, int node, int start, int end, String minOrMax) {
        // 현재 노드가 리프노드인 경우
        if (start == end) {
            // 현재 노드에 배열 값 저장
            return segmentTree[node] = arr[start];
        } else { // 현재 노드가 리프노드가 아닌 경우
            // 왼쪽 자식노드 탐색
            int leftNodeValue = init(arr, segmentTree, node * 2, start, (start + end) / 2, minOrMax);
            // 오른쪽 자식노드 탐색
            int rightNodeValue = init(arr, segmentTree, (node * 2) + 1, (start + end) / 2 + 1, end, minOrMax);
            // 현재 초기화 하고있는 트리가 최솟인지 최댓인지 구별
            if (minOrMax.equals("min")) {
                // 최솟 값 세그먼트 트리인 경우 자식노드 중 작은 값을 가짐
                // 부모노드의 값 = Min(왼쪽 자식노드, 오른쪽 자식노드)
                return segmentTree[node] = Math.min(leftNodeValue, rightNodeValue);
            } else {
                // 최댓 값 세그먼트 트리인 경우 자식노드 중 큰 값을 가짐
                // 부모노드의 값 = Max(왼쪽 자식노드, 오른쪽 자식노드)
                return segmentTree[node] = Math.max(leftNodeValue, rightNodeValue);
            }
        }
    }

    // 세그먼트 트리를 통해 주어진 범위 안에서 최솟, 최댓 값 가져오기
    public static int getValue(int[] segmentTree, int node, int start, int end, int left, int right, String minOrMax) {
        // 최솟, 최댓 값을 탐색할 범위가 노드가 가지는 범위를 벗어난 경우
        if (end < left || right < start) {
            if (minOrMax.equals("min")) {
                // 최솟 값 세그먼트 트리를 탐색 중이라면 int타입의 가장 큰 수를 리턴
                return Integer.MAX_VALUE;
            } else {
                // 최댓 값 세그먼트 트리를 탐색 중이라면 int타입의 가장 작은 수를 리턴
                return Integer.MIN_VALUE;
            }
        } else if (left <= start && end <= right) {
            // 최솟, 최댓 값을 탐색할 범위가 노드가 가지는 범위보다 큰 경우 노드 값 리턴
            return segmentTree[node];
        } else {
            // 그 외의 경우는 2가지가 더 존재
            // 최솟, 최댓 값을 탐색할 범위보다 노드가 가지는 범위가 큰 경우
            // 최솟, 최댓 값을 탐색할 범위에 노드가 가지는 범위가 일부 포함 되는 경우
            // 위의 2가지 경우에 해당할 때는 자식노드를 탐색 후 자식노드의 값을 가져옴
            int leftNodeValue = getValue(segmentTree, node * 2, start, (start + end) / 2, left, right, minOrMax);
            int rightNodeValue = getValue(segmentTree, (node * 2) + 1, (start + end) / 2 + 1, end, left, right, minOrMax);
            // 현재 탐색 중인 세그먼트 트리가 최솟인지 최댓인지 구분
            if (minOrMax.equals("min")) {
                // 최솟 값 세그먼트 트리를 탐색 중이라면 최솟 값 리턴
                return Math.min(leftNodeValue, rightNodeValue);
            } else {
                // 최댓 값 세그먼트 트리를 탐색 중이라면 최댓 값 리턴
                return Math.max(leftNodeValue, rightNodeValue);
            }
        }
    }
}
