package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * N개의 팀. 1 ~ N번
 * 작년에 비해서 상대적인 순위가 바뀐 팀의 목록만 발표
 * ex) 작년 13 > 6 ~ 올해 13 < 6 ~ (6, 13) 발표
 * 작년 순위와 상대적인 순위가 바뀐 모든 팀의 목록이 주어졌을 때, 올해 순위를 만드는 프로그램
 * 주어진 정보를 가지고 확실한 올해 순위를 만들 수 없는 경우가 있을 수도 있고, 일관성이 없는 잘못된 정보일 수도 있음
 * 위의 두 경우도 모두 찾아내야 함
 */

public class BJ_3665_최종순위_문희주 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 참가 팀의 수 N
			
			int[] inDegree = new int[N+1]; // 진입차수. 2 <= N <= 500
			boolean[][] graph = new boolean[N+1][N+1]; // 간선 연결 정보
			
			// 작년 순위 정보 입력
            ArrayList<Integer> arrayList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                arrayList.add(x);
            }
            // 방향 그래프의 간선 정보 초기화
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    graph[arrayList.get(i)][arrayList.get(j)] = true;
                    inDegree[arrayList.get(j)] += 1;
                }
            }
			
			// 등수 변화 목록 입력
			int M = Integer.parseInt(br.readLine()); // 상대적인 등수가 바뀐 쌍의 수 M
			boolean flag = true; // 일관성 체크
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 간선 방향 뒤집기
				if(graph[a][b]) {
					graph[a][b] = false;
					graph[b][a] = true;
					
					inDegree[a] += 1;
					inDegree[b] -= 1;
				}
				else {
					graph[a][b] = true;
					graph[b][a] = false;
					
					inDegree[a] -= 1;
					inDegree[b] += 1;
				}
			}
			
			// 위상 정렬 시작
			ArrayList<Integer> result = new ArrayList<>();
			Queue<Integer> q = new LinkedList<>();
			
			// 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
			for(int i = 1; i <= N; i++) {
				if(inDegree[i] == 0) q.offer(i);
			}
			
			boolean certain = true; // 위상 정렬 결과가 오직 하나인지의 여부 ~ 확실한 순위
			boolean cycle = false; // 그래프 내 사이클 존재 여부 ~ 일관성 체크
			
			// 노드의 개수만큼 반복
			for(int i = 0; i < N; i++) {
				// 큐가 비어있으면 사이클이 발생했다는 의미
				if(q.size() == 0) {
					cycle = true;
					break;
				}
				
				// 큐의 원소가 2개 이상이라면 가능한 정렬 결과가 여러 개라는 의미
				if(q.size() >= 2) {
					certain = false;
					break;
				}
				
				// 큐에서 원소 꺼내기
				int now = q.poll();
				result.add(now);
				
				// 해당 원소와 연결된 노드들의 진입차수 -1
				for(int j = 1; j <= N; j++) {
					if(graph[now][j]) {
						inDegree[j] -= 1;
						
						// 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
						if(inDegree[j] == 0) q.offer(j);
					}
				}
			}
			
			if(cycle) sb.append("IMPOSSIBLE\n");
			else if(!certain) sb.append("?\n");
			else {
				for(int i = 0; i < N; i++) {
					sb.append(result.get(i) + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
