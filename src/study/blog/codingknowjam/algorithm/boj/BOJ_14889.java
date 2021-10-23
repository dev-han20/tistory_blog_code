package study.blog.codingknowjam.algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

// 백준온라인저지 14889번 스타트와 링크 문제 풀이
public class BOJ_14889 {

    // 직원들 능력치 저장하는 2차원 배열
    static int[][] status;
    // 팀 나누기 위해서 사용하는 체크배열
    static boolean[] teamChk;
    // 팀을 저장할 링크드리스트
    static LinkedList<Integer> team = new LinkedList<>();
    // 최소 능력치 차이 결과 값 저장할 변수
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        // 정보 받기위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 사람수
        int N = Integer.parseInt(br.readLine());
        // 총 사람 수로 배열 길이주고 생성
        status = new int[N][N];
        teamChk = new boolean[N];

        // 능력치 배열 초기화
        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split(" ");
            for (int j = 0; j < info.length; j++) {
                status[i][j] = Integer.parseInt(info[j]);
            }
        }

        // 모든 경우의 수 판단 (재귀함수 사용)
        recursion(0, N/2, N);

        // 결과 출력
        System.out.println(result);

    }

    /**
     * 팀을 나누는 모든 경우의 수 체크를 위한 재귀메서드
     * @param index 재귀호출 시 마다 1씩 증가하면서 리스트에 저장할 팀원을 가리키는 변수
     * @param limit 팀별 가져야하는 팀원의 수(재귀 종료를 위해 사용)
     * @param N 전체 인원 수
     */
    public static void recursion(int index, int limit, int N){

        // 필요한 팀원 만큼 팀에 배정이 끝난 경우
        if (team.size() == limit) {
            // 팀의 능력치 저장을 위한 변수
            int teamResult = 0;

            // 다른 팀을 저장하기 위한 리스트
            ArrayList<Integer> otherTeam = new ArrayList<>();
            // 다른 팀 팀원 저장
            for (int i = 0; i < teamChk.length; i++) {
                // 처음 팀에 들어갔던 팀원들 제외한 나머지 다른팀에 저장
                if (!teamChk[i]) {
                    otherTeam.add(i);
                }
            }
            // 다른팀의 능력치 저장을 위한 변수
            int otherTeamResult = 0;

            // 팀의 능력치 계산 및 저장
            for (int i = 0; i < team.size()-1; i++) {
                int teamFst = team.get(i);
                for (int j = i+1; j < team.size(); j++) {
                    int teamSec = team.get(j);
                    teamResult += status[teamFst][teamSec];
                    teamResult += status[teamSec][teamFst];
                }
            }


            // 다른팀의 능력치 계산 및 저장
            for (int i = 0; i < otherTeam.size()-1; i++) {
                int otherTeamFst = otherTeam.get(i);
                for (int j = i+1; j < otherTeam.size(); j++) {
                    int otherTeamSec = otherTeam.get(j);
                    otherTeamResult += status[otherTeamFst][otherTeamSec];
                    otherTeamResult += status[otherTeamSec][otherTeamFst];
                }
            }

            // 팀과 다른팀의 능력치 차이가 제일 작은 값으로 result 갱신
            result = Math.min(result ,Math.abs(teamResult - otherTeamResult));
            // 재귀 종료
            return;
        }

        // 전체 직원 수 만큼 반복
        // index값을 i로 줘서 중복 안나게 방지 (0,1,2랑 2,1,0은 같은거임)
        // i가 팀원의 인덱스랑 같은것(0번 팀원, 1번 팀원 등등)
        for (int i = index; i < N; i++) {
            // 팀에 팀원 추가
            team.add(i);
            // 추가된 팀원 체크
            teamChk[i] = true;
            // 다음 팀원 배정을 위해 재귀호출
            recursion(i +1, limit, N);
            // 추가된 팀원 팀에서 제외한 걸로 체크
            teamChk[i] = false;
            // 팀에서 팀원 제거
            team.removeLast();
        }

    }
}
