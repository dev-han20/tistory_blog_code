package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준온라인저지 17140번 이차원 배열과 연산 문제풀이 Java
public class BOJ_17140 {

    public static void main(String[] args) throws IOException {

        // 입력을 받기 위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 주어진 정보 배열로 받아서 변수에 초기화
        String[] info = br.readLine().split(" ");
        int r = Integer.parseInt(info[0]);
        int c = Integer.parseInt(info[1]);
        int k = Integer.parseInt(info[2]);

        // 연산하면서 값을 저장할 배열
        int[][] arr = new int[101][101];

        // 주어진 정보대로 배열 값 초기화
        for (int i = 1; i < 4; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j < 4; j++) {
                arr[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        // 시간
        int time = 0;
        // 초기 주어진 행 개수
        int rowCount = 3;
        // 초기 주어진 열 개수
        int colCount = 3;

        // 연산 시작
        while (true) {

            // 현재 배열의 r행 c열의 값이 k라면 연산 종료
            if (arr[r][c] == k) {
                break;
            }

            // 계속 연산해서 시간이 100초가 되면 연산 종료
            if (time == 100) {
                time = -1;
                break;
            }

            // 시간 1초 증가
            time++;

            // 행의 개수 >= 열의 개수
            if (rowCount >= colCount) {

                // 연산에 사용할 변수 생성 후 현재 열의 개수 저장
                // 왜냐하면 colCount변수는 연산 종료 후에 최대 열의 길이를 저장해야하므로
                // 연산 중간에 열의 길이가 바뀌는 것을 고려해서 연산에 사용할 현재 열의 개수는따로 저장
                int curColCount = colCount;
                // 연산 종료후 최대 열의 길이를 저장하기 위해 0으로 값 변경
                colCount = 0;

                // 현재 행의 개수만큼 반복
                for (int i = 1; i <= rowCount; i++) {
                    // 등장한 횟수를 저장하기 위한 해쉬 맵
                    HashMap<Integer, Integer> dupCount = new HashMap<>();
                    // 등장한 횟수 밑 수의 크기에 따라 정렬하기위해 사용할 리스트
                    List<Node> list = new ArrayList<>();

                    // 현재 열의 길이까지 반복
                        for (int j = 1; j <= curColCount; j++) {
                        // 배열의 값이 0이 아닐 때만 맵에 저장(key : 숫자값, value : 숫자가 등장한 횟수)
                        if (arr[i][j] != 0) {
                            dupCount.put(arr[i][j], dupCount.getOrDefault(arr[i][j], 0) + 1);
                        }
                    }

                    // 맵이 만약에 비어있다면 다음 행으로 이동
                    if (dupCount.isEmpty()) {
                        continue;
                    }

                    // 맵을 전체 순회해서 리스트에 저장 (노드 클래스는 숫자값, 등장횟수를 가지고 있음)
                    for (Map.Entry<Integer, Integer> e : dupCount.entrySet()) {
                        list.add(new Node(e.getKey(), e.getValue()));
                    }

                    // 리스트 정렬(등장횟수 오름차순, 등장횟수가 같으면 숫자 오름차순)
                    Collections.sort(list, (o1, o2) -> {
                            if (o1.count > o2.count) {
                                return 1;
                            } else if (o1.count == o2.count) {
                                if (o1.value > o2.value) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                return -1;
                            }
                    });

                    // 연산 후 열의 길이를 저장하기 위한 인덱스변수
                    int index = 0;
                    // 리스트의 사이즈만큼 반복문 수행
                    for (int j = 0; j < list.size() ; j++) {
                        // 열의 길이가 100이 넘어가면 그 이후는 버리기 위해 49번 인덱스까지만 배열에 값 저장
                        if (j == 50) {
                            break;
                        }
                        // 저장할 배열의 인덱스 계산
                        int length = (j + 1) * 2;
                        // 앞서 계산한 인덱스가 결국 열의 최대 길이가 되므로 해당 값으로 갱신
                        index = length;
                        // 리스트에서 노드 꺼내기
                        Node node = list.get(j);
                        // 계산한 인덱스의 위치에 꺼낸 노드의 값들 저장
                        arr[i][(j + 1) * 2 - 1] = node.value;
                        arr[i][(j + 1) * 2] = node.count;
                    }

                    // 하나의 행을 연산 후에 얻은 열의 길이와 현재까지 갱신된 열의길이 중 더 큰값으로 갱신
                    colCount = Math.max(colCount, index);

                    // 연산 후 열의 길이가 줄어들었다면 이전에 저장된 값을 0으로 바꿔주기 위해 반복문 수행
                    // 앞서 계산한 연산 후 열의 길이+1번 인덱스부터 연산 전 열의길이까지 반복문 수행
                    for (int j = index+1; j <= curColCount; j++) {
                        // 값이 0이 아닌 것들을 0으로 변경
                        if (arr[i][j] != 0) {
                            arr[i][j] = 0;
                        }
                    }
                }
            } else { // 행의 개수 < 열의 개수

                // 연산에 사용할 변수 생성 후 현재 행의 개수 저장
                // 왜냐하면 rowCount변수는 연산 종료 후에 최대 행의 길이를 저장해야하므로
                // 연산 중간에 행의 길이가 바뀌는 것을 고려해서 연산에 사용할 현재 행의 개수는따로 저장
                int curRowCount = rowCount;
                // 연산 종료후 최대 행의 길이를 저장하기 위해 0으로 값 변경
                rowCount = 0;

                // 현재 열의 개수만큼 반복
                for (int j = 1; j <= colCount; j++) {
                    // 등장한 횟수를 저장하기 위한 해쉬 맵
                    HashMap<Integer, Integer> dupCount = new HashMap<>();
                    // 등장한 횟수 밑 수의 크기에 따라 정렬하기위해 사용할 리스트
                    List<Node> list = new ArrayList<>();

                    // 현재 행의 길이까지 반복
                    for (int i = 1; i <= curRowCount; i++) {
                        // 배열의 값이 0이 아닐 때만 맵에 저장(key : 숫자값, value : 숫자가 등장한 횟수)
                        if (arr[i][j] != 0) {
                            dupCount.put(arr[i][j], dupCount.getOrDefault(arr[i][j], 0) + 1);
                        }
                    }

                    // 맵이 비어있다면 다음 열 탐색
                    if (dupCount.isEmpty()) {
                        continue;
                    }

                    // 맵을 전체 순회해서 리스트에 저장 (노드 클래스는 숫자값, 등장횟수를 가지고 있음)
                    for (Map.Entry<Integer, Integer> e : dupCount.entrySet()) {
                        list.add(new Node(e.getKey(), e.getValue()));
                    }

                    // 리스트 정렬(등장횟수 오름차순, 등장횟수가 같으면 숫자 오름차순)
                    Collections.sort(list, (o1, o2) -> {
                        if (o1.count > o2.count) {
                            return 1;
                        } else if (o1.count == o2.count) {
                            if (o1.value > o2.value) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else {
                            return -1;
                        }
                    });

                    // 연산 후 행의 길이를 저장하기 위한 인덱스변수
                    int index = 0;
                    // 리스트의 사이즈만큼 반복문 수행
                    for (int i = 0; i < list.size() ; i++) {
                        // 행의 길이가 100이 넘어가면 그 이후는 버리기 위해 49번 인덱스까지만 배열에 값 저장
                        if (i == 50) {
                            break;
                        }
                        // 저장할 배열의 인덱스 계산
                        int length = (i + 1) * 2;
                        // 앞서 계산한 인덱스가 결국 연산 후 행의 최대 길이가 되므로 해당 값으로 갱신
                        index = length;
                        // 리스트에서 노드 꺼내기
                        Node node = list.get(i);
                        // 계산한 인덱스의 위치에 꺼낸 노드의 값들 저장
                        arr[length-1][j] = node.value;
                        arr[length][j] = node.count;
                    }

                    // 하나의 열을 연산 후에 얻은 행의 길이와 현재까지 갱신된 행의길이 중 더 큰값으로 갱신
                    rowCount = Math.max(rowCount, index);

                    // 연산 후 행의 길이가 줄어들었다면 이전에 저장된 값을 0으로 바꿔주기 위해 반복문 수행
                    // 앞서 계산한 연산 후 행의 길이+1번 인덱스부터 연산 전 행의길이까지 반복문 수행
                    for (int i = index+1; i <= curRowCount; i++) {
                        // 값이 0이 아닌 것들을 0으로 변경
                        if (arr[i][j] != 0) {
                            arr[i][j] = 0;
                        }
                    }
                }
            }
        }

        // 연산 종료후 시간 출력
        System.out.println(time);
    }

    // 정렬에 사용하기 위한 노드 클래스
    static class Node {
        // 숫자를 저장할 변수
        int value;
        // 숫자가 등장한 횟수를 저장할 변수
        int count;

        public Node(int value, int count){
            this.value = value;
            this.count = count;
        }
    }
}
