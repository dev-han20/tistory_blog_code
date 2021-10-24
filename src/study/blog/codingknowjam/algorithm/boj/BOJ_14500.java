package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_14500 {
    private static int result = 0;
    private static int otherResult = 0;
    private static int R = 0;
    private static int C = 0;
    private static int[][] map;
    private static ArrayList<Integer> beforeValues = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        R = Integer.parseInt(NM[0]);
        C = Integer.parseInt(NM[1]);
        map = new int[R][C];

        initMap(br);

        R1C4();
        R2C2();
        R2C3();

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                int tempResult = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1];
                result = Math.max(result, tempResult);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 3; j++) {
                int tempResult = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3];
                result = Math.max(result, tempResult);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int tempResult = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1];
                int otherTempResult = map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2] + map[i][j + 1];
                result = getMaxValueTriple(result, tempResult, otherTempResult);

                tempResult = map[i][j + 1] + map[i][j + 2] + map[i + 1][j] + map[i + 1][j + 1];
                otherTempResult = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2];
                result = getMaxValueTriple(result, tempResult, otherTempResult);
            }
        }

    }

    private static void initMap(BufferedReader br) throws IOException {
        for (int i = 0; i < R; i++) {
            String[] tempString = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tempString[j]);
            }
        }
    }

    private static void updateResult(int firstValue, int secondValue) {
        result = Math.max(result, Math.max(firstValue, secondValue));
    }

    private static void updateResult(int firstValue) {
        result = Math.max(result, firstValue);
    }

    /**
     * □□□□ 구하기
     */
    private static void R1C4() {
        for (int i = 0; i < R; i++) {
            int lt = 0;
            otherResult = map[i][0] + map[i][1] + map[i][2] + map[i][3];
            updateResult(otherResult);
            for (int rt = 4; rt < C; rt++) {
                otherResult = otherResult + map[i][rt] - map[i][lt];
                updateResult(otherResult);
                lt++;
            }
        }
    }

    /**
     * □□ 구하기
     * □□
     */
    private static void R2C2() {
        for (int i = 0; i < R - 1; i++) {
            int lt = 0;
            otherResult = map[i][0] + map[i][1] + map[i + 1][0] + map[i + 1][1];
            updateResult(otherResult);
            for (int rt = 2; rt < C - 1; rt++) {
                otherResult = otherResult + map[i][rt] + map[i + 1][rt]
                        - map[i][lt] - map[i + 1][lt];
                updateResult(otherResult);
                lt++;
            }
        }
    }

    /**
     * □□□ 에 포함되는 테트로미노들 구하기
     * □□□
     */
    private static void R2C3() {
        for (int i = 0; i < R - 1; i++) {
            for (int j = 0; j < C - 2; j++) {
                beforeValues.add(map[i][j] + map[i + 1][j] + map[i][j + 1]
                        + map[i + 1][j + 1] + map[i][j + 2] + map[i + 1][j + 2]);
            }
        }

        for (int i = 0; i < R - 1; i++) {
            for (int j = 0; j < C - 2; j++) {
                otherResult.
            }
        }
    }

}
