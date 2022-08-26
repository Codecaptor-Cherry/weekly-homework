package com.ssafy.cfj.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * 공유기 설치
 */
public class RouterInstallation {
	/** Number of homes, Number of routers */
	static int N, C;
	
	/** Location of homes */
	static int[] Homes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Homes = new int[N];
		
		/** Get the location of each home */
		for (int i = 0; i < N; i++) {
			Homes[i] = Integer.parseInt(br.readLine().trim());
		}
		
		/** Sort in an ascending order to use binary search */
		Arrays.sort(Homes);
		
		/**
		 * As the target is distance, front and rear is the range of distance
		 * Initialize front with 0 and rear with the difference of distance between first home and the last home
		 * */
		int front = 0;
		int rear = Homes[N - 1] - Homes[0];
		int maxDistance = 0;
		
		while (front <= rear) {
			int mid = (front + rear) / 2;
			int count = 1;
			int threshold = Homes[0] + mid;
			for (int i = 1; i < N; i++) {
				if (Homes[i] >= threshold) {
					threshold = Homes[i] + mid;
					count++;
				}
			}
			
			/** If required condition is met, increment the front */
			if (count >= C) {
				maxDistance = Math.max(maxDistance, mid);
				front = mid + 1;
			} else {
				rear = mid - 1;
			}
		}
		
		System.out.println(maxDistance);
	}
}
