import java.util.Scanner;

public class Q28_고정점찾기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		System.out.println(binarySearch(arr));
		
		sc.close();
	}
	
	
	public static int binarySearch(int[] arr) {
		int start = 0;
		int end = arr.length;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (mid == arr[mid]) {
				return mid;
			} else if (mid > arr[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return -1;
	}
}
