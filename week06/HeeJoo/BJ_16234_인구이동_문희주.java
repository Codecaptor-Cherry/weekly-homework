package DFS_BFS;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * NxN 크기의 땅
 * (r, c)에 A[r][c]명 살고 있음
 * 인접한 나라 사이에는 국경선 존재 ..
 * 
 * 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루동안 연다.
 * 2. 열어야하는 국경선이 모두 열렸다면, 인구 이동 시작
 * 3. 연합을 이루고 있는 각 칸의 인구수는 (연합 인구수)/(연합을 이루는 칸의 개수)가 된다. 편의상 소수점 out
 * 4. 연합을 해체하고, 모든 국경선 닫기
 * 
 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램
 * 
 * 이동 인구 수가 0이면 이동 종료
 * (0, 0)부터 (N-1, N-1)까지 사방향 탐색 ~ 조건을 만족하면 좌표와 배열값 저장, 방문처리
 * 연합이 완성되면 인구 수 갱신
 */

public class BJ_16234_인구이동_문희주 {
	static int N, L, R;
	static int[][] Map, dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	static boolean[][] visited;
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		boolean flag = true;
		
		visited = new boolean[N][N];
		while(flag) {
			if(BFS() == 0) flag = false;
			else result++;
			
//			print();
//			System.out.println();
		}
		
		System.out.println(result);
	}
	
	public static int BFS() {
		int count = 0;
		
		// 모든 좌표에 대해 탐색
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				
				Queue<Point> q = new LinkedList<>(); // BFS
				List<Point> pList = new ArrayList<>(); // 연합 좌표
				
				q.add(new Point(i, j));
				pList.add(new Point(i, j));
				visited[i][j] = true;
				
				int sum = Map[i][j];
				
				// BFS 탐색
				while(!q.isEmpty()) {
					Point p = q.poll();
					
					for(int k = 0; k < 4; k++) {
						int nx = p.x + dir[k][0];
						int ny = p.y + dir[k][1];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if(visited[nx][ny]) continue;
						if(!check(Map[p.x][p.y], Map[nx][ny])) continue;
						
						q.add(new Point(nx, ny));
						pList.add(new Point(nx, ny));
						visited[nx][ny] = true;
						sum += Map[nx][ny];
						count++;
					}
				}
				
				// 연합 형성 완료
				if(count > 0) {
					int avg = sum / pList.size();
					
					for(int k = 0; k < pList.size(); k++) {
						Point p = pList.get(k);
						Map[p.x][p.y]= avg; 
					}
				}
				
			}
		}
		
		// 다음 인구이동을 위해 방문 배열 초기화
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		
		return count;
	}
	
	public static boolean check(int a, int b) {
		int r = Math.abs(a - b);
		if(r <= R && r >= L) return true;
		
		return false;
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(Map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
