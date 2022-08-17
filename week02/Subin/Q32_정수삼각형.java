import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q32_정수삼각형 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N + 1][N + 2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N + 1][N + 2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				// 대각선 왼쪽 위와 대각선 오른쪽 위 숫자 중 큰 수 선택해서
				// 현재 숫자 더하기
				dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + input[i][j];
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, dp[N][i]); // 최대값 구하기
		}
		System.out.println(ans);
	}
	
}


//0 0
//0 7 0
//0 3 8 0
//0 8 1 0 0
//0 2 7 4 4 0
//0 4 5 2 6 5 0
