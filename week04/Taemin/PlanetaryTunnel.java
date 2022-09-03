package com.ssafy.cfj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PlanetaryTunnel {
	
	static class Planet {
		int num;
		int x;
		int y;
		int z;
		
		public Planet(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Tunnel implements Comparable<Tunnel>{
		int from;
		int  to;
		long distance;
		
		public Tunnel(int from, int to, long distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		@Override
		public int compareTo(Tunnel other) {
			return Long.compare(this.distance, other.distance);
		}
	}
	
	static int N;
	static ArrayList<Planet> Planets;
	static PriorityQueue<Tunnel> Tunnels;
	
	static int[] Parents;
	
	static int findParent(int x) {
		if (Parents[x] != x) {
			return Parents[x] = findParent(Parents[x]);
		}
		
		return Parents[x];
	}
	
	static boolean union(int x, int y) {
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Planets = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Planets.add(new Planet(i, Integer.parseInt(st.nextToken()),
					 				 Integer.parseInt(st.nextToken()),
					 				 Integer.parseInt(st.nextToken())));
		}
		
		
		Tunnels = new PriorityQueue<>();
		Collections.sort(Planets, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.x, o2.x);
			}
		});
		
		for (int i = 0; i < N - 1; i++) {
			Planet from = Planets.get(i);
			Planet to = Planets.get(i + 1);
			Tunnels.add(new Tunnel(from.num, to.num, Math.abs(from.x - to.x)));
		}
		
		Collections.sort(Planets, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.y, o2.y);
			}
		});
		
		for (int i = 0; i < N - 1; i++) {
			Planet from = Planets.get(i);
			Planet to = Planets.get(i + 1);
			Tunnels.add(new Tunnel(from.num, to.num, Math.abs(from.y - to.y)));
		}
		
		Collections.sort(Planets, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.z, o2.z);
			}
		});
		
		for (int i = 0; i < N - 1; i++) {
			Planet from = Planets.get(i);
			Planet to = Planets.get(i + 1);
			Tunnels.add(new Tunnel(from.num, to.num, Math.abs(from.z - to.z)));
		}
		
		
		Parents = new int[N];
		for (int i = 0; i < N; i++) {
			Parents[i] = i;
		}
		
		int count = 0;
		long totalDistance = 0;
		
		while (true) {
			if (count == N - 1) break;
			Tunnel tunnel = Tunnels.poll();
			int from = tunnel.from;
			int to = tunnel.to;
			long distance = tunnel.distance;
			
			if (union(from, to)) {
				count++;
				totalDistance += distance;

			}
		}
		
		System.out.println(totalDistance);
	}

}
