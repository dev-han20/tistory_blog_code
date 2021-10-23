package study.blog.codingknowjam.algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    public static void main(String[] args) {

        String[] arr = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

//        System.out.println(solution(4, 5, arr));
    }

    public  int solution(int m, int n, String[] board) {
        int answer = 0;

        String[][] map = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] info = board[i].split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = info[j];
            }
        }

        List<Block> removeBlocks = new ArrayList<>();

        // 반시계방향
        int[] moveR = {0, 1, 1, 0};
        int[] moveC = {0, 0, 1, 1};
        boolean gameContinue = true;

        while (gameContinue) {
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    int count = 0;

                    for (int k = 0; k < 4; k++) {
                        if (map[i][j].equals("-")) {
                            continue;
                        }

                        int nextR = i + moveR[k];
                        int nextC = j + moveC[k];
                        if (map[i][j].equals(map[nextR][nextC])) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    if (count == 4) {
                        for (int k = 0; k < 4; k++) {
                            int nextR = i + moveR[k];
                            int nextC = j + moveC[k];
                            removeBlocks.add(new Block(nextR, nextC));
                        }
                    }
                }
            }

            if (removeBlocks.size() != 0) {
                for (Block b : removeBlocks) {
                    if (!map[b.row][b.column].equals("-")) {
                        map[b.row][b.column] = "-";
                        answer++;
                    }
                }
                removeBlocks.clear();
            } else {
                gameContinue = false;
            }

            for (int j = 0; j < n; j++) {
                for (int i = m-1; i >= 0; i--) {
                    if (!map[i][j].equals("-")) {
                        int index = i;
                        while (true) {
                            int nextR = index + 1;
                            if (nextR < m && map[nextR][j].equals("-")) {
                                map[nextR][j] = map[nextR-1][j];
                                map[nextR-1][j] = "-";
                                index++;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }


        return answer;
    }

    class Block {
        int row;
        int column;

        public Block(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

}