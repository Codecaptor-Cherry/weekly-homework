import java.util.Scanner;
import java.util.StringTokenizer;

public class Q03_문자열뒤집기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		int ans = Integer.MAX_VALUE;
		
		// 1로 이루어진 연속된 그룹의 수 세기
		StringTokenizer st = new StringTokenizer(input, "0");
		ans = st.countTokens();
		
		// 0으로 이루어진 연속된 그룹의 수 세기
		st = new StringTokenizer(input, "1");
		ans = Math.min(ans, st.countTokens());
		
		// 둘 중 작은 것이 정답
		System.out.println(ans);
		
		sc.close();
	}
	
}
