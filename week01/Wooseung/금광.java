package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 금광 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] map = new int[n][m];
            int[][] dp = new int[n][m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {   // map 초기화 dp 첫번째 열에 값 초기화
                    int temp = Integer.parseInt(st.nextToken());
                    map[i][j] = temp;
                    if(j == 0) {
                        dp[i][j] = temp;
                    }
                }
            }

            for (int y = 1; y < m; y++) {
                for (int x = 0; x < n; x++) {

                    if (x + 1 < n) {    // 오른쪽 위
                        dp[x][y] = Math.max(dp[x][y], dp[x + 1][y - 1] + map[x][y]);
                    }

                    dp[x][y] = Math.max(dp[x][y], dp[x][y - 1] + map[x][y]);    // 오른쪽

                    if (x - 1 >= 0) {   // 오른쪽 아래
                        dp[x][y] = Math.max(dp[x][y], dp[x - 1][y - 1] + map[x][y]);
                    }
                }

            }
            int ans = 0;
            for(int i = 0; i < n; i++) {    // 배열 마지막 열의 최대값 비교
                ans = Math.max(ans, dp[i][m - 1]);
            }
            System.out.println(ans);    // 최대값 출력
        }
    }

}
