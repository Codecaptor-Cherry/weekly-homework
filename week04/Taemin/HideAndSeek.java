package com.ssafy.cfj.shortroute;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HideAndSeek {
	
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] distance;
	static boolean[] visited;
	static final int INF = (int)1e9;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		distance = new int[N + 1];
		visited = new boolean[N + 1];
		Arrays.fill(distance, INF);
		
		distance[1] = 0;
		
		for (int i = 0; i < N; i++) {
			int index = find();
			if (index == -1) break;
			
			visited[index] = true;
			
			for (int j = 0; j < adjList.get(index).size(); j++) {
				if (!visited[adjList.get(index).get(j)] &&
					distance[adjList.get(index).get(j)] > distance[index] + 1) {
					distance[adjList.get(index).get(j)] = distance[index] + 1;
				}
			}
		}
		
		System.out.println(Arrays.toString(distance));
		
		// find maximum
		int maxIndex = 0;
		int max = 0;
		int count = 0;
		
		for (int i = 1; i <= N; i++) {
			if (distance[i] > max) {
				max = distance[i];
				maxIndex = i;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (distance[i] == max) count++;
		}
		
		System.out.println(maxIndex + " " + max + " " + count);
		
	}
	
	public static int find() {
		int index = -1;
		int min = INF;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && distance[i] < min) {
				index = i;
				min = distance[i];
			}
		}
		
		return index;
	}

}
