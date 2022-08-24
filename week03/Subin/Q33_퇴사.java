import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q33_퇴사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		for (int i = N - 1; i >= 0; i--) { // 마지막 날짜부터 확인
			if (i + T[i] <= N) {
				dp[i] = Math.max(P[i] + dp[i + T[i]], dp[i + 1]); // 오늘 상담o, 오늘 상담x 중 큰 것 선택
			} else { // 퇴사 날까지 상담을 끝내지 못한다면 선택하지 못함
				dp[i] = dp[i + 1]; // 오늘 상담x 선택
			}
			
		}
		
		System.out.println(dp[0]);
	}
	
}