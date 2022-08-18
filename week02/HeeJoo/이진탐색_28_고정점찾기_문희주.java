package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/*
 * 고정점 : 수열의 원소 중에서 "그 값이 인덱스와 동일한 원소" ex) arr[i] = i;
 * N개의 서로 다른 원소를 가진 수열이 오름차순 정렬된 상태
 * 해당 수열의 고정점을 구하는 프로그램(최대 1개)
 * 고정점이 존재하지 않는다면 "-1" 출력
 */
public class 이진탐색_28_고정점찾기_문희주 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 수열의 크기 N
		
		// 수열 입력
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < n; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
	
		int start = 0;
		int end = n-1;
		int mid = 0;
		boolean flag = false; // 정답 여부
		
		// 시작점이 끝점보다 왼쪽에 있을 때까지만
		while(start <= end) {
			mid = (start + end) / 2; // 중간점
			
			// 중간점과 대소 비교하여 start 또는 end 갱신
			if(arr[mid] > mid) { // 배열 값이 더 큰 경우 배열 값이 줄도록 끝점을 갱신
				end = mid - 1;
			}
			else if(arr[mid] < mid){ // 인덱스 값이 더 큰 경우 배열 값을 증가시키기 위해 시작점 갱신
				start = mid + 1;
			}
			// 인덱스 값과 배열 값이 큰 경우 고정점 탐색 완료
			else {
				flag = true;
				break;
			}
		}
		
		// 정답 여부에 따른 출력
		if(flag) {
			System.out.println(mid);
		}
		else System.out.println("-1");
	}

}
