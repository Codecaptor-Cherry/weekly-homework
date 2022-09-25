package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 두 개의 문자열 A, B
 * 문자열 A를 편집하여 문자열 B로 만들고자 함
 * 다음의 세 연산 중 한 번에 하나씩 선택하여 이용 가능
 * 
 * 연산1. 삽입 : 특정한 위치에 하나의 문자 삽입
 * 연산2. 삭제 : 특정한 위치에 있는 하나의 문자 삭제
 * 연산3. 교체 : 특정한 위치에 있는 하나의 문자를 다른 문자로 교체
 * 
 * 편집 거리 : 문자열 A를 편집하여 문자열 B로 만들기 위해 사용한 연산의 수
 * 문자열 A를 B로 만드는 최소 편집 거리를 구하는 프로그램
 * ex) sunday와 saturday의 최소 편집 거리는 3
 */

public class DP_36_편집거리_문희주 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		
		int a = A.length();
		int b= B.length();
		
		// dp 테이블 생성
		int[][] dp = new int[a + 1][b + 1];
		for(int i = 1; i <= b; i++) {
			dp[0][i] = i;
		}
		for(int i = 1; i <= a; i++) {
			dp[i][0] = i;
		}
		
		for(int i = 1; i <= a; i++) {
			for(int j = 1; j <= b; j++) {
				// 문자가 같은 경우 ~ 왼쪽 위
				if(A.charAt(i-1) == B.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];

				} else { // 문자가 다른 경우 ~ 왼쪽, 왼쪽 위, 위쪽 
					dp[i][j] = 1 + Math.min(dp[i][j-1],  Math.min(dp[i-1][j], dp[i-1][j-1]));
				}
			}
		}
		
		print(dp, a, b);
		
		System.out.println(dp[a][b]);
	}
	
	public static void print(int[][] dp, int a, int b) {
		for(int i = 0; i <= a; i++) {
			for(int j = 0; j <= b; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
