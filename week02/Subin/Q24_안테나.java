import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q24_안테나 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] House = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			House[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(House);
		
		int min = Integer.MAX_VALUE;
		int ans = -1;
		// 중간 값이 최소가 된다
		// 홀수일 경우 중간 값 하나, 짝수일 경우 중간 값 두개 비교해서 작은 것 택 
		for (int i = (N - 1) / 2; i <= N / 2; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				sum += (House[i] - House[j]) * (i < j ? 1 : -1);
			}
			if (min > sum) {
				min = sum;
				ans = House[i];
			}
		}
		System.out.println(ans);
	}
	
}
