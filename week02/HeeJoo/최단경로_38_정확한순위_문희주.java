package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 학생 N명 ~ 성적 분실, 비교 결과 일부만 존재 ex) 1번 < 5번, 3번 < 4번, 4번 < 2번, 5번 < 2번 ... 등
 * 학생 N명의 성적은 모두 다름
 * 주어진 정보를 통해 순위를 정확히 알 수 있는 학생도 있고, 알 수 없는 학생도 존재
 * 학생들의 성적을 비교한 결과가 주어질 때, 성적 순위를 정확히 알 수 있는 학생 수를 구하는 프로그램
 * 
 * x의 자식 수(k) + 부모(p) = n - 1이면 x의 등수는  n - k =  p + 1 등
 * 플로이드 워셜 ~ 연결 여부 갱신
 * graph[i][j]로 연결 여부 체크 ~ 자식과 부모의 수 구하기 
 */
public class 최단경로_38_정확한순위_문희주 {
	// output : 1
		static String src = "6 6\r\n" +
							"1 5\r\n" +
							"3 4\r\n" +
							"4 2\r\n" +
							"4 6\r\n" +
							"5 2\r\n" +
							"5 4\r\n";
		
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedReader br = new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 학생의 수 N
		int m = Integer.parseInt(st.nextToken()); // 성적 비교 횟수
		
		// M개의 성적 비교 정보 입력(A B : A의 점수 < B의 점수)
		int[][] graph = new int[n][m]; // 인접 행렬
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			graph[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		// 플로이드 워셜 ~ 연결 관계 갱신
		for(int k = 0; k < n-1; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(graph[i][k] == 1 && graph[k][j] == 1) graph[i][j] = 1;
				}
			}
		}
		
		int count = 0; // 최종 출력 : 성적 순위를 정확히 알 수 있는 학생 수
		// 1번부터 N번 학생까지 하나씩 검사. i = 학생 번호
		for(int i = 0; i < n; i++) {
			// 행 : 윗 등수, 열 : 아래 등수
			int up = 0;
			int down = 0;
			for(int j = 0; j < n; j++) {
				if(graph[i][j] == 1) up++;
				if(graph[j][i] == 1) down++;
			}
			// 등수를 정확히 알 수 있는지 체크
			if(up + down == n - 1) count++;
		}
		
		System.out.println(count);
	}

}
