package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N개의 집과 M개의 도로
 * 각 집은 0번 ~ N-1번
 * 모든 도로에는 가로등 구비. 특정한 도로의 가로등을 하루 동안 켜기 위한 비용 = 해당 도로의 길이
 * 일부 가로등이 켜진 도로만으로도 모든 마을을 갈 수 있도록 하여, 절약할 수 있는 최대 금액을 출력하는 프로그램
 * 
 * 크루스칼 이용 ?
 */

public class 그래프_43_어두운길_문희주 {
	static final int INF = Integer.MAX_VALUE;
	// output : 51
	static String src = "7 11\r\n" +
						"0 1 7\r\n" +
						"0 3 5\r\n" +
						"1 2 8\r\n" +
						"1 3 9\r\n" +
						"1 4 7\r\n" +
						"2 4 5\r\n" +
						"3 4 15\r\n" +
						"3 5 6\r\n" +
						"4 5 8\r\n" +
						"4 6 9\r\n" +
						"5 6 11\r\n";
	
	static int[] parent;
	
	static int find(int x) {
		if(x != parent[x]) return parent[x] = find(parent[x]);
		return x;
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 수 N
		int M = Integer.parseInt(st.nextToken()); // 도로의 수 M
		
		// 정점 i의 부모를 자기 자신으로 초기화
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		// M개의 도로 정보 입력
		// {X, Y, Z} : X번 집과 Y번 집 사이에 양방향 도로가 있으며, 그 길이는 Z
		int cost = 0; // 최종 출력 : 절약할 수 있는 최대 금액 ~ 전체 가로등 활성화 비용 - 활성화한 일부 가로등 비용
		int[][] graph = new int[M][3];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			cost += graph[i][2]; // 전체 가로등 활성화 비용
		}
			
		// 크루스칼 수행
		Arrays.sort(graph, (o1, o2) -> Integer.valueOf(o1[2] - o2[2]));	// 도로의 길이를 기준으로 오름차순 정렬
		for(int i = 0; i < M; i++) {
			if(find(graph[i][0]) != find(graph[i][1])) { // X와 Y가 연결되지 않았다면
				union(graph[i][0], graph[i][1]); // X와 Y 연결
				cost -= graph[i][2]; // 활성화한 비용 빼주기
			}
		}
		
		System.out.println(cost);
	}
}
