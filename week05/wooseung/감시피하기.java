package codingtestproblem.p3.day0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시피하기 {

    static int N;
    static char map[][];
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static class Node {
        int x,y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }
    static ArrayList<Node> teachers = new ArrayList<>();
    static ArrayList<Node> blanks = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j]=='T') teachers.add(new Node(i,j));
                if(map[i][j]=='X') blanks.add(new Node(i,j));
            }
        }

        visited = new boolean[blanks.size()];
        comb(0,0);
        System.out.println("NO");
    }


    private static void comb(int cnt, int start) {
        if(cnt==3) {

            if(dfs()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for(int i = start; i < blanks.size() ; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            Node n = blanks.get(i);
            map[n.x][n.y]= 'O';
            comb(cnt+1, i+1);
            map[n.x][n.y]= 'X';
            visited[i]= false;
        }
    }

    private static boolean dfs() {

        for(Node n : teachers) {
            int x = n.x;
            int y = n.y;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                while(check(nx,ny)) {
                    if(map[nx][ny]=='S') return false;
                    if(map[nx][ny]=='O') break;

                    nx+=dx[i];
                    ny+=dy[i];

                }
            }
        }
        return true;
    }


    private static boolean check(int nx, int ny) {
        if(nx>=0&&ny>=0&&nx<N&&ny<N) return true;
        return false;
    }

}