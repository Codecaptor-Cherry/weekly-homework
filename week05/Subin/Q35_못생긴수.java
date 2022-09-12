import java.util.Scanner;

public class Q35_못생긴수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] dp = new int[n];
		dp[0] = 1;
		
		int twoIdx = 0, threeIdx = 0, fiveIdx = 0;
		for (int i = 1; i < n; i++) {
			if (twoIdx * 2 <= dp[i - 1]) ++twoIdx;
			if (threeIdx * 3 <= dp[i - 1]) ++threeIdx;
			if (fiveIdx * 5 <= dp[i - 1]) ++fiveIdx;
			
			dp[i] = Math.min(twoIdx * 2, Math.min(threeIdx * 3, fiveIdx * 5));
		}
		
		System.out.println(dp[n - 1]);
		
		sc.close();
	}

}
