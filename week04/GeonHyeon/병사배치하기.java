package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 병사배치하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int MAX = 1;
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                //나보다 큰수라면 -> 내림차순성립. -> 갱신
                if(arr[i]<arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            //최대값 갱신. MAX의 초기값은 1이여야함...ㅜ
            MAX = Math.max(dp[i], MAX);
        }


        System.out.println(N-MAX);

    }
}
