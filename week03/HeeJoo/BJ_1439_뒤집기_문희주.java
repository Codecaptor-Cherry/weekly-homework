package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1439_뒤집기_문희주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine(); // 0과 1로만 이루어진 문자열 S
		int[] check = new int[2]; // 연속된 0과 1 토큰 개수
		
		int flag = s.charAt(0) - '0'; // 시작 글자
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i)-'0' == flag) continue; // i번째 글자가 flag와 같다면 pass
			// i번째 글자가 flag와 다르다면
			else {
				check[flag] += 1; // flag 토큰개수 +1
				flag = (flag + 1) % 2; // flag 변경
			}
		}
		check[flag] += 1; // 문자열을 다 읽은 후 마지막 토큰은 따로 +1
		
		if(s.length() == 1) System.out.println(1); // 예외 : 문자열 길이 1이면 무조건 뒤집기 1회
		else {
			if(check[0] < check[1]) System.out.println(check[0]);
			else System.out.println(check[1]);
		}
	}

}
