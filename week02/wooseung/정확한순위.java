package codingtestproblem.p3.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정확한순위 {

    static int n, m, ans, inf = 99999999;   // 플로이드 워셜 초기화를 위한 inf변수
    static int arr[][];


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = 0;
         arr = new int[n + 1][n + 1];

        for(int i = 1; i < arr.length; i++) {   // 자기 자신은 0으로 그 외에는 inf로 초기화
            for(int j = 1; j < arr.length; j++) {
                if(i == j) {
                    arr[i][j] = 0;
                }else {
                    arr[i][j] = inf;
                }
            }
        }

        for(int i = 0; i < m; i++) {    // 간선에 대한 정보를 받아서 초기화
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1;
        }
        for(int i = 0; i <= n; i++){
            System.out.println(Arrays.toString(arr[i]));
        }

        
        // 플로이드 워셜 알고리즘 실행
        // 모든 지점에서 다른 모든 지점까지의 최단 경로를 계산
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    arr[i ][j] = Math.min(arr[i ][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++) {   // cnt = 6이라면 모든 성적을 다 비교할 수 있는 학생이기 때문에 
            int cnt = 0;                // 정확히 순위를 알 수 있는 학생이기 때문에 ans 값 증가
            for(int j = 1; j <= n; j++) {
                if(arr[i][j] != inf || arr[j][i] != inf) {
                    cnt += 1;
                }
            }
            if(cnt == n) {
                ans++;
            }
        }
        System.out.println(ans);
    }

}
