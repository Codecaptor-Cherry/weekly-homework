package com.ssafy.cfj.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SortCard {
	
	static class Card implements Comparable<Card>{
		int num;
		
		public Card(int num) {
			this.num = num;
		}
		
		@Override
		public int compareTo(Card other) {
			return Integer.compare(this.num, other.num);
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Card> pQueue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			pQueue.add(new Card(Integer.parseInt(br.readLine())));
		}
		
		int answer = 0;
		
		if (n == 1) {
			System.out.println(answer);
			return;
		}
		
		while (true) {
			if (pQueue.size() == 1) {
				break;
			}
			
			int value1 = pQueue.poll().num;
			int value2 = pQueue.poll().num;
			
			int total = value1 + value2;
			answer += total;
			
			pQueue.offer(new Card(total));
		}

		System.out.println(answer);
	}
}
