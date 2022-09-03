package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N명의 병사가 무작위로 나열
 * 전투력이 높은 병사가 앞쪽에 오도록 내림차순 배치
 * 특정한 위치에 있는 병사를 열외시키는 방법 이용
 * 병사에 대한 정보가 주어졌을 때, 남아있는 병사의 수가 최대가 되도록 하기 위해서 열외해야 하는 병사의 수를 출력하는 프로그램
 * 
 * LCS : 가장 긴 증가하는 부분 수열
 */

public class BJ_18353_병사배치하기_문희주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 병사의 수 N
		
		// 병사의 전투력 정보 입력
		// 0인덱스 사용 x
		int[] array = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N ; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] DP = new int[N+1]; // DP 테이블 생성. 0인덱스 사용 x
		Arrays.fill(DP, 1); // 자기자신.. 모두 1명으로 초기화
		// 뒤에서부터 오름차순으로 탐색
		for(int i = N-1; i >= 1; i--) {
			for(int j = N; j > i; j--) {
				// index부터 N번까지
				if(array[i] > array[j]) DP[i] = Math.max(DP[i], DP[j] + 1); // array[index]가 더 크면 DP테이블 최댓값 비교 및 갱신
			}
		}
		
		int sum = 0; // 병사 최대 인원
		for(int i = 1; i <= N; i++) {
			if(sum < DP[i]) sum = DP[i];
		}
		
		System.out.println(N - sum);
	}

}
