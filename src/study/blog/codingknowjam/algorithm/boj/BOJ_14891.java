package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14891 {


    // 백준온라인저지 14891 톱니바퀴 Java풀이
    public static void main(String[] args) throws IOException {

        // 입력받을 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 4개 초기화
        Gear gearOne = new Gear(br.readLine().split(""));
        Gear gearTwo = new Gear(br.readLine().split(""));
        Gear gearThree = new Gear(br.readLine().split(""));
        Gear gearFour = new Gear(br.readLine().split(""));

        // 테스트 케이스 개수
        int K = Integer.parseInt(br.readLine());

        // 결과 값
        int result = 0;

        // 테스트 케이스 수만큼 반복
        for (int i = 0; i < K; i++) {
            // 문제에서 주어진 정보 받기
            String[] info = br.readLine().split(" ");

            // 톱니바퀴 별로 회전을 어떻게 할지 저장할 배열
            // 0 : 회전 안함, 1 : 시계방향 회전, -1 : 반시계방향 회전
            int[] chk = new int[4];

            // 1번 톱니바퀴가 회전하는 경우
            if (info[0].equals("1")) {
                // 1번 톱니바퀴 회전 방향 저장
                chk[0] = Integer.parseInt(info[1]);
                // 1번 톱니바퀴와 2번 톱니바퀴가 서로 다른 극인 경우
                if (!gearOne.arr[gearOne.right].equals(gearTwo.arr[gearTwo.left])) {
                    // 2번 톱니바퀴의 회전 방향 저장(1번 톱니바퀴의 반대방향으로)
                    chk[1] = 0 - chk[0];
                    // 2번 톱니바퀴와 3번 톱니바퀴가 서로 다른 극인 경우
                    if (!gearTwo.arr[gearTwo.right].equals(gearThree.arr[gearThree.left])) {
                        // 3번 톱니바퀴의 회전 방향 저장(2번 톱니바퀴의 반대방향으로)
                        chk[2] = 0 - chk[1];
                        // 3번 톱니바퀴와 4번 톱니바퀴가 서로 다른 극인 경우
                        if (!gearThree.arr[gearThree.right].equals(gearFour.arr[gearFour.left])) {
                            // 4번 톱니바퀴의 회전 방향 저장(3번 톱니바퀴의 반대방향으로)
                            chk[3] = 0 - chk[2];
                        }
                    }
                }
            }
            // 2번 톱니바퀴가 회전하는 경우
            else if (info[0].equals("2")) {
                // 2번 톱니바퀴 회전 방향 저장
                chk[1] = Integer.parseInt(info[1]);
                // 2번 톱니바퀴와 3번 톱니바퀴가 서로 다른 극인 경우
                if (!gearTwo.arr[gearTwo.right].equals(gearThree.arr[gearThree.left])) {
                    // 3번 톱니바퀴의 회전 방향 저장(2번 톱니바퀴의 반대방향으로)
                    chk[2] = 0 - chk[1];
                    // 3번 톱니바퀴와 4번 톱니바퀴가 서로 다른 극인 경우
                    if (!gearThree.arr[gearThree.right].equals(gearFour.arr[gearFour.left])) {
                        // 4번 톱니바퀴의 회전 방향 저장(3번 톱니바퀴의 반대방향으로)
                        chk[3] = 0 - chk[2];
                    }
                }
                // 2번 톱니바퀴와 1번 톱니바퀴가 서로 다른 극인 경우
                if (!gearTwo.arr[gearTwo.left].equals(gearOne.arr[gearOne.right])) {
                    // 1번 톱니바퀴의 회전 방향 저장(2번 톱니바퀴의 반대방향으로)
                    chk[0] = 0 - chk[1];
                }
            }
            // 3번 톱니바퀴가 회전하는 경우
            else if (info[0].equals("3")) {
                // 3번 톱니바퀴 회전 방향 저장
                chk[2] = Integer.parseInt(info[1]);
                // 3번 톱니바퀴와 4번 톱니바퀴가 서로 다른 극인 경우
                if (!gearThree.arr[gearThree.right].equals(gearFour.arr[gearFour.left])) {
                    // 4번 톱니바퀴의 회전 방향 저장(3번 톱니바퀴의 반대방향으로)
                    chk[3] = 0 - chk[2];
                }
                // 3번 톱니바퀴와 2번 톱니바퀴가 서로 다른 극인 경우
                if (!gearThree.arr[gearThree.left].equals(gearTwo.arr[gearTwo.right])) {
                    // 2번 톱니바퀴의 회전 방향 저장(3번 톱니바퀴의 반대방향으로)
                    chk[1] = 0 - chk[2];
                    // 2번 톱니바퀴와 1번 톱니바퀴가 서로 다른 극인 경우
                    if (!gearTwo.arr[gearTwo.left].equals(gearOne.arr[gearOne.right])) {
                        // 1번 톱니바퀴의 회전 방향 저장(2번 톱니바퀴의 반대방향으로)
                        chk[0] = 0 - chk[1];
                    }
                }
            }
            // 4번 톱니바퀴가 회전하는 경우
            else {
                // 4번 톱니바퀴 회전 방향 저장
                chk[3] = Integer.parseInt(info[1]);
                // 4번 톱니바퀴와 3번 톱니바퀴가 서로 다른 극인 경우
                if (!gearFour.arr[gearFour.left].equals(gearThree.arr[gearThree.right])) {
                    // 3번 톱니바퀴의 회전 방향 저장(4번 톱니바퀴의 반대방향으로)
                    chk[2] = 0 - chk[3];
                    // 3번 톱니바퀴와 2번 톱니바퀴가 서로 다른 극인 경우
                    if (!gearThree.arr[gearThree.left].equals(gearTwo.arr[gearTwo.right])) {
                        // 2번 톱니바퀴의 회전 방향 저장(3번 톱니바퀴의 반대방향으로)
                        chk[1] = 0 - chk[2];
                        // 2번 톱니바퀴와 1번 톱니바퀴가 서로 다른 극인 경우
                        if (!gearTwo.arr[gearTwo.left].equals(gearOne.arr[gearOne.right])) {
                            // 1번 톱니바퀴의 회전 방향 저장(2번 톱니바퀴의 반대방향으로)
                            chk[0] = 0 - chk[1];
                        }
                    }
                }
            }

            // chk가 0이면 회전을 안하는 경우
            // 0이 아니면 어느 방향으로든 회전을 하는 경우
            // 회전을 하는 경우에만 회전
            if (chk[0] != 0) {
                gearOne.rotate(chk[0]);
            }
            if (chk[1] != 0) {
                gearTwo.rotate(chk[1]);
            }
            if (chk[2] != 0) {
                gearThree.rotate(chk[2]);
            }
            if (chk[3] != 0) {
                gearFour.rotate(chk[3]);
            }
        }

        // 테스트케이스가 종료되고 각 톱니바퀴 12시방향의 극에 따라 점수 계산
        if (gearOne.arr[gearOne.top].equals("1")) {
            result = result + 1;
        }
        if (gearTwo.arr[gearTwo.top].equals("1")) {
            result = result + 2;
        }
        if (gearThree.arr[gearThree.top].equals("1")) {
            result = result + 4;
        }
        if (gearFour.arr[gearFour.top].equals("1")) {
            result = result + 8;
        }

        // 출력
        System.out.println(result);
    }

    // 톱니바퀴
    static class Gear {
        // 톱니바퀴의 극 정보를 저장할 배열
        String[] arr = new String[8];
        // 톱니바퀴의 12시, 9시, 3시방향 인덱스
        int top, left, right;

        // 톱니바퀴 객체 생성할 때 각각의 변수들 초기화
        public Gear(String[] arr) {
            this.arr = arr;
            this.top = 0;
            this.left = 6;
            this.right = 2;
        }

        // 톱니바퀴 회전 메서드
        // d는 방향을 의미 (시계, 반시계)
        public void rotate(int d) {
            // 톱니바퀴가 회전하면 12시, 9시, 3시방향의 극이 바뀌므로 바뀐 극의 인덱스로 업데이트
            this.top = indexUpdate(this.top, d);
            this.left = indexUpdate(this.left, d);
            this.right = indexUpdate(this.right, d);
        }

        // 톱니바퀴 인덱스를 업데이트 하는 메서드
        // 톱니바퀴를 일렬로 나열해서 배열로 정보를 저장했으므로
        // 배열의 마지막 인덱스 혹은 첫번째 인덱스를 벗어나면
        // 처음 혹은 마지막 인덱스로 변경 되도록 삼항연산자 사용
        public int indexUpdate(int gearIndex, int d) {
            // 시계 방향
            if (d == 1) {
                return gearIndex - 1 == -1 ? 7 : --gearIndex;
            } else {
                // 반시계 방향
                return gearIndex + 1 == 8 ? 0 : ++gearIndex;
            }
        }
    }
}

