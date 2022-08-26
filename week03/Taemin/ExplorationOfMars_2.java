package com.ssafy.cfj.shortroute;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 다익스트라 알고리즘을 이용한 화성탐사
 */

class Node implements Comparable<Node> {
	
	private int x;
	private int y;
	private int distance;
	
	public Node(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y; 
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	@Override
	public int compareTo(Node other) {
		return Integer.compare(this.distance, other.distance);
	}
}

public class ExplorationOfMars_2 {
	
	public static final int INF = (int)1e9;
	public static int[][] graph = new int[125][125];
	public static int[][] d = new int[125][125];
	public static int testCase, n;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		testCase = sc.nextInt();
		
		for (int tc = 0; tc < testCase; tc++) {
			n = sc.nextInt();
			
			/** 맵 입력 받기*/
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					graph[i][j] = sc.nextInt();
				}
			}
			
			/** 최단거리 테이블을 모두 무한으로 초기화 */
			for (int i = 0; i < n; i++) {
				Arrays.fill(d[i], INF);
			}
			
			/** 
			 * 초기 시작 위치 지정
			 * 우선 순위 큐에 시작 노드 삽입
			 * 최단 거리 테이블 설정
			 * */
			int x = 0, y = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(x, y, graph[x][y]));
			d[x][y] = graph[x][y];
			
			while(!pq.isEmpty()) {
				/** 움직일 수 있는 노드 중에서 거리가 가장 짧은 곳을 가져온다*/
				Node node = pq.poll();
				/** 해당 노드로의 거리를 가져온다 */
				int dist = node.getDistance();
				x = node.getX();
				y = node.getY();
				
				/** 해당 노드로의 거리가 저장한 최단 거리보다 크면 갈 필요가 없다*/
				if (d[x][y] < dist) continue;
				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					/** 범위 검증 */
					if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					
					/** 현재 노드를 거쳐서 새로운 노드로 가는 거리 */
					int cost = dist + graph[nx][ny];
					
					/** 현재 노드를 거쳐서 새로운 노드로 가는 거리가 기존에 저장된 최단 거리보다 짧으면 갱신하고 노드 입력*/
					if (cost < d[nx][ny]) {
						d[nx][ny] = cost;
						pq.offer(new Node(nx, ny, cost));
					}
				}
			}
			
			System.out.println(d[n - 1][n - 1]);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
