package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1 ~ N번 헛간 중 하나를 골라 숨을 수 있음
 * 술래는 항상 1번 헛간에서 출발
 * 전체 맵에는 총 M개의 양방향 통로 존재하며, 전체 맵은 항상 어떤 헛간에서 다른 헛간으로 도달이 가능한 형태로 주어짐
 * 최단거리 : 지나야 하는 길의 최소 개수
 * 1번 헛간으로부터 최단 거리가 가장 먼 헛간의 번호를 구하는 프로그램
 */

class Node implements Comparable<Node>{
	int index, distance;

	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return this.distance - o.distance;
	}
	
	
}

public class 최단경로_40_숨바꼭질_문희주 {
	// answer : 4 2 3
	static String src = "6 7\r\n" +
						"3 6\r\n" +
						"4 3\r\n" +
						"3 2\r\n" +
						"1 3\r\n" +
						"1 2\r\n" +
						"2 4\r\n" +
						"5 2\r\n";
	
	static int N, INF = (int)1e9;
	static ArrayList<ArrayList<Node>> graph;
	static int[] dist;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new StringReader(src));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 헛간의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 통로의 개수 M
		
		// 그래프 초기화
		graph = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		// M개의 통로 정보 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			
			// 양방향 통로
			graph.get(A).add(new Node(B, 1));
			graph.get(B).add(new Node(A, 1));
		}

		// 최단 거리 테이블 생성
		dist = new int[N];
		Arrays.fill(dist, INF);
		dijkstra(0); // 1번이지만 0인덱스 사용 ~ 0
		
		// 출력 : 숨어야 하는 가장 작은 헛간 번호, 해당 헛간까지의 거리, 동일한 거리의 헛간 개수
		int index = 0, d = 0, count = 0;
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			if(d < dist[i]) {
				index = i + 1;
				d = dist[i];
				count = 1;
			}
			else if(d == dist[i]) {
				count++;
			}
		}
		
		System.out.println(index + " " + d + " " + count);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(start, 0)); // 출발 좌표 ~ 비용 0
		
		dist[start] = 0; // 자기 자신은 0
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int now = node.index; // 현재 노드 번호
			int d = node.distance; // 현재 노드까지의 비용
			
			if(dist[now] < d) continue; // 이미 최솟값이라면 갱신 x
			
			// 현재 노드와 인접한 노드들 확인
			for(int i = 0; i < graph.get(now).size(); i++) {
				Node next = graph.get(now).get(i); // 다음 노드
				int cost = dist[now] + next.distance; // 현재 노드에서 다음 노드로 넘어가는 총 비용
				
				// 현재 노드를 거쳐서 다음 노드로 이동하는 거리가 더 짧은 경우
				if(cost < dist[next.index]) {
					dist[next.index] = cost;
					pq.offer(new Node(next.index, cost));
				}
			}
		}
		
	}
}
