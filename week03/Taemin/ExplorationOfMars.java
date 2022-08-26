package com.ssafy.cfj.shortroute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *	화성탐사
 */
public class ExplorationOfMars {
	
	static int T, N;
	static int[][] Map;
	static int[][] Accumulated;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < N);
	}
	
	public static int BFS() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		
		/** Check if the target value in the accumulated matrix has ever updated */
		boolean[][] checked = new boolean[N][N];
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int row = cur[0];
			int col = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				/** Validate the index */
				if (!validateRange(checkRow, checkCol)) continue;
								
				/** If it is the first time for the value in the accumulated matrix */
				if (!checked[checkRow][checkCol]) {
					/** Update */
					Accumulated[checkRow][checkCol] = Accumulated[row][col] + Map[checkRow][checkCol];
					/** Check */
					checked[checkRow][checkCol] = true;
					/** Add checked index */
					queue.offer(new int[] {checkRow, checkCol});
					continue;
				}
				
				/** Check new route */
				if (Accumulated[checkRow][checkCol] > Accumulated[row][col] + Map[checkRow][checkCol]) {
					Accumulated[checkRow][checkCol] = Accumulated[row][col] + Map[checkRow][checkCol];
					/** Add newly checked index */
					queue.offer(new int[] {checkRow, checkCol});
				}
			}
		}
		
		return Accumulated[N - 1][N - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N][N];
			Accumulated = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int c = 0; c < N; c++) {
					int distance = Integer.parseInt(st.nextToken());
					Map[r][c] = Accumulated[r][c] = distance;
				}
			}
			
			sb.append(BFS()).append("\n");
		}
		
		System.out.println(sb);
	}

}
