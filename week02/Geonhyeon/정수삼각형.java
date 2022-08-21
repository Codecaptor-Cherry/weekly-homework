package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] size = new int[n+1]; //반복재귀 방지용 메모이제이션
        int N = size(n,size);
        int MAX = 0;
        int[] arr = new int[N];
        int[] dp = new int[N];

        for (int i = 1,idx=0; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0] = arr[0];
        for (int i = 1,k=1; i < n; i++) {
            dp[k] = arr[k]+dp[k-i];
            k++;
            for (int j = 0; j < i-1 ; j++) {
                dp[k] = arr[k]+Math.max(dp[k-i],dp[k-i-1]);
                k++;
            }
            dp[k] = arr[k]+dp[k-i-1];
            k++;
        }

        for (int i = N-n; i < N; i++) {
            if(dp[i]>MAX) MAX = dp[i];
        }
        System.out.println(MAX);

    }

    static int size(int i,int[] size){
        int sum = 0;
        for (int j = 1; j <= i; j++) {
            sum+=j;
        }
        return sum;
    }
}
