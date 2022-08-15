import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q41_여행계획 {
	
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i; // 부모 노드를 자신으로 초기화
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				if (st.nextToken().equals("1")) {
					union(i, j); // 도시가 연결되어 있다면 union
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int p = find(Integer.parseInt(st.nextToken()));
		for (int i = 1; i < M; i++) {
			if (p != find(Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return ;
			}
		}
		System.out.println("YES");
	}
	
	public static void union(int x, int y) {
		x = find(x); // x의 부모 찾기
		y = find(y); // y의 부모 찾기
		
		if (x == y) return ; // 부모가 같다면 리턴
		
		parent[y] = x; // 부모 바꾸기
	}
	
	public static int find(int x) {
		if (x == parent[x]) return (x); // 자신이 부모라면 자신 리턴
	
		return (parent[x] = find(parent[x])); // 부모를 찾아서 대입하고 리턴
	}
}
