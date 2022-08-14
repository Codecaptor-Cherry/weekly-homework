package com.ssafy.cfj.shortcut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Floyd {
	/** Infinite */
	static final int INF = (int)1e9;
	
	/** Number of city, Number of bus route */
	static int N, M;

	/** Shortcut map */
	static int[][] Map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		Map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) Map[i][j] = 0;
				else Map[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int charge = Integer.parseInt(st.nextToken());
			
			if (Map[from][to] > charge) {
				Map[from][to] = charge;
			}
		}
		
		for (int via = 1; via <= N; via++) {
			for (int from = 1; from <= N; from++) {				
				for (int to = 1; to <= N; to++) {
					Map[from][to] = Math.min(Map[from][to], Map[from][via] + Map[via][to]);
				}
			}
		}
		
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (Map[from][to] == INF) Map[from][to] = 0;
				System.out.printf("%d ", Map[from][to]);
			}
			System.out.println();
		}
	}
}
