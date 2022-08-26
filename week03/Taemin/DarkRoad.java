package com.ssafy.cfj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DarkRoad {
	
	static int N, M;
	
	static PriorityQueue<Edge> Pqueue = new PriorityQueue<Edge>();
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int distance;
		
		public Edge(int from, int to, int distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge other) {
			return Integer.compare(this.distance, other.distance);
		}
	}
	
	static int[] Parents;
	
	public static boolean union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if (x == y) {
			return false;
		}
		
		if (x < y) {
			Parents[y] = x;
		} else {
			Parents[x] = y;
		}
		
		return true;
	}
	
	public static int findParent(int x) {
		if (Parents[x] != x) {
			return Parents[x] = findParent(Parents[x]);
		}
		
		return Parents[x];
	}
	
	public static void make() {
		Parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			Parents[i] = i;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		make();
		
		int total = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine().trim(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			total += distance;
			Pqueue.offer(new Edge(from, to, distance));
		}
		
		int count = 0;
		int min = 0;
		while (count < N - 1) {
			Edge e = Pqueue.poll();
			
			if (union(e.from, e.to)) {
				count++;
				min += e.distance;
			}
		}
		
		System.out.println(total - min);		
	}
}
