package study.blog.codingnojam.algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

// 백준온라인저지 14889번 스타트와 링크 문제 풀이
public class BOJ_14889 {

    static int[][] status;
    static boolean[] teamChk;
    static LinkedList<Integer> team = new LinkedList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        status = new int[N][N];
        teamChk = new boolean[N];

        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split(" ");
            for (int j = 0; j < info.length; j++) {
                status[i][j] = Integer.parseInt(info[j]);
            }
        }

        recursion(0, N/2, N);
        System.out.println(result);

    }

    public static void recursion(int index, int limit, int N){

        if (team.size() == limit) {
            int teamResult = 0;

            ArrayList<Integer> otherTeam = new ArrayList<>();
            for (int i = 0; i < teamChk.length; i++) {
                if (!teamChk[i]) {
                    otherTeam.add(i);
                }
            }
            int otherTeamResult = 0;

            for (int i = 0; i < team.size()-1; i++) {
                int teamFst = team.get(i);
                for (int j = i+1; j < team.size(); j++) {
                    int teamSec = team.get(j);
                    teamResult += status[teamFst][teamSec];
                    teamResult += status[teamSec][teamFst];
                }
            }

            for (int i = 0; i < otherTeam.size()-1; i++) {
                int otherTeamFst = otherTeam.get(i);
                for (int j = i+1; j < otherTeam.size(); j++) {
                    int otherTeamSec = otherTeam.get(j);
                    otherTeamResult += status[otherTeamFst][otherTeamSec];
                    otherTeamResult += status[otherTeamSec][otherTeamFst];
                }
            }

            result = Math.min(result ,Math.abs(teamResult - otherTeamResult));
            return;
        }

        for (int i = index; i < N; i++) {
            team.add(i);
            teamChk[i] = true;
            recursion(i +1, limit, N);
            teamChk[i] = false;
            team.removeLast();
        }

    }
}
