package com.ssafy.cfj.bfsdfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * 경쟁적 전염
 */
public class CompetitiveInfection {
	
	/** Virus */
	static class Virus implements Comparable<Virus>{
		int type;	// virus number
		int row;	// row
		int col;	// col
		
		/** Constructor */
		public Virus(int type, int row, int col) {
			this.type = type;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Virus o) {
			return Integer.compare(this.type,o.type);
		}
	}
	
	/**
	 * N: 		Size of map
	 * K: 		Maximum number of virus
	 * S: 		Seconds
	 * X, Y: 	index
	 * */
	static int N, K, S, X, Y;
	
	/** Map */
	static int[][] Map;
	
	/** Array for storing contagious virus */
	static ArrayList<Virus> Array;
	
	/** Direction (North, East, South, West) */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/** Validate the index */
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	/** BFS logic for spreading virus */
	public static void BFS() {
		/** Repeated times */
		for (int i = 0; i < S; i++) {
			/** Sort virus numbers in an ascending order */
			Collections.sort(Array);
			
			/** Store the current size of virus */
			int size = Array.size();
			
			for (int j = 0; j < size; j++) {
				/** Always get the first virus in the array */
				Virus cur = Array.get(0);
				
				/** Remove the first array (indices are automatically diminished by 1 */
				Array.remove(0);
				
				/** Check four directions */
				for (int d = 0; d < 4; d++) {
					int checkRow = cur.row + Dx[d];
					int checkCol = cur.col + Dy[d];
					
					if (!validateRange(checkRow, checkCol)) continue;
					if (Map[checkRow][checkCol] != 0) continue;
					
					/** If valid, check the map and store the spread virus */
					Map[checkRow][checkCol] = cur.type;
					Array.add(new Virus(cur.type, checkRow, checkCol));
				}
			}
			
//			DEBUG
//			printMap();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Map = new int[N + 1][N + 1];
		Array = new ArrayList<Virus>();		
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				int type = Integer.parseInt(st.nextToken());
				Map[r][c] = type;
				if (type >= 1) {
					Array.add(new Virus(type, r, c));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		BFS();
		System.out.println(Map[X][Y]);
	}
	
	public static void printMap() {
		System.out.println("\n-------------------------------");
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				System.out.print(Map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------\n");
	}
}
