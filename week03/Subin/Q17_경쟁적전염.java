import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q17_경쟁적전염 {
	
	static int N, K, S, X, Y;
	static int[][] map;
	static Queue<int[]> q = new LinkedList<>(); // 0: r, 1: c, 2: virus number
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 방향 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		// 0: r, 1: c, 2: virus number
		// 바이러스 번호를 기준으로 최소힙
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] != 0) {
					pq.add(new int[] {i, j, map[i][j]});
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		// 최소힙을 사용하여 큐에 바이러스 번호가 작은 순서대로 넣기
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			q.add(cur);
		}
		
		bfs();
		
		System.out.println(map[X - 1][Y - 1]); // 출력
	}

	public static void bfs() {
		int time = 0;
		
		// S초동안 bfs
		while (time++ < S) {
			int size = q.size();
			
			while (size-- > 0) {
				int[] cur = q.poll();
			
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					
					// 범위 체크, 바이러스 존재 여부 체크
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != 0) continue;
					
					q.add(new int[] {nr, nc, cur[2]});
					map[nr][nc] = cur[2];
				}
			}
		}
	}
	
}
