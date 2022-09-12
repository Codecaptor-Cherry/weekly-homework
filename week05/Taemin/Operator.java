package com.ssafy.cfj.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Operator {
	
	static int N, Max, Min;
	static int[] Operands;
	static int[] numOfOperators;
	static int[] Operators;
	static int[] Sequence;
	
	public static void DFS(int depth, int flag) {
		if (depth == N - 1) {
			int result = Operands[0];
			
			for (int i = 0; i < N - 1; i++) {
				int operator = Sequence[i];
				
				switch(operator) {
				case 0:
					result += Operands[i + 1];
					break;
				case 1:
					result -= Operands[i + 1];
					break;
				case 2:
					result *= Operands[i + 1];
					break;
				case 3:
					result /= Operands[i + 1];
					break;
				}
			}
			
			Max = Math.max(Max, result);
			Min = Math.min(Min, result);
			return;
		}
		
		for (int i = 0; i < N - 1; i++) {
			if ((flag & 1 << i) == 0) {
				/** Store operator */
				Sequence[depth] = Operators[i];
				DFS(depth + 1, flag | 1 << i);				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/** Store operands */
		N = Integer.parseInt(br.readLine());
		Operands = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Operands[i] = Integer.parseInt(st.nextToken());
		}
		
		/** Store number of operators */
		numOfOperators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			numOfOperators[i] = Integer.parseInt(st.nextToken());
		}
		
		/** Store operators */
		Operators = new int[N - 1];
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int number = numOfOperators[i];
			for(int j = 0; j < number; j++) {
				Operators[idx++] = i;
			}
		}
		
		
		Max = Integer.MIN_VALUE;
		Min = Integer.MAX_VALUE;
		Sequence = new int[N - 1];
		DFS(0, 0);
		
		System.out.println(Max);
		System.out.println(Min);
	}
}
