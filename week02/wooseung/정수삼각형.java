package codingtestproblem.p3.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;



        int n = Integer.parseInt(br.readLine());
        int ans = 0;
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for(int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }


       dp[0][0] = arr[0][0];

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j <= i; j++) {
                int temp = dp[i][j] + arr[i + 1][j];
                if(dp[i + 1][j] < temp) {
                    dp[i + 1][j] = temp;
                }
                temp = dp[i][j] + arr[i + 1][j + 1];
                if(dp[i + 1][j + 1] < temp){
                    dp[i + 1][j + 1] = temp;
                }
            }
        }

//        for(int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        for(int i = 0; i < n; i++){
            ans = Math.max(ans, dp[n - 1][i]);
        }
        System.out.println(ans);
    }

}
