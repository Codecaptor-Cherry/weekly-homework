import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q45_최종순위 {

	static int N, M, T[], inDegree[];
	static boolean[][] adjArr;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			
			T = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				T[i] = Integer.parseInt(st.nextToken());
			}

			adjArr = new boolean[N + 1][N + 1]; // 인접행렬
			inDegree = new int[N + 1]; // 정점별 진입차수
			for (int i = 1; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					adjArr[T[i]][T[j]] = true;
					++inDegree[T[j]];
				}
			}
			
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a팀의 순위가 b팀보다 높다면 a와 b의 값을 교체
				if (adjArr[a][b]) {
					int temp = a;
					a = b;
					b = temp;
				}
				
				adjArr[a][b] = true;
				adjArr[b][a] = false;
				--inDegree[a];
				++inDegree[b];
			}
			
			topologySort();
		}
		
		System.out.println(sb);
	}	
	
	private static void topologySort() {
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) queue.add(i);
		}
		
		while (!queue.isEmpty()) {
			// 확실한 순위를 찾을 수 없다면 "?"를 출력
			if (queue.size() != 1) {
				sb.append("?\n");
				return ;
			}

			int cur = queue.poll();
			list.add(cur);
			
			for (int i = 1; i <= N; i++) {
				if (!adjArr[cur][i]) continue;
				if (--inDegree[i] == 0) queue.add(i);
			}
		}
		
		// 데이터에 일관성이 없어서 순위를 정할 수 없는 경우에는 "IMPOSSIBLE"을 출력
		if (list.size() != N) {
			sb.append("IMPOSSIBLE\n");
			return ;
		}
		
		for (int n: list) sb.append(n).append(" ");
		sb.append("\n");
	}
}
