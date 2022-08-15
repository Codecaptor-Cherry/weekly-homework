import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q37_플로이드 {

	static int[][] dp;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 노선이 여러개일 수 있으므로 최솟값만 갱신
			if (dp[a][b] == 0 || dp[a][b] > c) dp[a][b] = c;
		}
		
		floydWarshall();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(dp[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	// https://blog.naver.com/ndb796/221234427842
	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) { // 경유지 k
			for (int i = 1; i <= N; i++) { // 출발지 i
				for (int j = 1; j <= N; j++) { // 도착지 j
					if (i == j || dp[i][k] == 0 || dp[k][j] == 0) continue; // 출발점과 도착점이 같거나 길이 없으면 continue
					if (dp[i][j] == 0 || dp[i][j] > dp[i][k] + dp[k][j]) { // 경로의 최솟값을 갱신 (dp가 0이면 처음 경로를 찾은 것)
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}
	}
}
