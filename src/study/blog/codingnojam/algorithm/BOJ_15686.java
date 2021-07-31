package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_15686 {

    static int result = Integer.MAX_VALUE;

    // 백준온라인저지 15686 치킨배달 문제 Java풀이
    public static void main(String[] args) throws IOException {

        // 입력을 받을 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시 크기, 치킨집 개수 받기
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);

        // 치킨집들을 저장할 리스트
        List<Chicken> chickens = new ArrayList<>();

        // 여러개의 치킨집 중에 M개를 뽑아낼 때 저장할 리스트
        List<Chicken> search = new ArrayList<>();

        // 도시를 나타낼 2차원 배열
        // 0인덱스는 쓰지 않기 위해 길이 1씩 증가
        int[][] city = new int[N + 1][N + 1];

        // 도시를 나타내는 2차원 배열 초기화
        // 0인덱스는 사용하지 않으므로 1부터 시작
        for (int i = 1; i < city.length; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j < city[i].length; j++) {
                // 초기화 과정에서 치킨집 발견 시 리스트에 저장
                if (Integer.parseInt(temp[j-1]) == 2) {
                    chickens.add(new Chicken(i, j));
                }
                city[i][j] = Integer.parseInt(temp[j-1]);
            }
        }

        // 재귀메서드로 모든 경우의수 체크 시작
        recursion(city, 0, M, chickens, search);

        // 결과 출력
        System.out.println(result);
    }

    // 치킨집
    static class Chicken {
        // 치킨집 행과 열
        int row;
        int column;

        public Chicken(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    /**
     * 재귀메서드로 치킨거리 최솟값 찾기
     * @param city : 도시정보를 가진 2차웝 배열
     * @param index : 배열에 사용할 인덱스
     * @param M : 도시에 있어야 하는 최대 치킨집 개수
     * @param chickens : 도시에 있는 모든 치킨집을 저장한 리스트
     * @param search : 모든치킨집 중에 M개의 치킨집을 뽑아내서 저장할 리스트
     */
    static void recursion(int[][] city, int index, int M, List<Chicken> chickens, List<Chicken> search) {

        // M개의 치킨집을 다 고른경우
        if (search.size() >= M) {
            // 임시 결과 저장 값
            int tempResult = 0;
            // 도시 탐색
            for (int i = 1; i < city.length; i++) {
                for (int j = 1; j < city[i].length; j++) {
                    // 가정 집인 경우
                    if (city[i][j] == 1) {
                        // 최소거리값을 저장할 변수
                        int minDistance = Integer.MAX_VALUE;
                        // 가정집과 M개의 치킨집 모두 거리 계산
                        for (Chicken c : search) {
                            // M개의 치킨집 중 가정집과 거리가 가장 가까운 값으로 거리값 저장
                            int temp = Math.abs(i-c.row) + Math.abs(j-c.column);
                            minDistance = Math.min(minDistance, temp);
                        }
                        // 구한 최소거리값을 임시 결과변수에 저장
                        tempResult += minDistance;
                    }
                }
            }

            // 임시결과 변수와 최종결과 변수 값 중 작은 값을 최종결과 변수에 저장 후 종료
            result = Math.min(tempResult, result);
            return;
        }

        // 도시에 있는 치킨집 중에서 M개의 치킨집 뽑는 모든 경우의수 탐색
        for (int i = index; i < chickens.size(); i++) {
            search.add(chickens.get(i));
            recursion(city, i + 1, M, chickens, search);
            search.remove(search.size() - 1);
        }
    }

}
