import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q15_특정거리의도시찾기 {
	static int N, M, K, X;
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer> ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		// 인접리스트 만들기
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj[start].add(end);
		}
		
		visited = new boolean[N + 1];
		ans = new ArrayList<>();
		search(X);
		
		if (ans.isEmpty()) { // 답이 없다면 -1 출력
			System.out.println("-1");
			return ;
		}
		
		StringBuilder sb = new StringBuilder();
		Collections.sort(ans); // 도시는 오름차순 출력
		for (int i = 0; i < ans.size(); i++) {
			sb.append(ans.get(i)).append("\n");
		}
		System.out.print(sb);
	}
	
	// BFS
	public static void search(int start) {
		Queue<int[]> q = new LinkedList<>(); 
		q.add(new int[]{start, 0}); // 큐에 다음 노드와 거리 저장
		visited[start] = true; // 방문체크
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[1] == K) ans.add(cur[0]); // 해당 도시까지의 거리가 K이면 ans 리스트에 추가
			
			for (int next: adj[cur[0]]) {
				if (!visited[next]) { // 방문하지 않은 도시라면
					q.add(new int[] {next, cur[1] + 1}); // 큐에 추가하고
					visited[next] = true; // 방문체크
				}
			}
		}
	}
	
}