package com.ssafy.cfj.implemantation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/***
 * 문자열 재정렬
 * @author devTaemin
 *
 */
public class RearrangeString {
	
	static String S;
	static int Total;
	static ArrayList<Character> Array;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		S = sc.next();
		Array = new ArrayList<>();
		
 	 	for (int i = 0; i < S.length(); i++) {
 	 		char target = S.charAt(i);
 	 		/** If target is Character (Upper case)*/
 	 		if ((int)target >= 65) {
 	 			Array.add(target);
 	 		/** If target is Number */
 	 		} else {
 	 			Total += target - '0';
 	 		}
 	 	}
 	 	
 	 	/** Sort in an ascending order */
 	 	Collections.sort(Array, new Comparator<Character>() {

			@Override
			public int compare(Character o1, Character o2) {
				return o1 - o2;
			}
 	 	});
 	 	
 	 	for (Character c : Array) {
 	 		sb.append(c);
 	 	}	
 	 	
 	 	/** Consider the case without number */
 	 	if (S.length() != Array.size()) {
 	 		sb.append(Total); 	 		
 	 	}
 	 	
 	 	System.out.println(sb);
	}
}
