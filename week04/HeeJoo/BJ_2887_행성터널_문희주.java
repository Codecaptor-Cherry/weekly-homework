package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * N개의 행성
 * 행성은 3차원 좌표 위의 한 점으로 생각하면 됨
 * 두 행성 A와 B를 터널로 연결할 때 비용 = min(|Xa-Xb|, |Ya-Yb|, |Za-Zb|)
 * 터널을 총 N-1개 건설해서 모든 행성이 서로 연결되게 하려고 함
 * 이때, 모든 행성을 터널로 연결하는데 필요한 최소 비용을 구하는 프로그램
 */

class Position implements Comparable<Position>{
	int idx, c;

	public Position(int idx, int c) {
		this.idx = idx;
		this.c = c;
	}

	@Override
	public int compareTo(Position o) {
		return Integer.compare(this.c, o.c);
	}
}

class Edge{
	int a, b, dist;

	public Edge(int a, int b, int dist) {
		this.a = a;
		this.b = b;
		this.dist = dist;
	}
}
public class BJ_2887_행성터널_문희주 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 행성의 개수 N
		
	    ArrayList<Position> x = new ArrayList<Position>();
        ArrayList<Position> y = new ArrayList<Position>();
        ArrayList<Position> z = new ArrayList<Position>();
		// 행성 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			x.add(new Position(i, a));
			y.add(new Position(i, b));
			z.add(new Position(i, c));
		}
		
		Collections.sort(x);
		Collections.sort(y);
		Collections.sort(z);
		
		parent = new int[N];
		for(int i = 0; i < N; i++) parent[i] = i;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
		
		// 각각 x, y, z를 기준으로 정렬한 뒤 인접한 노드의 간선만 pq에 삽입
		for(int i = 0; i < N-1; i++) {
			pq.offer(new Edge(x.get(i).idx, x.get(i+1).idx, Math.abs(x.get(i).c - x.get(i+1).c)));
			pq.offer(new Edge(y.get(i).idx, y.get(i+1).idx, Math.abs(y.get(i).c - y.get(i+1).c)));
			pq.offer(new Edge(z.get(i).idx, z.get(i+1).idx, Math.abs(z.get(i).c - z.get(i+1).c)));
		}

		int count = 0;
		int cost = 0;

		while(count != N-1) {
			Edge e = pq.poll();
			if(find(e.a)!= find(e.b)) {
				union(e.a, e.b);
				cost += e.dist;
				count++;
			}
		}
		
		System.out.println(cost);
		
	}
	public static int find(int a) {
		if(a != parent[a]) return parent[a] = find(parent[a]);
		
		return a;
	}
	
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}

}
