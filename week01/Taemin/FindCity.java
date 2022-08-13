package com.ssafy.cfj.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 특정 거리의 도시 찾기 
 * @author devTaemin
 *
 */
public class FindCity {

	public static void main(String[] args) throws IOException {
		/** Number of city, Number of edge, Target, Start */
		int N, M, K, X;
		
		/** Infinite number */
		final int INF = (int)1e9;
		
		/** Distance array */
		int[] Distance = null;
		
		/** stack for DFS */
		Stack<Integer> stack = new Stack<Integer>();
		
		/** Information about the edges */
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		/** Answers */
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		/** Skip 0 index */
		Distance = new int[N + 1];
		
		/** Initialize distance */
		Arrays.fill(Distance, INF);
		
		/** Initialize graph */
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		/** Get the information of graph */
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(to);
		}
		
		
		/** DFS */
		Distance[X] = 0;
		stack.add(X);
		while(!stack.isEmpty()) {
			int city = stack.pop();
			
			for (int i = 0; i < graph.get(city).size(); i++) {
				int move = graph.get(city).get(i);
				
				/** Compare the distance */
				if (Distance[city] + 1 < Distance[move]) {
					Distance[move] = Distance[city] + 1;
					
					/** 
					 * If the distance is over the target distance,
					 * It is useless to compare more for finding answers
					 * */
					if (Distance[move] <= K) {
						stack.push(move);						
					}
				}
			}
		}
		
		/** Find the answers */
		for (int i = 1; i <= N; i++) {
			if (Distance[i] == K) {
				result.add(i);
			}
		}
		
		/** Sort int ascending order*/
		Collections.sort(result);
		
		/** Print out the result */
		if (result.size() == 0) {
			System.out.println(-1);
		} else {
			for (int elem : result) {
				System.out.println(elem);
			}
		}
	}
}