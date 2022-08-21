package com.ssafy.cfj.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * 탑승구
 * @author devTaemin
 *
 */
public class BoardingGate {
	
	/** Number of Gate, Number of Plane*/
	static int NumOfGate, NumOfPlane;
	static int[] Parents, Planes;
	
	public static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if (a < b) {
			Parents[b] = a;
		} else {
			Parents[a] = b;
		}
	}
	
	public static int findParent(int a) {
		if (Parents[a] != a) {
			Parents[a] = findParent(Parents[a]);
		}
		
		return Parents[a];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		NumOfGate = Integer.parseInt(br.readLine());
		NumOfPlane = Integer.parseInt(br.readLine());
		Parents = new int[NumOfGate + 1];
		Planes = new int[NumOfPlane + 1];
		
		/** Initialize parents */
		for (int i = 1; i <= NumOfGate; i++) {
			Parents[i] = i;
		}
		
		/** Initialize planes */
		for (int i = 1; i <= NumOfPlane; i++) {
			Planes[i] = Integer.parseInt(br.readLine());
		}
		
		
		int count = 0;
		for (int i = 1; i <= NumOfPlane; i++) {
			int plane = Planes[i];
			int gate = findParent(plane);
			
			if (gate == 0) {
				break;
			}
			else {
				union(gate, gate - 1);
				count++;
			}
		}
		
		System.out.println(count);
	}
}
