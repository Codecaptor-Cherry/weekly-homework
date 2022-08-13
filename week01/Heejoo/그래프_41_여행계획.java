package 그래프;

/*
 * BJ 1976 여행가자
 * 입력 형식만 바꾸면 됨
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그래프_41_여행계획 {
	static int[] parent;
	public static int findParent(int a) {
		if (parent[a] != a) {
			return findParent(parent[a]);
		}
		return a;
	}
	
	public static void unionParent(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if (pa < pb) {
			parent[pb] = pa;
		}
		else {
			parent[pa] = pb;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		st = new StringTokenizer(br.readLine());
//		int n = Integer.parseInt(st.nextToken()); // 여행지의 수 N
//		int m = Integer.parseInt(st.nextToken()); // 여행을 계획한 도시의 수 M
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		// 부모를 자기자신으로 초기화
		for(int i = 1; i < n+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				if(input == 1) {
					unionParent(i, j);
				}
			}
		}
		
		// 여행 계획
		st = new StringTokenizer(br.readLine());
		int[] dest = new int[m];
		for(int i = 0; i < m; i++) {
			dest[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true;
		for(int i = 0; i < m - 1; i++) {
			int start = dest[i];
			int end = dest[i+1];
			
			if(findParent(start) != findParent(end)) {
				flag = false;
				break;
			}
		}
		
		if(flag) System.out.println("YES");
		else System.out.println("NO");
	}

}
