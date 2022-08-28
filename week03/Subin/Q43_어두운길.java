import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q43_어두운길 {

	static int N, M, parents[];
	static Edge[] edges;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Edge[M];
		long ans = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken()));
			ans += edges[i].weight; // 모든 가로등의 비용 더하기
		}
		Arrays.sort(edges);
		
		System.out.println(ans);
		
		make();
		
		int cnt = 0;
		for (int i = 0; i < edges.length; i++) {
			if (union(edges[i].from, edges[i].to)) {
				ans -= edges[i].weight; // 모든 가로등의 비용에서 가로등을 연결할 수 있는 최소비용 빼주기
				System.out.println(edges[i].weight);
				if (++cnt == N) break;
			}
		}
		
		System.out.println(ans); // 남은 비용이 절약할 수 있는 최대비용이다.
	}
	
	public static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return false;
		
		parents[y] = x;
		return true;
	}
	
	public static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
}
