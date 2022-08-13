package com.ssafy.cfj.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class GoldMine {

	static int T, N, M;
	static int Maximum;
	static int[][] Array;
	static int[][] DP;
	
	/** Direction */
	static int[] Dx = { -1, 0, 1 };
	static int[] Dy = { 1, 1, 1 };
	
	public static void BFS(int row, int col) {
		int[] index = { row, col };
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(index);
		
		while (!queue.isEmpty()) {
			int[] mine = queue.poll();
			int curRow = mine[0];
			int curCol = mine[1];
			
			for (int d = 0; d < 3; d++) {
				int checkRow = curRow + Dx[d];
				int checkCol = curCol + Dy[d];
				
				// Validate range
				if (checkRow < 1 || checkRow > N || checkCol < 1 || checkCol > M) {
					continue;
				}
				
				// check DP
				if (DP[checkRow][checkCol] > DP[curRow][curCol] + Array[checkRow][checkCol]) {
					continue;
				}
				
				DP[checkRow][checkCol] = DP[curRow][curCol] + Array[checkRow][checkCol];
				queue.add(new int[] { checkRow, checkCol });
			}			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Array = new int[N + 1][M + 1];
			DP = new int[N + 1][M + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= M; col++) {
					int elem = Integer.parseInt(st.nextToken());
					Array[row][col] = elem;
					DP[row][col] = elem;
				}
			}
			
			printArray();
			printDP();
			
			Maximum = 0;
			
			// Change the start point
			for (int row = 1; row <= N; row++) {
				BFS(row, 1);
				printArray();
				printDP();
			}
			
			for (int row = 1; row <= N; row++) {
				Maximum = Math.max(Maximum, DP[row][M]);
			}
			
			sb.append(Maximum).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void printArray() {
		System.out.println("\n------------- Array --------------");
		for (int[] line : Array) {
			for (int elem : line) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printDP() {
		System.out.println("\n------------- DP --------------");
		for (int[] line : DP) {
			for (int elem : line) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
