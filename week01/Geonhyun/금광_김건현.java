package com.ssafy;

import java.util.Scanner;

public class 금광_김건현 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0;T>t;t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] arr = new int[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			int[][] dp = new int[N][M];
			for(int i=0;i<N;i++) {
				dp[i][0] = arr[i][0];
			}
			for(int j=1;j<M;j++) { //1열 부터 끝열까지
				for(int i=0;i<N;i++) {//각행에대해
					if(i==0) {//제일 위
						dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j-1])+arr[i][j]; //왼쪽과 왼쪽아래
					}
					else if(i==(N-1)) {//제일 아래
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-1])+arr[i][j]; //왼쪽과 왼쪽위
					}
					else {
						dp[i][j] = Math.max(dp[i][j-1], Math.max(dp[i-1][j-1], dp[i+1][j-1]))+arr[i][j]; //세방향모두 고려
					}
				}
			}
			int max = Integer.MIN_VALUE;
			for(int i=0;i<N;i++) {
				if(dp[i][M-1]>max) max = dp[i][M-1];
			}
			System.out.println(max);
		}
		
	}
}
