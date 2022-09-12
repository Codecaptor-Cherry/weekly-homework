package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * A, B 두 사람이 볼링 중 ,,, "서로 무게가 다른 볼링공 선택" ,,,
 * 총 N개의 볼링공 ,,, 각 볼링공마다 무게가 적혀 있고 ,,, 순서대로 1 ~ N번
 * 같은 무게의 공이 여러 개 있을 수 있지만 ,, 서로 다른 공으로 간주
 * 볼링공의 무게는 1 ~ M까지의 자연수 형태로 존재
 * ex) N =5, M = 3 ~ 볼링공 : 1, 3, 2, 3, 2 ~ 총 8가지 경우의 수 존재 ...
 * N개의 공의 무게가 각각 주어질 때, 두 사람이 서로 무게가 다른 볼링공을 고르는 경우의 수를 구하는 프로그램
 */

public class 그리디_05_볼링공고르기_문희주 {
	// answer : 8
	static String src1 = "5 3\r\n" +
						"1 3 2 3 2";
	// answer : 25
	static String src2 = "8 5\r\n" +
						"1 5 4 3 2 4 5 2";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(src1));
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 공의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 공의 최대 무게 M
		
		// 공의 무게 정보 입력
		int[] balls = new int[M+1]; // 무게 배열
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int idx = Integer.parseInt(st.nextToken());
			balls[idx]++;
		}

		int result = 0;
		for(int i = 1; i < M+1; i++) {
			N -= balls[i]; // 무게가 i인 볼링공의 개수(A가 선택할 수 있는 개수) 제외
            result += balls[i] * N; // B가 선택하는 경우의 수와 곱해주기
		}
		System.out.println(result);
	}
}
