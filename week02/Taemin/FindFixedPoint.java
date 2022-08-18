package com.ssafy.cfj.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 고정점 찾기 
 * @author devTaemin
 *
 */
public class FindFixedPoint {
	
	static int N;
	static int[] Array;
	
	/** 동빈나 답안 */
	public static int binarySearch2(int[] arr, int start, int end) {
		if (start > end) return -1;
		int mid = (start + end) / 2;
		
		if (arr[mid] == mid) return mid;
		else if (arr[mid] > mid) return binarySearch2(arr, start, mid - 1);
		else return binarySearch2(arr, mid + 1, end);
	}
	
	/** 내 답안 */
	public static int binarySearch() {
		int front = 0;
		int rear = N - 1;
		
		while (front <= rear) {
			int mid = (front + rear) / 2;
			
			if (Array[mid] == mid) {
				return mid;
			} else if (Array[mid] > mid) {
				rear = mid - 1;
			} else {
				front = mid + 1;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Array = new int[N];
		for (int i = 0; i < N; i++) {
			Array[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int result = binarySearch();
		System.out.println(result);
	}
}
