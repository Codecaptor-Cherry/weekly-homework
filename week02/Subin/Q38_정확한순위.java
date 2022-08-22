import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q38_정확한순위 {

	static int N, M;
	static int[][] dp;
	
	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N][N];
		
		// 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) dp[i][j] = INF;
			}
		}
		
		// a, b의 관계 표시
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			dp[a][b] = 1;
		}
		
		// 플로이드 워셜
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) { // i번째 학생 확인
			int cnt = 0; // 앞뒤 관계를 알 수 있는 학생 수
			for (int j = 0; j < N; j++) { // j번째 학생에 대해
				if (dp[i][j] != INF || dp[j][i] != INF) { // 앞뒤 관계를 알 수 있으면 
					cnt++; // cnt 증가
				}
			}
			if (cnt == N) ans++; // 앞뒤 관계를 알 수 있는 학생이 N명이라면 ans 증가
		}
		
		System.out.println(ans);
	}
	
}
 