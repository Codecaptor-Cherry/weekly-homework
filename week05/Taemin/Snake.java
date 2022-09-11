package com.ssafy.cfj.implemantation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Snake {
	
	public static class Node {
		public int row;
		public int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static int N, K, L, Second;
	public static int[][] Map;
	public static HashMap<Integer, Character> Direction;
	public static Deque<Node> body;
	
	/** Direction (North, East, South, West) */
	public static int[] Dx = { -1, 0, 1, 0 };
	public static int[] Dy = { 0, 1, 0, -1 };
	
	public static int turnRight(int cur) {
		int next = cur + 1;
		if (next > 3) {
			next = 0;
		}
		
		return next;
	}
	
	public static int turnLeft(int cur) {
		int next = cur - 1;
		if (next < 0) {
			next = 3;
		}
		
		return next;
	}
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	public static void play() {
		int d = 1;
		
		while(true) {
//			// DEBUG
//			System.out.println("\n====================================");
//			System.out.println(Second);
//			printMap();
//			System.out.println("====================================\n");
			
			/** Increment second */
			Second++;
			
			/** Current head */
			Node head = body.peekFirst();
			int nextRow = head.row + Dx[d];
			int nextCol = head.col + Dy[d];
			
			/** Check range */
			if (!validateRange(nextRow, nextCol)) return;
			
			/** Check body */
			if (Map[nextRow][nextCol] == 1) return;
			
			/** Check apple */
			if (Map[nextRow][nextCol] != 2) {
				Node tail = body.pollLast();
				Map[tail.row][tail.col]= 0; 
			} 
			
			/** Move */
			Map[nextRow][nextCol] = 1;
			Node newHead = new Node(nextRow, nextCol);
			body.addFirst(newHead);
			
			/** change direction */
			if (Direction.containsKey(Second)) {
				char turn = Direction.get(Second);
				
				if (turn == 'L') {
					d = turnLeft(d);
				} else if (turn == 'D') {
					d = turnRight(d);
				}
			}
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		Map = new int[N + 1][N + 1];
		
		/** Apple (2) */
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			Map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}
		
		
		L = Integer.parseInt(br.readLine());
		Direction = new HashMap<Integer, Character>(); 
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int second = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			
			Direction.put(second, d);
		}
		
		body = new ArrayDeque<>();
		body.addFirst(new Node(1, 1));
		Map[1][1] = 1;
		Second = 0;
		play();
		
		System.out.println(Second);
	}

	
	public static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(Map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
