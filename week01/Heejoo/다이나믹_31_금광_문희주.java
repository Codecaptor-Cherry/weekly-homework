package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 다이나믹_31_금광_문희주 {
	static int[][] dir;
	static int n, m, dx, dy;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트 케이스 개수 T
		int t = getInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			// 금광의 크기 NxM
			st = new StringTokenizer(br.readLine());
			n = getInt(st.nextToken());
			m = getInt(st.nextToken());
			
			int[][] arr = new int[n][m];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					arr[i][j] = getInt(st.nextToken());
				}
			}
			
			// 다음 열 위, 오른, 아래
			dir = new int[][] {{-1, 1}, {0, 1}, {1, 1}};
			
			dp = new int[n][m];
			// dp테이블
			for(int i = 0; i < n; i++) {
				dp[i] = Arrays.copyOf(arr[i], arr[i].length);
			}
			
			for(int y = 0; y < m; y++) {
				for(int x = 0; x < n; x++) {
					// 1시 방향 이동
					if(getBound(x, y, 0)){
						dp[dx][dy] = Math.max(dp[dx][dy], arr[dx][dy] + dp[x][y]);
					}
					
					// 3시 방향 이동
					if(getBound(x, y, 1)) {
						dp[dx][dy] = Math.max(dp[dx][dy], arr[dx][dy] + dp[x][y]);
					}
					// 5시 방향 이동
					if(getBound(x, y, 2)) {
						dp[dx][dy] = Math.max(dp[dx][dy], arr[dx][dy] + dp[x][y]);
					}
				}
			}
			
			int max = 0;
			for(int index = 0; index < n; index++) {
				if(max < dp[index][m-1]) {
					max = dp[index][m-1];
				}
			}
			sb.append(max + "\n");
		}
		System.out.println(sb);
	}
	
	public static boolean getBound(int x, int y, int index) {
		if((x + dir[index][0] >= 0 && x + dir[index][0] < n) && (y + dir[index][1] >= 0 && y + dir[index][1] < m)) {
			dx = x + dir[index][0];
			dy = y + dir[index][1];
			return true;
		}
		return false;
	}
	public static int getInt(String s) {
		return Integer.parseInt(s);
	}
}
