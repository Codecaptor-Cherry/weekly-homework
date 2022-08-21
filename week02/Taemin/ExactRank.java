package com.ssafy.cfj.shortcut;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정확한 순위
 * @author devTaemin
 *
 */
public class ExactRank {
	
	static int N, M;
	static boolean[][] Map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			Map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		/** 
		 * Floyd-Warshall Algorithm
		 * : Find every route from every node to every node
		 * */
		for (int via = 1; via <= N; via++) {
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (Map[r][c] == false) {
						if(Map[r][via] && Map[via][c]) Map[r][c] = true;
					}
				}
			}
		}
		
		int count = 0;
		for (int check = 1; check <= N; check++) {
			boolean find = true;
			for (int compare = 1; compare <= N; compare++) {
				/** Pass to compare itself*/
				if (check == compare) continue;
				
				/** Check if check -> compare*/
				if (Map[check][compare]) continue;
				
				/** Check if compare -> check */
				if (!Map[compare][check]) {
					find = false;
					break;
				}
			}
			
			if (find) count++;
		}
		
		
		System.out.println(count);
		
	}
	
	public static void printMap() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.printf("%b ", Map[r][c]);
			}
			System.out.println();
		}
	}

}
