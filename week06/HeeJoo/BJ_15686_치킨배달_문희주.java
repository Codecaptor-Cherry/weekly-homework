package 구현;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * NxN 도시 ~ (1, 1)부터 시작
 * 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리
 * 도시의 치킨 거리 = 모든 집의 치킨 거리
 * 두 칸 사이의 거리 = |r1 - r2| + |c1 - c2|
 * 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업
 * 도시의 최소 치킨 거리 고르기
 */

public class BJ_15686_치킨배달_문희주 {
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] Map;
	static List<Point> homes, chicks, result;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		homes = new ArrayList<>();
		chicks = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				
				// 빈 칸(0), 집(1), 치킨집(2)
				if(Map[i][j] == 1) {
					homes.add(new Point(i, j));
				} else if(Map[i][j] == 2) {
					chicks.add(new Point(i, j));
				}
			}
		}
		
		result = new ArrayList<>();
		visited = new boolean[chicks.size()];
		perm(0, 0);
		
		System.out.println(answer);
		
	}
	
	public static void perm(int index, int cnt) {
		if(cnt == M) {
			int sumDist = 0; // 현재 경우의 치킨 거리 합
			int min; // i번째 집의 최소 치킨 거리
			
			for(int i = 0; i < homes.size(); i++) {
				min = Integer.MAX_VALUE;
				for(int j = 0; j < M; j++) {
					int dist = getDist(homes.get(i), result.get(j));
					
					if(min > dist) min = dist; // 최소 치킨 거리로 갱신
				}
				sumDist += min;
				
				if(answer < sumDist) return; // 도중에 현재까지의 정답보다 크다면 종료
			}
			
			// 정답 갱신
			if(answer > sumDist) {
				answer = sumDist;
				return;
			}
		}
		
		// 부분 집합
		for(int idx = index; idx < chicks.size(); idx++) {
			if(visited[idx]) continue;
			
			visited[idx] = true;
			result.add(chicks.get(idx));
			perm(idx+1, cnt+1);
			
			result.remove(cnt);
			visited[idx] = false;
			perm(idx+1, cnt);
		}
	}
	
	public static int getDist(Point home, Point chick) {
		return Math.abs(home.x - chick.x) + Math.abs(home.y - chick.y); 
	}

}
