import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q07_럭키스트레이트 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		if (isLucky(input)) {
			System.out.println("LUCKY");
		} else {
			System.out.println("READY");
		}
	}
	
	public static boolean isLucky(String str) {
		int mid = str.length() / 2; // 문자열의 가운데
		int sum = 0; // 각 자릿수의 합을 구할 변수

		// 왼쪽 부분의 합을 구한다.
		for (int i = 0; i < mid; i++) {
			sum += str.charAt(i) - '0';
		}
		// 왼쪽 부분의 합에서 오른쪽 부분을 뺀다.
		for (int i = mid; i < str.length(); i++) {
			sum -= str.charAt(i) - '0';
		}
		// sum이  0이면 왼쪽 부분과 오른쪽 부분의 합이 동일한 것이다. 
		return (sum == 0);
	}
	
}
