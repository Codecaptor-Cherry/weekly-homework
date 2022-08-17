import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16_연구소 {
	
	static int N, M, max = Integer.MIN_VALUE;
	static char[][] Map;
	static ArrayList<Point> Virus;
	
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	
	static class Point {
		int y;
		int x;
		
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new char[N][M];
		Virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = st.nextToken().charAt(0);
				if (Map[i][j] == '2') {
					Virus.add(new Point(i, j));
				}
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(max);
	}
	
	// 벽 세우기
	public static void dfs(int r, int c, int cnt) {
		if (cnt == 3) {	
			char[][] map = new char[N][M];
			for (int i = 0; i < N; i++) map[i] = Arrays.copyOf(Map[i], M);

			bfs(map); // 바이러스 퍼뜨리기
			getMax(map); // 안전 영역 크기의 최댓값 구하기
			return ;
		}

		int j = c;
		for (int i = r; i < N; i++) {
			for (; j < M; j++) {
				if (Map[i][j] == '0') {
					Map[i][j] = '1'; // 벽 세우기
					dfs(i, j, cnt + 1);
					Map[i][j] = '0'; // 원상복구
				}
			}
			j = 0;
		}
	}
	
	// 바이러스 퍼뜨리기
	public static void bfs(char[][] map) {
		Queue<Point> q = new LinkedList<>();
		
		for (Point p: Virus) q.add(p);
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				// 범위 체크, 벽 체크
				if (ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == '1') continue;
				
				if (map[ny][nx] == '0') {
					map[ny][nx] = '2';
					q.add(new Point(ny, nx));
				}
			}
		}
	}
	
	// 안전 영역의 크기 구하고 최댓값 갱신
	public static void getMax(char[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') cnt++;
			}
		}
		max = Math.max(max, cnt);
	}
}
