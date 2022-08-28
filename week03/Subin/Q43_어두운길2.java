import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q43_어두운길2 {

	static int N, M;
	static Node[] adjList;
	
	static final int INF = (int)1e9;
	
	static class Node {
		int to;
		int weight;
		Node next;
		
		public Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N];
		long ans = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// *** 무향처리 ***
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
			
			ans += weight; // 모든 가로등의 비용 더하기
		}

		// 가중치를 기준으로 최소 힙
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2)-> n1.weight - n2.weight);
		pq.add(new Node(0, 0, null)); // 임의의 시작점
		
		int cnt = 0;
		boolean[] visited = new boolean[N];
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 방문한 정점이라면 continue
			if (visited[cur.to]) continue;
			
			visited[cur.to] = true; // 방문 체크
			ans -= cur.weight; // 모든 가로등의 비용에서 가로등을 연결할 수 있는 최소비용 빼주기
			
			if (++cnt == N) break; // 모든 정점을 방문하면 break;
			
			// 인접 정점 방문하기
			for (Node temp = adjList[cur.to]; temp != null; temp = temp.next) {
				if (!visited[temp.to]) { // 방문하지 않은 정점이라면
					pq.offer(temp); // 큐에 넣기
				}
			}
		}
		
		System.out.println(ans); // 남은 비용이 절약할 수 있는 최대비용이다.
	}
	
}
