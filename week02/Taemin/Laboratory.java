package com.ssafy.cfj.bfsdfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 연구실 
 * @author devTaemin
 *
 */
public class Laboratory {
	
	/** Room class */
	static class Room {
		int row;
		int col;
		
		public Room(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/** Size of row, Size of column*/
	static int N, M;
	
	/** Maximum number of rooms */
	static int Maximum;
	
	/** Map */
	static int[][] Map;
	
	/** Visited map */
	static boolean[][] Visited;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** BFS for presenting the transmission of virus */
	public static void BFS() {
		Queue<Room> queue = new LinkedList<Room>();
		int[][] copy = new int[N][M];
		
		/** Copy current room and check the location of virus */
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				int type = Map[row][col];
				copy[row][col] = type;
				if (type == 2) {
					queue.add(new Room(row, col));
				}
			}
		}
		
		/** Transmission of virus */
		while (!queue.isEmpty()) {
			Room room = queue.poll();
			int row = room.row;
			int col = room.col;
			
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				if (!validateRange(checkRow, checkCol)) continue;
				
				if (copy[checkRow][checkCol] == 0) {
					copy[checkRow][checkCol] = 2;
					queue.add(new Room(checkRow, checkCol));
				}
			}
		}
		
		/** Count the in-contaminated rooms */
		int count = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (copy[row][col] == 0) {
					count++;
				}
			}
		}
		
		/** Compare */
		Maximum = Math.max(Maximum, count);
	}
	
	/** Validate the index */
	public static boolean validateRange(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < M);
	}

	/** DFS for checking additional wall */
	public static void DFS(int depth) {
		if (depth == 3) {
			BFS();
			return;
		}
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (Map[row][col] == 0 && !Visited[row][col]) {
					Visited[row][col] = true;
					Map[row][col] = 1;
					DFS(depth + 1);
					Visited[row][col] = false;
					Map[row][col] = 0;
				}
			}
		}
	}
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/** Initialize Map */
		Map = new int[N][M];
		
		/** Initialize Visited map */
		Visited = new boolean[N][M];
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				Map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		/** DFS for checking additional wall */
		DFS(0);
		
		/** Print out the result */
		System.out.println(Maximum);
	}

}
