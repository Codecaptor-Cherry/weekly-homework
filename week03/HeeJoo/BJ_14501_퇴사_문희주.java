package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 남은 N일 동안 최대한 많은 상담
 * 하루에 하나씩 서로 다른 사람의 상담
 * 각각의 상담 완료 기간 T_i, 상담 완료 금액 P_i
 * 상담을 하는데 필요한 기간(T_i)은 1일보다 클 수 있기때문에, 모든 상담을 할 수 없다.
 * ex) 1일 상담의 T_i가 3일 때, 1일 상담을 진행하면 2일과 3일은 상담 불가
 * ex) 퇴사일이 8일이고 6일 상담의 T_i가 4일 때, 8일과 9일 상담 진행이 불가하므로 6일 상담은 불가
 * 상담을 적절히 선택하여, 최대 수익을 구하는 프로그램
 */

public class BJ_14501_퇴사_문희주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 퇴사까지 남은 기간 N
		
		// 상담 정보 입력
		int[] T = new int[N+1]; // 상담 기간 T
		int[] P = new int[N+1]; // 상담 비용 P
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp 테이블 만들기
		// index = i + T[i] : i일에 상담 진행 + T[i] = 다음 상담이 가능한 날 
		// dp[j] = max(dp[j], dp[i] + P[i]); : 현재 j일에 얻을 수 있는 최대 이익
		int[] dp = new int[N+2]; // dp[N+1] : N일까지 상담을 마친 후 결과
		for(int i = 1; i <= N; i++) {
			if(i + T[i] <= N+1) {
				int index = i + T[i];
				for(int j = index; j <= N+1; j++) { // index 이후도 모두 갱신
					dp[j] = Math.max(dp[j], dp[i] + P[i]);
				}
			}
			System.out.println(i + "일 상담 결과 : " + Arrays.toString(dp));
		}
		System.out.println();
		System.out.println("최종 완성 : " + Arrays.toString(dp));
		System.out.println(dp[N+1]);
	}

}
