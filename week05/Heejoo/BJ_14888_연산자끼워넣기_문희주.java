package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 수로 이루어진 수열 A와 N-1개의 연산자
 * 수와 수 사이에 연산자를 하나씩 넣어서 수식을 만들 수 있음
 * 연산자 우선순위는 무시하고 앞에서부터 계산
 * 나눗셈은 정수 나눗셈으로 몫만 취함
 * 음수를 양수로 나눌 때는 양수 나눗셈 후 몫을 음수로 ..
 * N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램
 */
public class BJ_14888_연산자끼워넣기_문희주 {
	static int N, max = -(int)1e9, min = (int)1e9;
	static int[] arr, ops;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 수의 개수 n
		
		// 수열 입력
		arr = new int[N]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 연산자 정보 입력
		ops = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}
		
		DFS(0, arr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void DFS(int count, int result) {
		if(count == N-1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(ops[i] == 0) continue;
			
			ops[i]--;
			DFS(count+1, calc(result, i, count+1));
			ops[i]++;
		}
	}
	
	public static int calc(int now, int op, int next) {
		switch (op) {
			case 0:
				return now + arr[next];
			case 1:
				return now - arr[next];
			case 2:
				return now * arr[next];
			case 3:
				return now / arr[next];
		}
		return 0;
	}

}
