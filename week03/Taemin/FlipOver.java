package com.ssafy.cfj.greedy;

import java.util.Scanner;

/**
 * 뒤집기
 * @author devTaemin
 */
public class FlipOver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/** Array of input characters */
		char[] array = sc.next().toCharArray();
		
		/** Check if the number is flipped over */
		boolean flip = true;
		
		/** Count the number of bundled zero and one */
		int countZero = 0;
		int countOne = 0;
		
		for (int i = 0; i < array.length; i++) {
			/** If the character is one */
			if (array[i] == '1') {
				if (flip) countOne++;
				flip = false;
				/** Check if flip in the next sequence */
				if (i + 1 < array.length && array[i + 1] == '0') {
					flip = true;
				}
			} 
			/** If the character is zero*/
			else {
				if (flip) countZero++;
				flip = false;
				/** Check if flip in the next sequence */
				if (i + 1 < array.length && array[i + 1] == '1') {
					flip = true;
				}
			}
		}
		
		int result = Math.min(countZero, countOne);
		System.out.println(result);
	}
}
