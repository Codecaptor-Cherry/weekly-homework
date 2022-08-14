package com.ssafy.cfj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TravelPlan {
	/** Number of city, Number of city planning to visit */
	static int N, M;

	/** State of connection */
	static int[][] Map;
	
	/** Traveling */
	static int[] Sequence;
	
	/** Parent city */
	static int[] Parent;
	
	static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if (a < b) {
			Parent[b] = a;
		}
		else {
			Parent[a] = b;
		}
	}
	
	static int findParent(int c) {
		if (Parent[c] != c) {
			return Parent[c] = findParent(Parent[c]);
		}
		
		return Parent[c];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		Parent = new int[N + 1];
		for (int city = 1; city <= N; city++) {
			Parent[city] = city;
		}
		
		Map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				Map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (Map[r][c] == 1) {
					union(r, c);
				}
			}
		}
		
//		System.out.println("\n-----------------");
//		for (int i = 1; i <= N; i++) {
//			System.out.printf("%d ", Parent[i]);
//		}
//		System.out.println("-----------------\n");
		
		
		Sequence = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			Sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean result = true;
		for (int i = 0; i < M - 1; i++) {
			if (findParent(Sequence[i]) != findParent(Sequence[i + 1])) {
				result = false;
				break;
			}
		}
		
//		System.out.println("\n-----------------");
//		for (int i = 1; i <= N; i++) {
//			System.out.printf("%d ", Parent[i]);
//		}
//		System.out.println("-----------------\n");
		
		if (result) System.out.println("YES");
		else System.out.println("NO");
	}

}
