import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q31_금광 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[][] input = new int[N + 1][M + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[N + 2][M + 1]; // 인덱스 경계 처리 안하기 위해 크게 설정
			for (int c = 1; c <= M; c++) { // 세로로 방문
				for (int r = 1; r <= N; r++) {
					dp[r][c] = Math.max(dp[r - 1][c - 1], Math.max(dp[r][c - 1], dp[r + 1][c - 1])) // 왼쪽 위, 왼쪽, 왼쪽 아래 채굴량 중 가장 큰 값에
								+ input[r][c]; // 현재 채굴량 더하기
				}
			}
			
			int ans = 0;
			for (int i = 1; i <= N; i++) ans = Math.max(ans, dp[i][M]); // 채굴량 최댓값 구하기
			System.out.println(ans);
		}
	}
	
}
