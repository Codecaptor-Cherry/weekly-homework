import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_18352_특정거리의도시찾기_문희주 {
	static int n, m, k, x; // BFS
	static int[] distance; // BFS
	static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(); // BFS
	
	public static void main(String[] args) throws IOException {
		final int MAX_DISTANCE = 1000000; // N의 최대 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 도시의 개수 N, 도로의 개수 M, 거리정보 K, 출발 도시 번호 X
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = getInt(st.nextToken());
		m = getInt(st.nextToken());
		k = getInt(st.nextToken());
		x = getInt(st.nextToken());
		
		distance = new int[n+1];
		
		// 내부 리스트 초기화
		// 인덱스 0은 사용 x
		for(int i = 0; i < n + 1; i++) { 
			list.add(new ArrayList<>());
			distance[i] = MAX_DISTANCE;
		}
		distance[x] = 0; // x -> x = 0

		// M개의 줄에 걸쳐 A => B 도로 정보 입력
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = getInt(st.nextToken());
			int b = getInt(st.nextToken());
			
			list.get(a).add(b);
		}
		
		BFS(x, 1);
		
		StringBuilder sb = new StringBuilder();
		// 최단 거리가 k인 도시 입력
		for(int idx = 1; idx < n + 1; idx++) {
			if(distance[idx] == k) sb.append(idx + "\n");
		}
		
		// 해당하는 도시가 없다면 -1 출력
		if(sb.length() == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sb);
		}

	}
	
	
	public static void BFS(int start, int d) {
		if(d > k) return; // k를 넘어가면 탐색할 필요 x
		// start -> index
		for(Integer index : list.get(start)){
			// 최단거리 갱신
			if(distance[index] > d) {
				distance[index] = d;
				BFS(index, d+1);
			}
		}
	}
	
	public static int getInt(String s) {
		return Integer.parseInt(s);
	}

}
