package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/*
 * 공항에 G개의 탑승구 존재. 각각의 탑승구는 1번부터 G번까지 번호로 구분
 * P개의 비행기가 차례대로 도착할 예정
 * i번째 비행기를 1번부터 g_i(1 <= g_i <= G) 탑승구 중 하나에 영구 도킹
 * 다른 비행기가 도킹하지 않은 탑승구에만 도킹 가능
 * P개의 비행기를 순서대로 도킹 ~ if 도킹 불가능한 비행기가 나오는 경우, 공항 운행 중지
 * 탑승구의 개수 G와 비행기의 개수 P가 주어질 때, 비행기 최대 도킹수를 구하는 프로그램
 * 
 * 가능한 범위의 가장 마지막 탑승구부터 확인 ~ 최대한 다른 비행기들이 많이 도킹할 수 있도록 ,,,
 * 이중 for문을 이용하면 시간 초과 ~ 서로소 집합 이용
 * parent[i] = i : 다른 노드와 연결 x = 아직 도킹 x
 * 도킹을 하면 앞번호 탑승구를 루트로 UnionParent
 * parent[i] = i-1 : 비행기 도킹 완료
 * findParent의 결과가 최종적으로 0이 나오면 더이상 도킹이 불가능 ~ 종료
 */
public class 그래프_42_탑승구_문희주 {
	// output : 2
	static String src1 = "4\r\n" +
						"3\r\n" +
						"4\r\n" +
						"1\r\n" +
						"1\r\n";
	
	// output : 3
	static String src2 = "4\r\n" +
						"6\r\n" +
						"2\r\n" +
						"2\r\n" +
						"3\r\n" +
						"3\r\n" +
						"4\r\n" +
						"4\r\n";
	
	static int[] parent;
	
	// a 탑승구의 부모 찾기 ~ 자기 자신이 아니면 도킹 완료 상태
	static int findParent(int a) {
		// 계속 부모타고 올라가서 0이면 더이상 도킹 불가능
		// 빈 자리가 있으면 도킹
		if(parent[a] != a) return parent[a] = findParent(parent[a]); // 최적화 ~ 타고타고 올라가며 부모를 계속 갱신 ~ 후에 nn -> 0으로 바로 뛰어넘기 가능
		return a;
	}
	
	// 도킹 상태 갱신 : parent[a] = a-1;
	static void unionParent(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		parent[pa] = pb;
	}
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedReader br = new BufferedReader(new StringReader(src2));
		
		int G = Integer.parseInt(br.readLine()); // 탑승구의 수 G
		int P = Integer.parseInt(br.readLine()); // 비행기의 수 P
		
		
		parent = new int[G + 1]; // 탑승구 배열. 0번 인덱스를 1번 인덱스의 부모로 사용
		for(int i = 1; i <= G; i++) {
			parent[i] = i; // 부모를 자기 자신으로 초기화
		}
		// 입력받고 바로 도킹 수행
		// K : i번째 비행기는 1번부터 k번째 탑승구 중 하나에 도킹 가능
		int count = 0; // 최종 출력 : 비행기 최대 도킹 수
		for(int i = 1; i <= P; i++) {
			int flight = Integer.parseInt(br.readLine()); // 각 비행기가 도킹할 수 있는 탑승구의 범위 정보 K
			
			// 도킹 시작
			int dock = findParent(flight); // 도킹 가능한 탑승구 찾기
			if(dock != 0) { // 아직 해당 탑승구가 도킹되지 않은 상태라면
				unionParent(dock, dock-1); // 도킹
				count++;
			}
			else break; // 더 이상 도킹이 불가능한 경우 종료
		}
		System.out.println(count);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
