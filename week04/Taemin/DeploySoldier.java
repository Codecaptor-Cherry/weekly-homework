package com.ssafy.cfj.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DeploySoldier {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] array = new int[n];
		int[] count = new int[n];
		Arrays.fill(count, 1);
		
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < k; i++) {
				if (array[i] > array[k]) {
					count[k] = Math.max(count[k], count[i] + 1);
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, count[i]);
		}
		
		
		System.out.println(n - max);		
	}
}
