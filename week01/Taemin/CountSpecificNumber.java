package com.ssafy.cfj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정렬된 배열에서 특정 수의 개수 구하기 
 * @author devTaemin
 *
 */
public class CountSpecificNumber {
	
	static int N, X;
	static int[] Array;
	
	public static int binarySearch() {
		int front = 1; 
		int rear = N;
		
		while (front < rear) {
			int mid = (front + rear) / 2;
			
			if (X < Array[mid]) {
				rear = mid - 1;
			} else if (X > Array[mid]){
				front = mid + 1;
			} else {
				return mid;
			}
		}
		
		return -1;
	}
	
	public static int lineSearch(int find) {
		int count = 1;
		
		int index = find - 1;
		while (true) {
			if (index < 1) break;
			if (Array[index] != X) break;
			count++;
			index--;
		}
		
		index = find + 1;
		while (true) {
			if (index > N) break;
			if (Array[index] != X) break;
			count++;
			index++;
		}
		
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		Array = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			Array[i] = Integer.parseInt(st.nextToken());
		}
		
		int find = binarySearch();
		
		if (find != -1) {
			find = lineSearch(find);
		}
		
		System.out.println(find);
	}

}
