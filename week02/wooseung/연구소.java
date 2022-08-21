package codingtestproblem.p3.day0822;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소 {  // 341  발표문제

    static int n, m, ans = Integer.MIN_VALUE;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] copyMap;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0);
        System.out.println(ans);
    }

    static void perm(int cnt) { // 순열을 통해서 벽 생성
        if(cnt == 3) {
            virus();
            return;
        }
        for(int i = 0; i < n; i++) {
            for(int j =0; j < m; j++){

                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    perm(cnt + 1);
                    map[i][j] = 0;

                }
            }
        }

    }

    static void virus() {   // BFS를 통해서 바이러스 살포

        copyMap = new int[n][m];

        Queue<Point> q = new LinkedList<>();

        for(int i = 0; i < n; i++) {    // copyMap에 map을 복사해주고 virus 좌표를 q에 넣어준다.
            for(int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
                if(copyMap[i][j] == 2) {
                    q.add(new Point(i, j));
                }
            }
        }

        while(!q.isEmpty()){

            Point p = q.poll();

            for(int i = 0; i < 4; i++) {

                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (copyMap[nx][ny] == 0) {
                        copyMap[nx][ny] = 2;
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }
        safeArae();
    }

    static void safeArae() {    // 안정영역 체크
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(copyMap[i][j] == 0) {
                    max++;
                }
            }
        }
        ans = Math.max(ans, max);
    }

}
