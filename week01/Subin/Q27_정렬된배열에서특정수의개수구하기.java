import java.util.Scanner;

public class Q27_정렬된배열에서특정수의개수구하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int target = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int cnt = upperBound(arr, target) - lowerBound(arr, target);
		if (cnt == 0) cnt = -1;
		
		System.out.println(cnt);
		sc.close();
	}
	
	/*
	 * 
	 * lower_bound 함수는 특정 배열과 찾고자 하는 key값이 있을 때 그 key값이 존재한다면 처음으로 등장하는 index를 반환한다. 
	 * 만약 존재하지 않는 값이라면 배열 맨 끝의 index를 반환한다.
	 * 
	 * upper_bound 함수는 특정 배열과 찾고자 하는 key값이 있을 때 그 key값이 존재한다면 마지막으로 등장하는 index의 다음 값을 반환한다. 
	 * 만약 존재하지 않는 값이라면 배열 맨 끝의 index를 반환한다.
	 * 
	 * */
	
	public static int lowerBound(int[] arr, int target) {
		int start = 0;
		int end = arr.length;
		
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return (end);
	}
	
	public static int upperBound(int[] arr, int target) {
		int start = 0;
		int end = arr.length;
		
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] <= target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return (end);
	}
}
