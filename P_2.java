package study.blog.codingnojam.algorithm;

import java.util.Arrays;

public class P_2 {
    static int count = 0;
    static int answer = 0;
    public static void main(String[] args) {
//        int[][] orders = {{10, 60}, {20, 30}, {20, 80}, {30, 40}, {35, 70}, {40, 20}};
//        sol( orders);
        int[] arr = {1, 0, 0, 1, 0, 0, 1, 0};
        int[] arr2 = {0, 0, 0, 1};
        int[] arr3 = {0, 1, 1, 0, 0, 1, 1, 0};
        System.out.println(sol(arr3));

    }

    static int sol(int[] arr) {
        SegmentTree sg = new SegmentTree(arr.length);
        sg.init(arr,1,0, arr.length-1);
        answer = count;
//        System.out.println(count);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count = 0;
                arr[i] = 1;
                sg.init(arr,1,0, arr.length-1);
                answer = Math.max(answer, count);
                arr[i] = 0;
            }
        }
        return answer         ;

    }

    static class SegmentTree{
        // 세그먼트 트리를 구현할 배열
        private boolean[] tree;
        private int count = 0;
        // 생성자에서 세그먼트 트리의 전체노드 수 계산 (즉, 배열 길이)
        SegmentTree(int n) {
            // 트리의 높이 계산
            double treeHeight = Math.ceil(Math.log(n)/Math.log(2))+1;
            // 트리의 노드 수 계산
            long treeNodeCount = Math.round(Math.pow(2, treeHeight));
            // 트리의 길이 설정
            tree = new boolean[Math.toIntExact(treeNodeCount)];
        }

        // 세그먼트 트리의 노드 값 초기화
        boolean init(int[] arr, int node, int start, int end ){

            // 세그먼트 트리의 리프노드인 경우
            if (start == end) {
                // 리프노드에 배열의 값 저장 후 리턴
                if (arr[start] == 1) {
                    return tree[node] = true;
                } else {
                    return tree[node] = false;
                }
            }else{
                // 리프노드가 아닌 경우에는 자식노드의 값을 더해서 노드의 값 초기화 후 리턴
                boolean temp1 = init(arr, node*2, start, (start+end)/2);
                boolean temp2 = init(arr, node*2+1, (start+end)/2+1, end);
                if (temp1 || temp2) {
                    count++;
                    return tree[node] = true;
                } else {
                    return tree[node] = false;
                }
            }
        }

    }
}
