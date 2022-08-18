package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 일직선 상의 마을에 여러 채의 집
 * 특정 위치의 집에 특별히 한 개의 안테나 설치
 * 안테나로부터 모든 집까지의 거리의 총 합이 최소가 되도록 설치
 * 안테나는 집이 위치한 곳에만 설치 가능
 * 동일한 위치에 여러 개의 집이 존재할 수 있음
 * 집들의 위치 값이 주어질 때, 안테나를 설치할 위치를 구하는 프로그램
 */

public class BJ_18310_정렬_문희주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 집의 수 N
		
		// N채의 집의 위치 정보
		int[] homes = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		for(int i = 0; i < n; i++) {
			homes[i] = Integer.parseInt(st.nextToken());
			sum += homes[i];
		}
		// 1 5 8 9 10 11

		Arrays.sort(homes); // 번호 오름차순 정렬
		
		// 중간값 이용
		// 홀수인 경우 중간 인덱스 사용 
		if(homes.length % 2 == 1) {
			System.out.println(homes[homes.length/2]);
		}
		// 짝수인 경우 중간값이 두 개 => 더 작은 값 출력
		else {
			System.out.println(homes[homes.length/2 - 1]);
		}
	}
}
