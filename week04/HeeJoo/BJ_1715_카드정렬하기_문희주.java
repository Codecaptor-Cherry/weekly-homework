package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * 정렬된 두 묶음의 숫자 카드 A, B
 * 각 묶음의 카드의 수를 A, B라 하면 보통 두 묶음을 만드는데 필요한 비교 횟수는 A+B
 * N개의 숫자 카드 묶음의 각각의 크기가 주어질 때, 최소한 몇 번의 비교가 필요한지 구하는 프로그램
 * 
 * 그리디 알고리즘
 * 가장 작은 카드 묶음 2개를 순차적으로 더해주기
 */

public class BJ_1715_카드정렬하기_문희주 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 카드 묶음의 개수 N
		
		// 각각의 카드 묶음의 정보 입력
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int result = 0; // 총 수행 결과
		while(pq.size() != 1) { // 카드 묶음이 1개가 된 경우 = 모두 합쳐짐
			int now = pq.poll() + pq.poll(); // 가장 작은 두 카드 묶음의 비교 횟수
			result += now; // 지금까지의 비교 횟수
			pq.add(now); // 합쳐진 카드 묶음
		}
		
		System.out.println(result);
	}
}
