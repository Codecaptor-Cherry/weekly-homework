import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4485
// 이거랑 같은 문제,,
public class Q39_화성탐사 {

	static int T, N, map[][], D[][];
	
	static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 방향 배열
	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			D = new int[N][N];
			for (int i = 0; i < N; i++) Arrays.fill(D[i], INF);
			dijkstra();
			sb.append("#").append(t).append(" ").append(D[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dijkstra() {
		// int 배열의 두 번째 요소를 기준으로 최소힙
		// 0: r, 1: c, 2: cost(누적 에너지 소모량)
		PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[2] - i2[2]);
	
		// 화성 탐사 기계는 [0][0] 위치에서 출발한다.
		pq.offer(new int[] {0, 0, map[0][0]});
		D[0][0] = map[0][0];
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			// 현재 경로보다 짧은 경로를 알고 있다면 continue
			if (cur[2] > D[cur[0]][cur[1]]) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				
				// 범위를 벗어난다면 continue
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				// 최단 경로를 갱신한다.
				if (D[nr][nc] > D[cur[0]][cur[1]] + map[nr][nc]) {
					D[nr][nc] = D[cur[0]][cur[1]] + map[nr][nc];
					pq.offer(new int[] {nr, nc, map[nr][nc]});
				}
			}
		}
	}
	
}
