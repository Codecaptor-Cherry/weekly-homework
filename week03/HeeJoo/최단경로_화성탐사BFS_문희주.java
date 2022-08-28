package 최단경로;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 출발 지점에서 목표 지점까지 이동할 때 항상 최적의 경로를 찾도록 개발
 * NxN 2차원 공간. 각각의 칸을 지나기 위한 비용 존재
 * (0, 0)에서 (N-1, N-1)로 이동하는 최소 비용을 출력하는 프로그램
 * 현재 위치에서 상하좌우 인접한 곳으로 1칸씩 이동 가능
 */

public class 최단경로_화성탐사BFS_문희주 {
	static String str1 = "3\r\n" +
						"3\r\n" +
						"5 5 4\r\n" +
						"3 9 1\r\n" +
						"3 2 7\r\n" +
						"5\r\n" +
						"3 7 2 0 1\r\n" +
						"2 8 0 9 1\r\n" +
						"1 2 1 8 1\r\n" +
						"9 8 9 2 0\r\n" +
						"3 6 5 1 5\r\n"+
						"7\r\n" +
						"9 0 5 1 1 5 3\r\n" +
						"4 1 2 1 6 5 3\r\n" +
						"0 7 6 1 6 8 5\r\n" +
						"1 1 7 8 3 2 3\r\n" +
						"9 4 0 7 6 4 1\r\n" +
						"5 8 3 2 4 8 3\r\n" +
						"7 4 8 4 8 3 4\r\n";
	
	static int N, minCost, INF = (int)1e9;
	static int[][] map, DP, dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(str1));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 탐사 공간의 크기 N
			
			// 탐사 공간 정보 입력
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			DP = new int[N][N]; // (x, y)까지 최단 거리를 기록하는 배열
			for(int i = 0; i < N; i++) Arrays.fill(DP[i], INF); // 최댓값 INF로 초기화
			DP[0][0] = map[0][0]; // 시작 좌표(0, 0)은 map[0][0]으로 고정적
			
			dijkstra();
			sb.append(DP[N-1][N-1] + "\n");
		}
		System.out.println(sb);
	}
	
	public static void dijkstra(){
		// {x, y, cost}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]); // cost를 기준으로 오름차순 정렬
		
		pq.offer(new int[]{0, 0, map[0][0]}); // 시작 좌표(0, 0)
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if(now[2] > DP[now[0]][now[1]]) continue;
			
			// 사방향 탐색
			for(int i = 0; i < 4; i++) {
				int dx = now[0] + dir[i][0];
				int dy = now[1] + dir[i][1];
				
				if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue; // 범위 체크
				
				// 최솟값 갱신 ~ 우선순위 큐에 다음 탐색으로 넣어주기
				// 현재 기록된 이동 좌표까지의 최소 비용 vs 이전 좌표 + 이동 좌표 통과 비용
				if(DP[dx][dy] > DP[now[0]][now[1]] + map[dx][dy]) {
					DP[dx][dy] = DP[now[0]][now[1]] + map[dx][dy];
					pq.offer(new int[] {dx, dy, DP[dx][dy]});
				}
			}
		}
	}

}
