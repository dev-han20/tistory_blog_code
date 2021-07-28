package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_15683 {

    static int result = 0;

    // 백준온라인저지 15683번 감시문제 Java풀이
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mapSize = br.readLine().split(" ");

        int N = Integer.parseInt(mapSize[0]);
        int M = Integer.parseInt(mapSize[1]);
        int[][] map = new int[N][M];
        List<CCTV> cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] mapInfo = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(mapInfo[j]);
                map[i][j] = temp;
                if (temp != 0 && temp != 6) {
                    cctvList.add(new CCTV(temp, i, j, i+j+10));
                }
            }
        }



    }

    static class CCTV {
        int no;
        int r;
        int c;
        int chkNum;


        public CCTV(int no, int r, int c, int chkNum) {
            this.no = no;
            this.r = r;
            this.c = c;
            this.chkNum = chkNum;
        }
    }

    static void DFS(List<CCTV> cctvList, int index, int[][] map) {
        if(index >= cctvList.size()){
            int tempResult = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == 0) {
                        tempResult ++;
                    }
                }
            }
            result = Math.min(tempResult, result);
        }

        CCTV cctv = cctvList.get(index);
        for (int d = 0; d < 4; d++) {
            cctvChk(cctv.no, cctv.r, cctv.c ,d, map, cctv.chkNum);
            DFS(cctvList, index+1, map);
            cctvChk(cctv.no, cctv.r, cctv.c ,d, map, cctv.chkNum);
        }
    }

    private static void cctvChk(int no, int r, int c, int d, int[][] map, int chk) {
        int[] moveR = {-1, 0, 1, 0};
        int[] moveC = {0, -1, 0, 1};
        if (no == 1) {
            int R = r;
            int C = c;
            while (map[R][C] != 6) {
                R = R + moveR[d];
                C = C + moveC[d];
                if (R < 0 || R >= map.length || C < 0 || C >= map[0].length) {
                    break;
                }else {
                    if (map[R][C] == 0) {
                        map[R][C] = chk;
                    } else if (map[R][C] == chk) {
                        map[R][C] = 0;
                    }
                }
            }
        }else if (no == 2) {
            int R1 = r;
            int C1 = c;
            int R2 = r;
            int C2 = c;
            int D1 = d;
            int D2 = d+2 == 4 ? 0 : d+2 == 5 ? 1 : d+2;
            while (map[R1][C1] != 6) {
                R1 = R1 + moveR[D1];
                C1 = C1 + moveC[D1];
                if (R1 < 0 || R1 >= map.length || C1 < 0 || C1 >= map[0].length) {
                    break;
                }else {
                    if (map[R1][C1] == 0) {
                        map[R1][C1] = chk;
                    } else if (map[R1][C1] == chk) {
                        map[R1][C1] = 0;
                    }
                }
            }
            while (map[R2][C2] != 6) {
                R2 = R2 + moveR[D2];
                C2 = C2 + moveC[D2];
                if (R2 < 0 || R2 >= map.length || C2 < 0 || C2 >= map[0].length) {
                    break;
                }else {
                    if (map[R2][C2] == 0) {
                        map[R2][C2] = chk;
                    } else if (map[R2][C2] == chk) {
                        map[R2][C2] = 0;
                    }
                }
            }
        } else if (no == 3) {
            int R1 = r;
            int C1 = c;
            int R2 = r;
            int C2 = c;
            int D1 = d;
            int D2 = d+1 == 4 ? 0 : d+1;
            while (map[R1][C1] != 6) {
                R1 = R1 + moveR[D1];
                C1 = C1 + moveC[D1];
                if (R1 < 0 || R1 >= map.length || C1 < 0 || C1 >= map[0].length) {
                    break;
                }else {
                    if (map[R1][C1] == 0) {
                        map[R1][C1] = chk;
                    } else if (map[R1][C1] == chk) {
                        map[R1][C1] = 0;
                    }
                }
            }
            while (map[R2][C2] != 6) {
                R2 = R2 + moveR[D2];
                C2 = C2 + moveC[D2];
                if (R2 < 0 || R2 >= map.length || C2 < 0 || C2 >= map[0].length) {
                    break;
                }else {
                    if (map[R2][C2] == 0) {
                        map[R2][C2] = chk;
                    } else if (map[R2][C2] == chk) {
                        map[R2][C2] = 0;
                    }
                }
            }
        } else if (no == 4) {
            int R1 = r;
            int C1 = c;
            int R2 = r;
            int C2 = c;
            int D1 = d;
            int D2 = d+1 == 4 ? 0 : d+1;
            while (map[R1][C1] != 6) {
                R1 = R1 + moveR[D1];
                C1 = C1 + moveC[D1];
                if (R1 < 0 || R1 >= map.length || C1 < 0 || C1 >= map[0].length) {
                    break;
                }else {
                    if (map[R1][C1] == 0) {
                        map[R1][C1] = chk;
                    } else if (map[R1][C1] == chk) {
                        map[R1][C1] = 0;
                    }
                }
            }
            while (map[R2][C2] != 6) {
                R2 = R2 + moveR[D2];
                C2 = C2 + moveC[D2];
                if (R2 < 0 || R2 >= map.length || C2 < 0 || C2 >= map[0].length) {
                    break;
                }else {
                    if (map[R2][C2] == 0) {
                        map[R2][C2] = chk;
                    } else if (map[R2][C2] == chk) {
                        map[R2][C2] = 0;
                    }
                }
            }
        } else {

        }
    }



}
