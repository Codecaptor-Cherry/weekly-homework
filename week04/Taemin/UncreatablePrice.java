package com.ssafy.cfj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UncreatablePrice {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(array);
		
		/**
		 * (1) 검증해야 할 양의 정수 시작점을 1로 초기화한다
		 * (2) 첫 시작에는 number가 1을 만들 수 있는지 확인이 필요하다
		 *     입력이 1 이상인 정수이기 때문에 1보다 큰 수가 나오면 1을 만들 수 없는 케이스이다
		 *     만약 첫 시작에 1이 있다면 target에 더해줘서 그 다음 검증이 필요한 2를 검사하자
		 * (3) 그 다음부터는 target보다 number의 값이 작거나 같은지 확인이 필요하다
		 *     예를 들어 두번째에는 1 혹은 2가 올 수 있다. 1이 오면 기존의 1+1이 2 이므로 가능하고
		 *     2가 들어온다면 2 자체로 검증 숫자를 만들 수 있기 때문이다.
		 *     그래서 만약 1이 들어온다면 target에 1을 더해서 3을 검증해야 하고, 2가 들어온다면 4를 검증해야한다
		 * (4) 마지막 예시로 만약 두 번째에 2가 들어왔고, 세 번째에 3이 들어온다면?
		 *     target인 4보다 작으므로 가능하다. 이전에 검증한 2에 이어서 3은 target 4보다 작은 수인 3을 만들 수 있으며
		 *     이전에 검증된 수에 더해져서 5, 6도 검증이 가능하다. 그래서 target은 4 + 3인 7이 된다
		 * (5) 이러한 방식으로 만약 들어온 숫자가 target보다 크면 target 숫자에 대한 검증이 불가능하기 때문에 검사를 멈춘다.
		 * */
		int target = 1;
		for (int number : array) {
			if (target >= number) {
				target += number;
			} else {
				break;
			}
		}
		
		System.out.println(target);
	}

}
