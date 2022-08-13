package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N개의 원소를 포함하고 있는 오름차순 수열
 * 해당 수열에서 x가 등장하는 횟수를 구하는 프로그램
 */
public class 이진탐색_정렬된배열에서특정수의개수구하기 {
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 원소의 개수 N, 타겟 x
		st = new StringTokenizer(br.readLine());
		n = getInt(st.nextToken());
		int x = getInt(st.nextToken());
		
		// N개의 원소
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = getInt(st.nextToken());
		}
		
		int start = getLowerBound(x);
		int end = getUpperBound(x);
		
		if(end-start == -1) System.out.println("-1");
		else System.out.println(end-start+1);
		
	}
	
	// (start, end, mid ~ arr[mid]) : (0, 6, 3 = 2) => (0, 3, 1 = 1) => (2, 3, 2 = 2)
	public static int getLowerBound(int x) {
		int start = 0;
		int end = n;
		
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(x <= arr[mid]) end = mid;
			else start = mid + 1;
		}
		return start;
	}
	
	// (start, end, mid ~ arr[mid]) : (0, 6, 3 = 2) => (4, 6, 5 = 2) => (6, 6, 6 = 3)
	public static int getUpperBound(int x) {
		int start = 0;
		int end = n;
		
		while(start < end) {
			int mid = (start + end)/2;
			
			if(x < arr[mid]) end = mid;
			else start = mid + 1;
		}
		return start - 1;
	}
	public static int getInt(String s) {
		return Integer.parseInt(s);
	}
}
