package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1932_정수삼각형_문희주 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 삼각형의 크기 N
		
		int[][] tri = new int[501][501];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int leng = st.countTokens();
			for(int j = 1; j <= leng; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= i; j++) {
				tri[i][j] += Math.max(tri[i-1][j-1], tri[i-1][j]);
			}
		}
		
		int result = 0;
		for(int max : tri[n]) {
			if(result < max) result = max;
		}
		System.out.println(result);
	}

}
