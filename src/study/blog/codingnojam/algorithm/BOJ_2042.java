package study.blog.codingnojam.algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2042 {

    public static void main(String[] args) throws IOException {
        // 입출력을 위한 변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 문제풀이를 위한 기본 정보 받기
        String[] info = br.readLine().split(" ");

        // 배열 변수 선언 (배열의 0인덱스는 안 쓸려고 길이 1추가)
        long[] arr = new long[Integer.parseInt(info[0])+1];

        // 배열 값 초기화
        for (int i = 1; i <= Integer.parseInt(info[0]); i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 생성
        SegmentTree st = new SegmentTree(Integer.parseInt(info[0]));
        // 세그먼트 트리 값 초기화
        st.init(arr, 1, 1, Integer.parseInt(info[0]));

        // 문제에서 주어진 구간 합, 특정 인덱스 값 변경 작업의 횟수만큼 반복
        for (int i = 0; i < Integer.parseInt(info[1]) + Integer.parseInt(info[2]); i++) {
            String[] operation = br.readLine().split(" ");

            // 배열의 특정 인덱스의 값을 변경하는 경우
            if(Integer.parseInt(operation[0]) == 1){
                // 세그먼트 트리의 노드 값을 변경하는 2가지의 방법
                // 1. 기존 값과 변경할 값의 차이를 구해서 트리의 노드 값 변경
                long diff = Long.parseLong(operation[2]) - arr[Integer.parseInt(operation[1])];
                arr[Integer.parseInt(operation[1])] = Long.parseLong(operation[2]);
                st.update(1, 1, Integer.parseInt(info[0]), Integer.parseInt(operation[1]), diff );

                // 2. 변경할 값만 가지고 트리의 노드 값 변경
                st.update2(1, 1, Integer.parseInt(info[0]), Integer.parseInt(operation[1]), Long.parseLong(operation[2]));
            }else{
                // 배열의 특정 구간 합 구하기
                long result = st.sum(1, 1, Integer.parseInt(info[0]), Integer.parseInt(operation[1]), Integer.parseInt(operation[2]));

                // 문제에서 주어진 조건에 맞게 출력할 값 저장
                bw.write(String.valueOf(result));
                bw.newLine();
            }
        }

        // 값 출력
        br.close();
        bw.flush();
        bw.close();

    }

    static class SegmentTree{
        // 세그먼트 트리를 구현할 배열
        private long[] tree;

        // 생성자에서 세그먼트 트리의 전체노드 수 계산 (즉, 배열 길이)
        SegmentTree(int n) {
            // 트리의 높이 계산
            double treeHeight = Math.ceil(Math.log(n)/Math.log(2))+1;
            // 트리의 노드 수 계산
            long treeNodeCount = Math.round(Math.pow(2, treeHeight));
            // 트리의 길이 설정
            tree = new long[Math.toIntExact(treeNodeCount)];
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

        void update(int node, int start, int end, int index, long diff) {
            if (index < start || end < index) {
                return;
            }else {
                tree[node] = tree[node] + diff;
                if (start != end) {
                    update(node*2, start, (start+end)/2, index, diff) ;
                    update(node*2+1, (start+end)/2+1, end, index, diff) ;
                }
            }
        }

        long update2(int node, int start, int end, int index, long changeValue) {
            if (index < start || end < index) {
                return tree[node];
            } else if (start == index && end == index) {
                return tree[node] = changeValue;
            } else {
                return tree[node] = update2(node * 2, start, (start + end) / 2, index, changeValue) +
                        update2(node * 2 + 1, (start + end) / 2 + 1, end, index, changeValue);

            }
        }
    }
}

