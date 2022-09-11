import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q05_볼링공고르기 {

	static int N, M, balls[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		balls = new int[M + 1];
		
		// 무게 별로 공의 수 저장하기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			++balls[Integer.parseInt(st.nextToken())];
		}
		
		// 현재 무게의 공으로 만들 수 있는 모든 조합의 수 구하기
		int ans = 0;
		for (int i = 1; i <= M; i++) {
			ans += balls[i] * (N - balls[i]);
		}
		// 공의 조합에는 순서가 없으므로 /2
		System.out.println(ans / 2);
	}
	
}
