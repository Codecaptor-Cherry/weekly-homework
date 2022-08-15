import java.util.Arrays;
import java.util.Scanner;

public class Q01_모험가길드 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] adventurer = new int[N];
		for (int i = 0; i < N; i++) {
			adventurer[i] = sc.nextInt(); // 모험가의 공포도를 입력받는다.
		}
		Arrays.sort(adventurer); // 정렬한다.

		int idx = N - 1, cnt = 0;
		while (idx >= 0) { // 모험가의 공포도가 높은 순서대로 팀을 짠다.
			idx -= adventurer[idx];
			if (idx >= -1) cnt++; // idx < -1이면 해당 팀에 인원이 부족한 것이다.
		}
		
		System.out.println(cnt);
		
		sc.close();
	}
	
}
