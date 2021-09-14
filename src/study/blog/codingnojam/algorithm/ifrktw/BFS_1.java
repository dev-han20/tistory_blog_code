package study.blog.codingnojam.algorithm.ifrktw;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int S = sc.nextInt();
        int E = sc.nextInt();

        int[] sky = {1, -1, 5};

        Queue<Man> q = new LinkedList<>();
        q.offer(new Man(S, 0));
        int[] chk = new int[10001];
        chk[S] = 1;

        while(!q.isEmpty()){
            Man man = q.poll();
            if (man.x == E) {
                System.out.println(man.jc);
                return;
            }
            for (int i = 0; i < sky.length; i++) {
                int l = man.x + sky[i];
                if (chk[l] == 1 || l > 10000 || l < 1) {
                    continue;
                } else {
                    q.offer(new Man(man.x + sky[i], man.jc + 1));
                    chk[man.x + sky[i]] = 1;
                }
            }
        }
    }

    static class Man{
        int x;
        int jc;

        public Man(int x, int jc) {
            this.x = x;
            this.jc = jc;
        }
    }
}
