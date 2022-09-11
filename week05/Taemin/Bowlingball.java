package com.ssafy.cfj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bowlingball {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		/** 공의 개수 */
		int numOfBall = Integer.parseInt(st.nextToken());
		/** 최대 무게 */
		int maxWeight = Integer.parseInt(st.nextToken());
		
		/** 무게 별 볼링 공의 수를 저장 */
		st = new StringTokenizer(br.readLine());
		int[] weights = new int[maxWeight + 1];
		for (int i = 0; i < numOfBall; i++) {
			int weight = Integer.parseInt(st.nextToken());
			weights[weight] += 1;
		}
		
		/** 총 경우의 수 */
		int count = 0;
		
		/**
		 * 	오름차순으로 현재 공과 조합을 이룰 수 있는 공을 곱하여 경우의 수를 더해가기
		 * 	사용된 공의 수는 남은 전체 공에서 제거하기
		 * */
		for (int i = 1; i <= maxWeight; i++) {
			numOfBall -= weights[i];
			count += weights[i] * numOfBall;
		}
		
		System.out.println(count);
	}
}
