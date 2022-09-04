package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N개의 동전을 이용하여 만들 수 없는 양의 정수 금액 중 최솟값을 구하는 프로그램
 * ex) N = 5, (1, 1, 2, 3, 9) ~ 8원
 * ex) N = 3, (3, 5, 7) ~ 1원
 */
public class 그리디_04_만들수없는금액_문희주 {
	static String src = "5\r\n" +
						"3 2 1 1 9\r\n";
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new StringReader(src));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] coin = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(coin);
		int result = 1; // 만들 수 없는 양의 정수 금액 최솟값
		
		for(int i = 0; i < N; i++) {
			if(coin[i] > result) break; // 현재 값보다 더 큰 코인으로는 작은 값을 만들 수 없음
			else {
				result += coin[i];
			}
		}
		
		System.out.println(result);
	}

}
