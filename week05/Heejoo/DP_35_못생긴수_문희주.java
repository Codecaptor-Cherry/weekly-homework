package 다이나믹프로그래밍;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 못생긴 수 : 오직 2, 3, 5만을 소인수로 가지는 수 ~ 오직 2, 3, 5를 약수로 가지는 합성수
 * N번째 못생긴수를 찾는 프로그램
 * ex) 10번째 = 12, 4번째 = 4
 * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, ...
 */

public class DP_35_못생긴수_문희주 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int[] ugly = new int[1001]; // 1 <= N <= 1000;
		ugly[0] = 1;
		
		int N = in.nextInt();
		
		int i2 = 1, i3 = 1, i5 = 1;
		int two = 2, three = 3, five = 5;
		
		for(int i = 1; i < N; i++) {
			int min = Math.min(two, three);
			min = Math.min(min, five);
			
			ugly[i] = min;
			
			if(min == two) two = ugly[i2++] * 2;
			if(min == three) three = ugly[i3++] * 3;
			if(min == five) five = ugly[i5++] * 5;
			
		}
		System.out.println(Arrays.toString(ugly));
		System.out.println(ugly[N-1]);
	}

}
