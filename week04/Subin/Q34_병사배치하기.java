import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q34_병사배치하기 {

	static int N, input[], dp[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		
		// 가장 긴 감소하는 부분 수열을 구한다.
		for (int i = 0; i < N; i++) {
			// i 이전 지점 중 i지점의 값보다 큰 지점 중 최대값을 취한다.
			for (int j = 0; j <= i; j++) {
				if (input[j] > input[i] && dp[j] >= dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		// 최대 병사의 수를 구한다.
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (dp[i] > max) {
				max = dp[i];
			}
		}
		
		// 열외해야 하는 병사의 수를 출력한다. 
		System.out.println(N - max);
	}
}
