package com.ssafy.cfj.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 퇴사
 */
public class Resignation {
	
	static int N;
	static int[] MaxPrice;
	static ArrayList<Integer[]> Schedule;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		MaxPrice = new int[N + 1];
		Schedule = new ArrayList<Integer[]>();
		Schedule.add(new Integer[] {0, 0});
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			Schedule.add(new Integer[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		for (int day = 1; day <= N; day++) {
			Integer[] schedule = Schedule.get(day);
			int period = schedule[0];
			int charge = schedule[1];
			
			/** Compare */
			if (MaxPrice[day - 1] > MaxPrice[day]) {
				MaxPrice[day] = MaxPrice[day - 1];
			}
			
			if (day + period - 1 <= N) {
				if (MaxPrice[day + period - 1] < MaxPrice[day - 1] + charge) {
					MaxPrice[day + period - 1] = MaxPrice[day - 1] + charge;					
				}
			}
		}
		
		System.out.println(MaxPrice[N]);
	}
}
