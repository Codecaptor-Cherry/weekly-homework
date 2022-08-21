import java.util.Arrays;
import java.util.Scanner;

public class Q08_문자열재정렬 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] input = sc.next().toCharArray();

		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			if ('0' <= input[i] && input[i] <= '9') { // 숫자는 합 구하기
				sum += input[i] - '0';
			} else { // 알파벳은 카운팅
				cnt++;
			}
		}
		
		char[] alphabet = new char[cnt]; // 알파벳 배열 생성해서
		int idx = 0;
		for (int i = 0; i < input.length; i++) {
			if ('A' <= input[i] && input[i] <= 'Z') { // input의 알파벳 넣기
				alphabet[idx++] = input[i];
			}
		}
		Arrays.sort(alphabet); // 정렬

		System.out.printf("%s%d\n", String.valueOf(alphabet), sum); // 출력
		
		sc.close();
	}
	
}
