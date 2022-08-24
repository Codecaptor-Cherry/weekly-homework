package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * NxN 크기의 시험관
 * 특정 위치 바이러스 존재(1번~K번 중 바이러스 종류 중 하나)
 * 모든 바이러스는 1초마다 상,하,좌,우 방향으로 증식
 * 단, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식
 * 또한, 증식 과정에서 특정한 칸에 이미 어떠한 바이러스가 존재한다면, 그 곳에서 다른 바이러스가 들어갈 수 없음
 * 시험관의 크기와 바이러스의 위치 정보가 주어졌을 때, S초가 지난 후에 (x, y)에 존재하는 바이러스의 종류를 출력하는 프로그램
 * 만약 S초가 지난 후에 해당 위치에 바이러스가 존재하지 않으면 0 출력
 * 문제의 좌표는 (1, 1)부터 시작
 * 
 * tnqlsdk .. tkfkdgo ...
 */
class Virus{
	int x, y, grade, time;
	
	public Virus(int x, int y, int grade, int time) {
		this.x = x;
		this.y = y;
		this.grade = grade;
		this.time = time;
	}
}
public class BJ_18405_경쟁적전염_문희주 {
	static int N, S;
	static Virus[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static List<Virus> vList;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 시험관의 크기 N
		int K = Integer.parseInt(st.nextToken()); // 바이러스 종류 K
		
		// 시험관 정보 입력
		map = new Virus[N][N];
		vList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int grade =Integer.parseInt(st.nextToken());
				
				if(grade != 0) {
					map[i][j] = new Virus(i, j, grade, 0);
					vList.add(new Virus(i, j, grade, 0));
				}
				else {
					map[i][j] = null;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine()); // 마지막 줄은 S, X, Y
		S = Integer.parseInt(st.nextToken()); // 전염 시간 S
		int X = Integer.parseInt(st.nextToken()); // 결과 행 X
		int Y = Integer.parseInt(st.nextToken()); // 결과 열 Y
		
		visited = new boolean[N][N];
		BFS();
		
		if(map[X-1][Y-1] == null) System.out.println(0);
		else System.out.println(map[X-1][Y-1].grade);
	}
	
	public static void BFS() {
		Queue<Virus> q = new LinkedList<>();
		
		for(Virus v : vList) {
			q.add(v);
			visited[v.x][v.y] = true;
		}
		
		int s = 1;
		int size = q.size();
		int count = 0;
		
		while(!q.isEmpty()) {
			if(s > S) return;
			Virus v = q.poll();
			//visited[v.x][v.y] = true;
			
			for(int i = 0; i < 4; i++) {
				int dx = v.x + dir[i][0];
				int dy = v.y + dir[i][1];
				if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
				
				// Virus[][] map
				if(map[dx][dy] == null) {
					map[dx][dy] = new Virus(dx, dy, v.grade, s);
				}
				else {
					if(!visited[dx][dy] && s == map[dx][dy].time){
						if(v.grade < map[dx][dy].grade) map[dx][dy] = new Virus(dx, dy, v.grade, s);
					}
				}
			}
			
			count++; // 차수
			// 3개 > 큐 몇개 꺼냄 ?
			// 3개 > 다음 차수
			
			if(count == size) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] != null && !visited[i][j]) {
							visited[i][j] = true;
							q.add(map[i][j]);
						}
					}
				}
				
				print(map);
				
				count = 0;
				size = q.size();
				s++;
				
				//print(visited);
			}
			
		}
	}
	public static void print(Virus[][] arr) {
		for(Virus[] array : arr) {
			for(Virus v : array) {
				if (v != null) {
					System.out.print(v.grade + " ");
				}
				else System.out.print("  ");
			}
			System.out.println();
		}
	}
	
	public static void print(boolean[][] arr) {
		for(boolean[] array : arr) {
			for(boolean v : array) {
				System.out.print(v + " ");
			}
			System.out.println();
		}
	}
}
