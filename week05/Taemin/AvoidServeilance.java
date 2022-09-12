package com.ssafy.cfj.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AvoidServeilance {
	
	static int N;
	static boolean FindAnswer;
	static char[][] Map;
	static ArrayList<int[]> Teachers;
	
	/** Direciton (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	public static void check() {		
		for (int[] teacher : Teachers) {
			for (int d = 0; d < 4; d++) {
				int curX = teacher[0];
				int curY = teacher[1];
				
				while (true) {
					curX += Dx[d];
					curY += Dy[d];
					
					if (!validateRange(curX, curY)) break;
					
					if (Map[curX][curY] == 'O') break;
					
					if (Map[curX][curY] == 'S') {
						return;
					}
				}
			}
		}
		
		FindAnswer = true;
	}
	
	public static void DFS(int depth, int row, int col) {
		if (FindAnswer) return;
		
		if (depth == 3) {
//			printMap();
//			System.out.println();
			check();
			return;
		}
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r < row || r <= row && c <= col) continue;
				if (Map[r][c] == 'X') {
					Map[r][c] = 'O';
					DFS(depth + 1, r, c);
					Map[r][c] = 'X';
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Map = new char[N + 1][N + 1];
		Teachers = new ArrayList<>();
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				char type = st.nextToken().charAt(0);
				Map[r][c] = type;
				if (type == 'T') {
					Teachers.add(new int[] {r, c});
				} 
			}
		}
		
		FindAnswer = false;
		DFS(0, 0, 0);
		
		if (FindAnswer) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	public static void printMap() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.print(Map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
