package com.ssafy.cfj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 모험가 길드
 * @author devTaemin
 *
 */
public class AdventurerGuild {
	/** Number of adventurer */
	static int N;
	
	/** Number of guild */
	static int NumOfGuild;
	
	/** Number of required team mate */
	static Integer[] Array;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/** Initialize */
		N = Integer.parseInt(br.readLine());
		NumOfGuild = 0;
		Array = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			Array[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(Array);
		
		/** Initialize the count number */
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			/** ith advanturer is chosen */
			count++;
			
			/** Compare ith advanturer's required numbers with accumulated number of group*/
			if (count >= Array[i]) {
				NumOfGuild++;
				count = 0;
			}
		}
		
		System.out.println(NumOfGuild);
		
		
		
		// 역순으로 정렬해서 그룹을 만드는 방법 (비효율) 
//		/** Sort in a descending order */
//		Arrays.sort(Array, Collections.reverseOrder());
//		
//		/** Change the start index from 0 to N - 1 */
//		for (int start = 0; start < N; start++) {
//			
//			/** Accumulate the local number of guild */
//			int localNumOfGuild = 0;
//			for (int idx = start; idx < N; idx++) {
//				int required = Array[idx];
//				idx += required - 1;
//				if (idx < N) {
//					localNumOfGuild++;
//				}
//			}
//			
//			/** Assign the maximum number of guild */
//			NumOfGuild = Math.max(NumOfGuild, localNumOfGuild);
//		}
//		
//		System.out.println(NumOfGuild);
	}

}
