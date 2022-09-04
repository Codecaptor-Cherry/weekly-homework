import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q44_행성터널 {

	static class Planet {
		int idx, x, y, z;
		
		public Planet(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Edge {
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int N, parents[];
	static Planet[] planets;
	static ArrayList<Edge> edges;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			planets[i] = new Planet(i, Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()));
		}

		edges = new ArrayList<>();
		// x좌표 기준으로 정렬
		Arrays.sort(planets, (p1, p2) -> p1.x - p2.x);
		for (int i = 1; i < N; i++) edges.add(new Edge(planets[i - 1].idx, planets[i].idx, planets[i].x - planets[i - 1].x));
		
		// y좌표 기준으로 정렬
		Arrays.sort(planets, (p1, p2) -> p1.y - p2.y);
		for (int i = 1; i < N; i++) edges.add(new Edge(planets[i - 1].idx, planets[i].idx, planets[i].y - planets[i - 1].y));
		
		// z좌표 기준으로 정렬
		Arrays.sort(planets, (p1, p2) -> p1.z - p2.z);
		for (int i = 1; i < N; i++) edges.add(new Edge(planets[i - 1].idx, planets[i].idx, planets[i].z - planets[i - 1].z));
		
		Collections.sort(edges, (e1, e2) -> e1.cost - e2.cost);
		
		// 크루스칼
		int cnt = 0;
		long cost = 0;
		make();
		for (int i = 0; i < edges.size(); i++) {
			if (union(edges.get(i).from, edges.get(i).to)) {
				if (++cnt == N) break ;
				cost += edges.get(i).cost;
			}
		}
		
		System.out.println(cost);
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
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
}
