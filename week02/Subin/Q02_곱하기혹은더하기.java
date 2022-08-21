import java.util.Scanner;

public class Q02_곱하기혹은더하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] input = sc.next().toCharArray();
		int res = input[0] - '0';
		
		for (int i = 1; i < input.length; i++) {
			if (res < 2 || input[i] - '0' < 2) res += input[i] - '0'; // 0~1은 덧셈
			else res *= input[i] - '0'; // 2~9는 곱셈
		}
		
		System.out.println(res);
				
		sc.close();
	}
	
}
