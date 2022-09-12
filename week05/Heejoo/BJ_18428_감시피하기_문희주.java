package DFS_BFS;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * NxN 크기의 복도
 * 특정한 위치에 선생님, 학생, 장애물 위치 가능
 * 선생님들은 자신의 위치에서 상하좌우 4가지 방향 감시 진행
 * 단, 장애물이 위치한 경우, 장애물 뒤편은 볼 수 없음
 * 학생들은 복도의 빈 칸 중에서 장애물을 설치할 위치를 골라, 정확히 3개의 장애물 설치
 * 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지 계산하는 프로그램
 */

public class BJ_18428_감시피하기_문희주 {
	static int N;
	static boolean result = false;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static List<Point> tList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 복도 크기 N
		
		// 복도 배열 정보 입력
		char[][] Map = new char[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				Map[i][j] = st.nextToken().charAt(0);
				
				if(Map[i][j] == 'T') tList.add(new Point(i, j));
			}
		}
		
		DFS(0, Map, 0, 0);
		
		if(result) System.out.println("YES");
		else System.out.println("NO");
	}
	
	public static void DFS(int count, char[][] map, int i, int j) {
		if(count == 3) {
			if(check(map)) result = true;
			return;
		}
		
		for(; i < N; i++) {
			for(; j < N; j++) {
				if(map[i][j] != 'X') continue;
				
				map[i][j] = 'O';
				DFS(count+1, map, i, j+1);
				map[i][j] = 'X';
				
				if(result) return;
			}
			j = 0;
		}
		
	}
	
	public static boolean check(char[][] map) {
		for(int i = 0; i < tList.size(); i++) {
			Point t = tList.get(i);
			for(int j = 0; j < 4; j++) {
				int dx = t.x + dir[j][0];
				int dy = t.y + dir[j][1];
				while(rangeCheck(dx, dy) && map[dx][dy] != 'O') {
					if(map[dx][dy] == 'S') return false;
					
					dx += dir[j][0];
					dy += dir[j][1];
				}
			}
		}
		
		return true;
	}
	
	public static boolean rangeCheck(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) return false;
		
		return true;
	}

}
