package codingtestproblem.p3.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Virus implements Comparable<Virus> {
    int x;
    int y;
    int num;
    /**
     * @param x
     * @param y
     * @param num
     */
    public Virus(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
    @Override
    public int compareTo(Virus o) {
        return this.num - o.num;
    }

}

public class 경쟁적전염 {

    static int n, k, s;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static ArrayList<Virus> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) {
                    list.add(new Virus(i, j, map[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(map[x - 1][y -1]);
    }

    static void bfs() {
        while(s-- > 0) {
            if(!list.isEmpty()) {
                Collections.sort(list);

                int t = list.size();

                for(int i = 0; i < t; i++) {
                    Virus v = list.get(0);
                    list.remove(0);
                    for(int j = 0; j < 4; j++) {

                        int nx = v.x + dx[j];
                        int ny = v.y + dy[j];

                        if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                            if(map[nx][ny] == 0) {
                                map[nx][ny] = v.num;
                                list.add(new Virus(nx, ny, v.num));
                            }
                        }
                    }

                }
            }
        }
    }

}