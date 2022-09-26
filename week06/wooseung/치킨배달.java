package codingtestproblem.p3.day0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
public class 치킨배달 {

    static int n, m;
    static int ans = Integer.MAX_VALUE;
    static int[] ansChicken;
    static boolean[] visited;
    static ArrayList<Pos> ch, h;
    static int chcnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer( br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ch = new ArrayList<>();
        h = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    h.add(new Pos(i, j));
                }
                if(num == 2) {
                    chcnt++;
                    ch.add(new Pos(i, j));
                }
            }
        }

        ansChicken = new int[m];
        visited = new boolean[chcnt];

        pick(0, 0);
        System.out.println(ans);

    }

    static void pick(int idx, int cnt) {
        if(cnt == m) {
            ans = Math.min(ans, calcDist());
            return;
        }
        for(int i = idx; i < chcnt; i++) {
            if(!visited[i]) {
                visited[i] = true;
                ansChicken[cnt] = i;
                pick(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }

    static int calcDist() {
        int distSum = 0;
        for(Pos home : h) {
            int dist = Integer.MAX_VALUE;
            for(int pick : ansChicken) {
                dist = Math.min(dist, (Math.abs(home.x- ch.get(pick).x) + Math.abs(home.y - ch.get(pick).y)));
            }
            distSum += dist;
        }

        return distSum;
    }

}
