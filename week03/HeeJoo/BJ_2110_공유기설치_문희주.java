package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 수직선 위 N개의 집. 각 집의 좌표는 x_1, ..., x_N(동일 좌표 xxx)
 * 공유기 C개 설치하기
 * 한 집에는 하나의 공유기만 설치 가능하며, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 설차하기
 * C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램
 * 2 <= N <= 200,000 / 2 <= C <= N / 0 <= x_i <= 1,000,000,000 이기 때문에 for문 xxx
 * 이분 탐색, 매개 변수 탐색을 이용해야 시간 초과 xxx
 */

public class BJ_2110_공유기설치_문희주 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
		
		// 집의 좌표 N개 입력
		int[] homes = new int[N];
		for(int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		
		// 1 2 4 8 9
		Arrays.sort(homes); // 이분 탐색을 위해 정렬
		int low = 1; // 최소  거리가 가질 수 있는 최솟값
		int high = homes[N-1] - homes[0]; // 최소 거리가 가질 수 있는 최댓값
		
		// 최대로 가질 수 있는 최소 거리 : Upper Bound ~ 최솟값을 증가시키기(left)
		while(low <= high) {
			int mid = (low + high) / 2; // 중간값을 기준으로 이분탐색
			
			// 목표 개수보다 설치 가능한 개수가 적은 경우
			int count = installCount(homes, mid);
			if(count < C) {
				high = mid - 1; // 최댓값을 감소시켜 공유기 간의 거리를 줄이기
			}
			// 목표 개수보다 설치 가능한 개수가 많거나 같은 경우
			// 목표 개수에 도달하더라도 low값을 최대한 증가
			else if(count >= C) {
				low = mid+1; // 최솟값을 증가시켜 공유기 간의 거리를 늘리기
			}
		}
		
		System.out.println(low-1); // UpperBound : 초과 이전 값이 정답이므로 -1
	}
	
	// 최소 dist만큼 거리를 두고 공유기 설치하기 : return 공유기 설치 개수
	public static int installCount(int[] homes, int dist) {
		// 첫 번째 집 고정적 설치
		int count = 1;
		int last = homes[0];
		
		// 두 번째 집부터 직전에 공유기를 설치한 집과의 거리를 비교하여 설치 가능여부 확인
		for(int i = 1; i < homes.length; i++) {
			int locate = homes[i]; // 이번에 설치할 집의 좌표
			
			if(locate - last >= dist) { // 최소 거리보다 떨어져있으면 설치가능
				count++;
				last = locate; // 직전 설치 집의 좌표 갱신
			}
		}
		return count;
	}

}
