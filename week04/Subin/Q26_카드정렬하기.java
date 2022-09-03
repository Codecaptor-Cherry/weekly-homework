import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q26_카드정렬하기 {

	static int N, input[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소힙
		
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int ans = 0;
		while (pq.size() > 1) {
			// pq에서 가장 작은 두개의 카드를 뽑는다.
			int card1 = pq.poll();
			int card2 = pq.poll();
			
			// 두 카드를 섞는다. 
			ans += (card1 + card2);
			
			// 섞인 카드를 pq에 넣는다. 
			pq.add(card1 + card2);
		}
		
		System.out.println(ans);
	}
}
