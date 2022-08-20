package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드 { // 플로이드 워셜 알고리즘(모든 최단 경로를 구하는 알고리즘)

    static final int inf = 9999999; // 갈 수 있는 길이 없을 때 설정할 INF값 설정
    static int n, m;
    static int[][] city;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        city = new int[n + 1][n + 1];   // 도시 이차원 배열 생성

        for(int i = 1; i <= n; i++){    // 도시 배열 inf로 초기화
            for(int j = 1; j <= n; j++) {
                city[i][j] = inf;
            }
        }

        for(int i = 1; i <= n; i++){    // 자기 자신에가 가능 비용 0으로 초기화
            for(int j = 1; j <= n; j++) {
                if(i == j) {
                    city[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < m; i++) {    // 각 도시로 가는 값을 입력
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(c < city[x][y]) {
                city[x][y] = c;
            }
        }
        //플로이드 워셜 알고리즘 수행
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
                }
            }
        }
        // 결과 출력
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(city[i][j] == inf) { // 값이 inf와 같으면 가는 길이 없기 때문에 0출력
                    System.out.print(0 + " ");
                }else { // 갈 수 있는 경우는 거리 출력
                    System.out.print(city[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

}
