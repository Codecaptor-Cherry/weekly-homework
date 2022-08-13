package com.ssafy.cfj.implemantation;

import java.util.Scanner;

/**
 * 럭키 스트레이트 
 * @author devTaemin
 *
 */
public class LuckyStraight {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] inputs = sc.next().split("");
		int size = inputs.length;
		
		// 0 ~ (size / 2) (exclusive)
		// (size / 2) ~ size (exclusive)
		int front = 0;
		for (int i = 0; i < size / 2; i++) {
			front += Integer.parseInt(inputs[i]);
		}
		
		int rear = 0;
		for (int i = size / 2; i < size; i++) {
			rear += Integer.parseInt(inputs[i]);
		}
		
		if (front == rear) {
			System.out.println("LUCKY");
		} else {
			System.out.println("READY");
		}
	}

}
