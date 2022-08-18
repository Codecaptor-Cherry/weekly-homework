package com.ssafy.cfj.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/***
 * 정수 삼각형 
 * @author devTaemin
 *
 */
public class IntegerTriangle {
	
	static int N;
	static int[][] Map;
	static int[][] DP;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N + 1][N + 1];
		DP = new int[N + 1][N + 1];
		
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= row; col++) {
				Map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		DP[1][1] = Map[1][1];
		for (int row = 1; row < N; row++) {
			for (int col = 1; col <= row; col++) {
				int leftCol = col;
				int rightCol = col + 1;
				
				DP[row + 1][leftCol] = Math.max(DP[row + 1][leftCol], Map[row + 1][leftCol] + DP[row][col]);
				DP[row + 1][rightCol] = Math.max(DP[row + 1][rightCol], Map[row + 1][rightCol] + DP[row][col]);
			}
		}
		
		int max = 0;
		for (int col = 1; col <= N; col++) {
			max = Math.max(max, DP[N][col]);
		}
		
		System.out.println(max);
	}

}
