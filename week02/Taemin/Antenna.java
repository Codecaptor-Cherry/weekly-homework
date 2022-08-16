package com.ssafy.cfj.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * 안테나
 * @author devTaemin
 *
 */
public class Antenna {
	
	static int N;
	static int[] Array;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Array = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Array[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(Array);
		
		System.out.println(Array[(Array.length - 1) / 2]);
	}

}
