import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q29_공유기설치 {

	static int N, C;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		System.out.println(search());
	}

	public static int search() {
		int start = 1;
		int end = arr[N - 1] - arr[0];
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (check(mid)) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return (end);
	}
	
	public static boolean check(int dis) {
		// 첫번째 공유기는 첫번째 집에 설치
		int prev = 0;
		int cnt = 1;
		
		for (int i = 1; i < N; i++) {
			if (arr[i] - arr[prev] >= dis) {
				prev = arr[i];
				++cnt;
			}
		}
		
		return cnt >= C;
	}
	
}

