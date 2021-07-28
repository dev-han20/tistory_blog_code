package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14891 {

    // 백준온라인저지 14891 톱니바퀴 Java풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Gear gearOne = new Gear(br.readLine().split(""));
        Gear gearTwo = new Gear(br.readLine().split(""));
        Gear gearThree = new Gear(br.readLine().split(""));
        Gear gearFour = new Gear(br.readLine().split(""));

        int K = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 0; i < K; i++) {
            String[] info = br.readLine().split(" ");
            int[] chk = new int[4];

            if (info[0].equals("1")) {
                chk[0] = Integer.parseInt(info[1]);
                if (!gearOne.arr[gearOne.right].equals(gearTwo.arr[gearTwo.left])) {
                    chk[1] = 0 - chk[0];
                    if (!gearTwo.arr[gearTwo.right].equals(gearThree.arr[gearThree.left])) {
                        chk[2] = 0 - chk[1];
                        if (!gearThree.arr[gearThree.right].equals(gearFour.arr[gearFour.left])) {
                            chk[3] = 0 - chk[2];
                        }
                    }
                }
            } else if (info[0].equals("2")) {
                chk[1] = Integer.parseInt(info[1]);
                if (!gearTwo.arr[gearTwo.right].equals(gearThree.arr[gearThree.left])) {
                    chk[2] = 0 - chk[1];
                    if (!gearThree.arr[gearThree.right].equals(gearFour.arr[gearFour.left])) {
                        chk[3] = 0 - chk[2];
                    }
                }
                if (!gearTwo.arr[gearTwo.left].equals(gearOne.arr[gearOne.right])) {
                    chk[0] = 0 - chk[1];
                }
            } else if (info[0].equals("3")) {
                chk[2] = Integer.parseInt(info[1]);
                if (!gearThree.arr[gearThree.right].equals(gearFour.arr[gearFour.left])) {
                    chk[3] = 0 - chk[2];
                }
                if (!gearThree.arr[gearThree.left].equals(gearTwo.arr[gearTwo.right])) {
                    chk[1] = 0 - chk[2];
                    if (!gearTwo.arr[gearTwo.left].equals(gearOne.arr[gearOne.right])) {
                        chk[0] = 0 - chk[1];
                    }
                }
            } else {
                chk[3] = Integer.parseInt(info[1]);
                if (!gearFour.arr[gearFour.left].equals(gearThree.arr[gearThree.right])) {
                    chk[2] = 0 - chk[3];
                    if (!gearThree.arr[gearThree.left].equals(gearTwo.arr[gearTwo.right])) {
                        chk[1] = 0 - chk[2];
                        if (!gearTwo.arr[gearTwo.left].equals(gearOne.arr[gearOne.right])) {
                            chk[0] = 0 - chk[1];
                        }
                    }
                }
            }

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

        System.out.println(result);


    }

    static class Gear {
        String[] arr = new String[8];
        int top, left, right;

        public Gear(String[] arr) {
            this.arr = arr;
            this.top = 0;
            this.left = 6;
            this.right = 2;
        }

        public void rotate(int d) {
            this.top = indexUpdate(this.top, d);
            this.left = indexUpdate(this.left, d);
            this.right = indexUpdate(this.right, d);
        }

        public int indexUpdate(int gearIndex, int d) {
            if (d == 1) {
                return gearIndex - 1 == -1 ? 7 : --gearIndex;
            } else {
                return gearIndex + 1 == 8 ? 0 : ++gearIndex;
            }
        }

        @Override
        public String toString() {
            return "Gear{" +
                    "top=" + top +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}

