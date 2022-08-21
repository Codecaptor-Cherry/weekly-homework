package DFS_BFS;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * NxM 직사각형 크기의 연구소 = 빈 칸(0) + 벽(1)
 * 일부 칸 바이러스 존재 ~ 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있음
 * 새로 세울 수 있는 벽의 개수는 "3개"
 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳 = 안전 영역
 * 연구소의 지도가 주어졌을 때, 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램
 * 
 * 1. 3개의 벽 위치 조합
 * 2. 
 */
public class BJ_14502_연구소_문희주 {
	static int n, m, max = 0;
	static int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	static char[][] Map;
	static List<Point> pList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로 크기 N
		m = Integer.parseInt(st.nextToken()); // 가로 크기 m
		
		// 데이터 입력
		Map = new char[n][m]; // 연구소 지도 정보
		pList = new ArrayList<>(); // 바이러스 좌표 리스트
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				char input = st.nextToken().charAt(0);
				Map[i][j] = input;
				
				// 바이러스 좌표를 리스트에 따로 저장
				if(input == '2') {
					pList.add(new Point(i, j));
				}
			}
		}
		
		DFS(0, 0, 0);
		
		System.out.println(max);
	}
	
	// 벽 세우기
	// 현재까지 세운 벽의 개수, 벽을 세울 좌표(x, y)
	// (x, y)에 벽을 세우면 다음 벽은 (x, y+1)부터 탐색
	public static void DFS(int cnt, int x, int y) {
		// 3개의 벽을 모두 세움
		if(cnt == 3) {
			char[][] map = new char[n][m];
			map = copy(Map);
			
			BFS(map); // 바이러스 퍼뜨리기			
			max = Math.max(max, getArea(map)); // 안전영역 대소비교 및 갱신
			
			return;
		}
		
		int j = y; // x행은 y부터 탐색
		for(int i = x; i < n; i++) {
			for(; j < m; j++) {
				if(Map[i][j] == '0') {
					Map[i][j] = '1'; // (i, j)에 벽 세우기
					DFS(cnt+1, i, j); // 벽 개수+1. 다음 벽은 (x, y+1)부터 탐색해야하나 y+1을 넘기면 마지막 열의 경우 범위를 벗어나서 (i, j)를 그대로 넘겨줌,,,
					Map[i][j] = '0'; // 다음 DFS를 위해 복구
				}
			}
			j = 0; // x+1 이후는 다시 0열부터 탐색
		}
	}
	
	// 바이러스 퍼뜨리기
	public static void BFS(char[][] map) {
		Queue<Point> queue = new LinkedList<>();
		
		for(int i = 0; i < pList.size(); i++) queue.add(pList.get(i));
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			// 상하좌우 탐색
			for(int i = 0; i < 4; i++) {
				int dx = now.x + dir[i][0];
				int dy = now.y + dir[i][1];
				
				if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue; // 배열 범위를 벗어나면 넘어가기
				
				// 빈 칸일 경우 바이러스를 퍼뜨리고, 다음 탐색을 위해 큐에 넣어주기
				if(map[dx][dy] == '0') {
					map[dx][dy] = '2';
					queue.add(new Point(dx, dy));
				}
			}
		}
	}
	
	// 안전 영역 구하기
	public static int getArea(char[][] map) {
		int sum = 0;
		
		for(char[] array : map) {
			for(char c : array) {
				if(c == '0') sum++;
			}
		}
		
		return sum;
	}
	
	// 2차원 배열 복사
	public static char[][] copy(char[][] ori){
		char[][] copyArray = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			copyArray[i] = ori[i].clone();
		}
		
		return copyArray;
	}
}
