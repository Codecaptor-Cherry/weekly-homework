import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q40_숨바꼭질 {

	static int N, M, d[];
	static Node[] adjList;
	
	static final int INF = (int)1e9;
	
	static class Node {
		int to;
		Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		d = new int[N];
		Arrays.fill(d, INF);
		dijkstra(0);

		int ans = 0;
		int dis = 0;
		for (int i = 0; i < N; i++) {
			if (d[i] > dis) {
				ans = i;
				dis = d[i];
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (d[i] == dis) ++cnt;
		}
		
		System.out.println((ans + 1) + " " + dis + " " + cnt);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2)-> i1[1] - i2[1]);
		
		pq.add(new int[] {start, 0});
		d[start] = 0;
	
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (d[cur[0]] < cur[1]) continue ;
			
			for (Node next = adjList[cur[0]]; next != null; next = next.next) {
				if (d[next.to] > d[cur[0]] + 1) {
					d[next.to] = d[cur[0]] + 1;
					pq.add(new int[] {next.to, d[next.to]});
				}
			}
		}
	}
}
