package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14501_퇴사_김건현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] pay = new int[N+1]; //보수
        int[] cost = new int[N+1]; //걸리는 일수
        int[] dp = new int[N+6]; //dp
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        for (int index = N; index > 0; index--) {
            //배열의 범위 안이라면
            int end = index+cost[index]-1; //끝나는 일수
            //범위 안이라면
            if(end <= N){
                dp[index] = Math.max(dp[index+1],pay[index]+dp[index+cost[index]]); //지금까지의 최댓값과 (이 일의보수+dp[이 일이 끝나고 나서]) 비교
            }
            else{
                dp[index] = dp[index+1];
            }
        }

        System.out.println(dp[1]);

    }
}
