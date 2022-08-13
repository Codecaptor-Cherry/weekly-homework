import java.util.Scanner;

public class 구현_럭키스트라이크 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// 점수 N
		String s = in.next();
		
		// 점수 N을 자릿수를 기준으로 반으로 나누기
		int standard = s.length() / 2;
		String s1 = s.substring(0, standard);
		String s2 = s.substring(standard, s.length());
		
		// 각 자릿수의 합이 동일한지 확인 ~ 결과에 따른 출력
		int i1 = 0; // 문자열 s1의 자릿수 합
		int i2 = 0; // 문자열 s2의 자릿수 합
		for(int idx = 0; idx < standard; idx++) {
			// char => int
			i1 += s1.charAt(idx) - '0';
			i2 += s2.charAt(idx) - '0';
		}
		
		// 결과가 같은 경우 "LUCKY" 출력
		if(i1 == i2) {
			System.out.println("LUCKY");
		}
		// 다른 경우 "READY" 출력
		else System.out.println("READY");
	}

}
