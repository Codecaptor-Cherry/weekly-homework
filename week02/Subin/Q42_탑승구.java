import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q42_탑승구 {

	static int G, P, ans;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		
		int ans = 0;
		for (int i = 0; i < P; i++) {
			// 현재 비행기의 탑승구 루트 확인
			int dock = find(Integer.parseInt(br.readLine()));
			if (dock == 0) break; // 루트가 0이라면 종료
			union(dock, dock - 1); // 왼쪽 집합과 합치기
			ans++;
		}
		System.out.println(ans);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return;
		
		if (x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	public static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
}
