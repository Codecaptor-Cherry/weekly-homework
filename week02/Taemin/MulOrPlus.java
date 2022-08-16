package com.ssafy.cfj.greedy;

import java.util.Scanner;

/***
 * 곱하기 혹은 더하기
 * @author devTaemin
 *
 */
public class MulOrPlus {
	
	static String S;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.next();
		
		int result = S.charAt(0) - '0';
		for (int i = 1; i < S.length(); i++) {
			int operand = S.charAt(i) - '0';
			if (result <= 1 || operand <= 1) {
				result += operand;
			} else {
				result *= operand;
			}
		}
		
		System.out.println(result);
	}

}
