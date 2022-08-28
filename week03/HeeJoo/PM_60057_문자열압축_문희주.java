package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현하여 더 짧은 문자열 만들기
 * 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현
 * 문자열은 제일 앞부터 정해진 길이만큼 잘라야 하며, 1은 생략한다.
 * 알파벳 소문자로만 이루어진 문자열 S가 주어질 때, 1개 이상 단위로 문자열을 잘라 압축 표현한 문자열 중 가장 짧은 것의 길이를 구하는 프로그램
 * ex) abcabcabcabcdededededede
 * 4개 단위로 압축 -> abcabcabcabc3dede
 * 6개 단위로 압축 -> 2abcabc2dedede
 */
public class PM_60057_문자열압축_문희주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine(); // 압축할 문자열 S
		
		int ans = 1000; // S의 길이는 1 이상 1,000 이하
		int comp = 1; // 압축 단위
		
		while(comp <= S.length() / 2) { // 문자열의 절반을 넘어가면 압축 xxx
			String subS = S.substring(0, comp); // 단위 문자열
			int count = 0; // 단위 문자열의 개수
			int index = 0; // 압축 시작 위치
			String result = ""; // 압축 문자열
			while(index + comp <= S.length()) { // 다음 압축이 문자열 범위를 벗어나면 break
				if(S.substring(index, index + comp).equals(subS)) { // index를 기준으로 단위 문자열과 같으면 압축
					count++; // 압축 카운트 + 1
				}
				else { // 해당 단위 문자열로 압축이 더 이상 불가능한 경우
					if(count == 1) { // count == 1 : count를 적으면 오히려 문자열 길이가 증가하므로 생략
						result += subS; // 결과에 단위 문자열만 적기
						count = 0; // 카운트 초기화
					}
					else { // count가 2 이상인 경우 : count를 적어도 길이 변화가 없거나 더 짧아짐
						result += count + subS; // count와 단위 문자열을 함께 기록
						count = 0; // 카운트 초기화
					}
					
					// 새로운 단위 문자열 만들기
					if(index + comp <= S.length()) { // 단위 문자열이 범위를 벗어나지 않는다면
						subS = S.substring(index, index + comp); // 단위 문자열 갱신
						count = 1; // 마지막에 index += comp를 해주므로 해당 if문에서는 count를 1로 초기화 ~ 그래야 지금 만든 부분도 카운트가 됨
					}
				}
				index += comp; // comp를 기준으로 index 이동
				//System.out.println(result);
			}
			// 끝부분 처리
			// 압축을 하다 범위를 벗어나면 마지막 붙이기를 못함
			// 위와 동일한 방식으로 한 번만 해주기
			if(count == 1) {
				result += subS;
			}
			else {
				result += count + subS;
			}
			
			// 압축되지 않는 나머지가 붙이기
			result += S.substring(index, S.length());
			
			if(ans > result.length()) ans = result.length(); // 압축 문자열 최솟값 갱신
			comp++; // 단위를 증가시켜 과정 반복
		}
		if(S.length() == 1) ans = 1; // 예외 처리 : 문자열 S의 길이가 1이면 답은 무조건 1
		System.out.println(ans);
	}

}
