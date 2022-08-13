package com.ssafy.cfj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class StudentRank implements Comparable<StudentRank>{
	String name;
	int kor;
	int eng;
	int math;
	
	public StudentRank() {
		
	}
	
	public StudentRank(String name, int kor, int eng, int math) {
		this.name = name; 
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	@Override
	public int compareTo(StudentRank o) {
		if (Integer.valueOf(o.kor).compareTo(Integer.valueOf(this.kor)) != 0) {
			return Integer.valueOf(o.kor).compareTo(Integer.valueOf(this.kor));
		} else {
			if (Integer.valueOf(this.eng).compareTo(Integer.valueOf(o.eng)) != 0) {
				return Integer.valueOf(this.eng).compareTo(Integer.valueOf(o.eng));
			} else {
				if (Integer.valueOf(o.math).compareTo(Integer.valueOf(this.math)) != 0) {
					return Integer.valueOf(o.math).compareTo(Integer.valueOf(this.math));
				}
			}
		}
		
		return this.name.compareTo(o.name);
	}
}

/**
 * 국영수 
 * @author devTaemin
 *
 */
public class KorEngMath {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<StudentRank> ranks = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			
			ranks.add(new StudentRank(name, kor, eng, math));
		}
		
		Collections.sort(ranks);
		
		for (int i = 0; i < N; i++) {
			System.out.println(ranks.get(i).name);
		}
		
	}

}
