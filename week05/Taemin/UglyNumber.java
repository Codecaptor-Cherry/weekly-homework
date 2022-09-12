package com.ssafy.cfj.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class UglyNumber {
	
	static int N;
	static PriorityQueue<Integer> PQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PQueue = new PriorityQueue<>();
		PQueue.add(1);
		
		int index = 1;
		while (index < N) {
			int top = PQueue.poll();
			int[] array = new int[] {top * 2, top * 3, top * 5};
			
			for (int check : array) {
				if (!PQueue.contains(check)) {
					PQueue.add(check);
				}
			}
			
			index++;
		}
		
		System.out.println(PQueue.poll());
	}
}
