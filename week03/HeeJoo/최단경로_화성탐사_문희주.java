package 최단경로;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

/*
 * 백준 제출 시 시간초과남 ..ㅠ
 * 출발 지점에서 목표 지점까지 이동할 때 항상 최적의 경로를 찾도록 개발
 * NxN 2차원 공간. 각각의 칸을 지나기 위한 비용 존재
 * (0, 0)에서 (N-1, N-1)로 이동하는 최소 비용을 출력하는 프로그램
 * 현재 위치에서 상하좌우 인접한 곳으로 1칸씩 이동 가능
 */
public class 최단경로_화성탐사_문희주 {
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
	
	static int N, minCost;
	static int[][] map, dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
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
			
			minCost = (int)1e9;
			visited = new boolean[N][N];
			BFS(new Point(0, 0), map[0][0]);
			
			sb.append(minCost + "\n");
		}
		System.out.println(sb);
	}
	
	public static void BFS(Point p, int cost) {
		if(cost > minCost) return; // 가지치기 : 최소 비용을 넘어가는 경우 더 이상 수행 xxx
		if(p.x == N-1 && p.y == N-1) {
			minCost = cost; // 위에서 가지치기를 하기때문에 그냥 최솟값 갱신
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int dx = p.x + dir[i][0];
			int dy = p.y + dir[i][1];
			
			if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
			if(visited[dx][dy]) continue;
			visited[dx][dy] = true;
			BFS(new Point(dx, dy), cost + map[dx][dy]);
			visited[dx][dy] = false;
		}
	}

}
