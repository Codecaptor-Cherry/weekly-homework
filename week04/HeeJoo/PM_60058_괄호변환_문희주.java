package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * '('와 ')'로만 이루어진 문자열 P
 * 균형잡힌 문자열 : '('의 개수 == ')'의 개수 ex) (()))(
 * 올바른 괄호 문자열 : 균형잡힌 문자열일 때, '('와 ')'의 짝이 모두 맞는 경우 ex) (())()
 * 괄호로만 이루어진 문자열 W가 균형잡힌 문자열이라면, 다음과 같은 과정을 통해 올바른 괄호 문자열로 변환 가능
 * 
 * 1. 입력이 빈 문자열일 경우. return 빈 문자열
 * 2. 문자열 W를 두 "군형잡힌 괄호 문자열" u, v로 분리
 * u는 균형잡힌 괄호 문자열로 재분리 불가능. v는 빈 문자열이 될 수 있음.
 * 
 * 3. 문자열 u가 올바른 괄효 문자열이라면, 문자열 v에 대해 1단계부터 수행
 * 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환
 * 
 * 4. 문자열 u가 올바른 괄호 문자열이 아니라면, 아래 과정 수행
 * 4-1. 빈 문자열에 첫 번째 문자로 '(' 붙이기
 * 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 이어 붙이기
 * 4-3. ')' 붙이기
 * 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙이기
 * 4-5. 생성된 문자열 반환
 * 
 * 1. u, v 나누기
 * 2. u 올바른 / 균형 판단
 * 3. ( + v 재귀 + ) + u 앞뒤제거 및 괄호 방향 뒤집기
 * 
 * 균형잡힌 괄호 문자열 P가 매개변수로 주어질 때, 주어진 알고리즘을 수행해 올바른 괄호 문자열로 변환한 결과를 return
 */

public class PM_60058_괄호변환_문희주 {
	static String p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		p = br.readLine();
		
		String result = DFS(p);

		System.out.println(result);
		

	}
	
	// 입력된 문자열 w에 대해서 과정 수행
	public static String DFS(String w) {
		if(w.length() == 0) return ""; // 빈 문자열인 경우, 빈 문자열 반환
		if(check(w)) return w; // 이미 올바른 괄호 문자열인 경우, 그대로 반환
		
		String result = ""; // 수행 결과
		String u, v; // 두 균형잡힌 괄호 문자열 u, v
		
		int index = split(w); // u와 v의 분기 인덱스 구하기
		
		u = w.substring(0, index);
		v = w.substring(index, w.length());
		
		// u가 올바른 괄호 문자열일 때
		boolean flag = check(u);
		if(flag) {
			result += u; // u는 그대로
			// v에 대해 재귀한 결과를 이어 붙이기
			result += DFS(v);
		}
		// u가 올바른 괄효 문자열이 아닐 때
		else {
			result += "("; // 4-1
			result += DFS(v); // 4-2
			result += ")"; // 4-3
			result += func(u);  // 4-4
		}
		
		return result;
	}
	
	// 올바른 괄호 문자열인지 확인하는 메서드
	public static boolean check(String u) {
		int open = 0, close = 0; // (, ) 개수
		
		for(int idx = 0; idx < u.length(); idx++) {
			if(u.charAt(idx) == '(') {
				open++;
			}
			else {
				close++;
			}
			
			if(open < close) return false; // open < close가 되는 경우 올바르지 않음 .. ex) )(
		}
		return true;
	}
	
	// 4-4. u의 첫 번째와 마지막 문자 제거. 나머지 문자열의 괄호 방향 뒤집기
	public static String func(String u) {
		String result = "";
		
		for(int idx = 1; idx < u.length()-1; idx++) {
			if(u.charAt(idx) == '(') result += ')';
            else result += '('; 
		}
		
		return result;
	}
	
	// 문자열 s를 u와 v로 나누기
	public static int split(String s) {
		int index = 0;
		int open = 0, close = 0; // (, ) 개수
		
		if(s.length() == 0) return index; // 빈 문자열
		
		for(int idx = 0; idx < s.length(); idx++) {
			if(s.charAt(idx) == '(') {
				open++;
				index++;
			}
			else {
				close++;
				index++;
			}
			
			if(open == close) break; // 개수가 동일하면 균형잡힌 문자열 완성
		}
		
		return index;
	}

}






























