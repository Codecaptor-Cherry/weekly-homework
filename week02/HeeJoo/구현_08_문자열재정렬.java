package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 구현_08_문자열재정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine(); // 알파벳 대문자와 숫자(0~9)로만 이루어진 문자열
		
		/* 모든 알파벳을 오름차순 정렬 + 그 뒤에 모든 숫자를 더한 값 출력
		 * 1. 한 글자씩 읽으며 알파벳과 숫자 구별
		 * 2. 알파벳은 리스트에 넣은 후 오름차순 출력
		 * 3. 숫자는 더하기 연산
		 */
		
		List<Character> list = new ArrayList<>();
		int sum = 0;
		
		for(int idx = 0; idx < s.length(); idx++) {
			// 해당 글자가 숫자인 경우
			if(Character.isDigit(s.charAt(idx))) {
				sum += s.charAt(idx) - '0';
			}
			// 해당 글자가 알파벳인 경우
			else {
				list.add(s.charAt(idx));
			}
		}
		
		Collections.sort(list);
		
		for(char c : list) {
			sb.append(c);
		}
		sb.append(sum);
		
		System.out.println(sb);
		
	}
	
}
