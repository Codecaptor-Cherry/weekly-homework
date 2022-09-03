import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q04_만들수없는금액 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] coins = new int[N];
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(coins);
		
		// 가능한 금액을 구한다.
		ArrayList<Integer> avail = new ArrayList<>();
		avail.add(0);
		for (int i = 0; i < N; i++) {
			int size = avail.size();
			for (int j = 0; j < size; j++) {
				int a = avail.get(j);
				// 작거나 같은 금액이라면 이미 가능한 금액 리스트에 저장되어 있으므로 무시한다.
				if (a + coins[i] <= avail.get(avail.size() - 1)) continue;
				// 마지막 금액보다 1만큼 크다면 리스트에 추가해준다. 
				else if (a + coins[i] == avail.get(avail.size() - 1) + 1) avail.add(a + coins[i]);
				else { // 그 외에는 저장된 가장 큰 금액보다 1만큼 큰 금액이 만들 수 없는 금액이다. 
					System.out.println(avail.get(avail.size() - 1) + 1);
					return ;
				}
			}
		}
		
		System.out.println(avail.get(avail.size() - 1) + 1);
	}
	
}
