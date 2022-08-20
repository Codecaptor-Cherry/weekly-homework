package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행계획 { // union find 알고리즘

    static int n,m;
    static int[][] map;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        parent = new int[n + 1];

        for(int i = 1; i <= n; i++) {   // 부모를 자기 자신으로 초기화
            parent[i] = i;
        }

        for(int i = 1; i <= n; i++) { // map에 여행지 연결 표시 
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1) { // i도시와 j도시가 연결되어 있으므로 union 연산 실행
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()));
        for(int i = 1; i < m; i++) {
            int current = Integer.parseInt(st.nextToken());
            if(start != find(current)){ // 현재 여행지가 부모 노드와 연결이 안되있다면 NO 출력
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");  // 아닐 시 YES 출력
    }

    static int find(int x) {    // 부모노드를 찾는다.
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {    // 번호가 빠른 여행지를 부모 노드로 만든다.
            if(x < y) {
                parent[y] = x;
            }else {
                parent[x] = y;
            }
        }
    }

}
