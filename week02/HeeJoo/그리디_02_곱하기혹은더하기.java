package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class 그리디_02_곱하기혹은더하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine(); // 각 자리가 숫자(0~9)로만 이루어진 문자열 S
		
		/* 곱하기 또는 더하기 연산을 하여 만들어질 수 있는 가장 큰 수 구하기
		 * 1. 연산을 수행하는 두 수 중에 0 또는 1이  있는 경우 : 더하기 
		 * 0을 곱하면 0이 되버려서 더 작아짐 / 1을 곱하면 변동 x
		 * 2. 이외의 수는 곱하기
		 */
		
		Stack<Integer> stack = new Stack<>();
		stack.add(s.charAt(0) - '0');
		for(int idx = 1; idx < s.length(); idx++) {
			int left = stack.pop();
			int right = s.charAt(idx) - '0';
			
			if(checkNum(left) || checkNum(right)) { // 둘 중 하나라도 0 또는 1인 경우
				stack.add(left+right); // 더하기 연산 결과를 스택에 저장
			}
			else stack.add(left*right);
		}
		
		System.out.println(stack.pop());
	}
	
	public static boolean checkNum(int num) {
		if (num == 0 || num == 1) return true; // 해당 수가 0 또는 1인 경우 true
		return false; // 이외의 수는 false
	}

}
