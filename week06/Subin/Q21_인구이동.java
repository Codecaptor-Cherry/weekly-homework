import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q21_인구이동 {

	static int N, L, R, A[][];
	static boolean[][] visited;

	static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solve());
	}

	private static int solve() {
		int day = 0;
		
		while (true) {
			visited = new boolean[N][N];
			
			int cnt = 0; // 연합의 수
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						cnt += bfs(r, c);
					}
				}
			}
			
			// 연합이 없다면 -> 인구 이동이 없다면 break
			if (cnt == 0) break;
			
			++day;
		}
		
		return day;
	}

	private static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		List<int[]> group = new ArrayList<>();

		int count = 0; // 연합을 이루고 있는 칸의 개수
		int sum = 0; // 연합의 인구 수
		
		q.add(new int[] {r, c});
		group.add(new int[] {r, c});
		visited[r][c] = true; // 방문 체크
		
		// bfs
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 연합 이루기
			++count;
			sum += A[cur[0]][cur[1]];
			group.add(cur);
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dir[d][0];
				int nc = cur[1] + dir[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue ;
				
				// 국경선을 공유한다면 큐에 넣기
				int diff = Math.abs(A[cur[0]][cur[1]] - A[nr][nc]);
				if (L <= diff && diff <= R) {
					visited[nr][nc] = true; // 방문 체크
					q.add(new int[] {nr, nc});
				}
			}
		}
		
		// 연합이 없다면 return 0;
		if (count == 1) return 0;
		
		// 인구 이동
		for (int[] pos: group) {
			A[pos[0]][pos[1]] = sum / count;
		}
		
		return 1;
	}
	
}
