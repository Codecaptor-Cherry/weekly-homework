//package day_0809;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class BJ_18352_특정거리의도시찾기_김건현 {
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		Queue<Integer> q = new LinkedList<>(); //bfs를 위한 큐
//		boolean[] visited;
//		int[][] road;
//
//		List<Integer> list = new ArrayList<>();
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		int K = Integer.parseInt(st.nextToken());
//		int X = Integer.parseInt(st.nextToken());
//		int city = 0;
//		int distance = 0;
//		visited = new boolean[N+1]; //도시는 1번부터시작 도시명 = 인덱스
//		visited[0] = true;
//		road = new int[N+1][N+1]; //길을 저장. 도시가 1부터시작하니 N+1로 크기설정
////		ArrayList<ArrayList<Integer>> road = new ArrayList<>();
//		for(int i=0;i<=N+1;i++) {
//			road.add(new ArrayList<>());
//		}
//
//		//road초기화
//		for(int i=0;i<M;i++) {
//			st = new StringTokenizer(br.readLine());
//			int a = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
////			road.get(a).add(b);
//			road[a][b] = 1;
//		}
//
//		q.offer(X);//우선출발점을 큐에넣어주고.
//		visited[X] = true;
//
//		//큐가 빌때 까지 반복. 너비우선탐색
//		while(!q.isEmpty()) {
//			int size = q.size();
//			for(int k=0;k<size;k++) { // 큐에있는 요소들을 하나씩 꺼낸다. 1이 들어있으면 꺼내고 2,3을 큐에넣어줌
//				city = q.poll();
////				System.out.println("Polled "+city);
//
//				for(int i=1;i<=road.get(city).size();i++) {
//					if(!visited[road.get(city).get(i-1)]) { //길이 있고 방문을 안했다면.
//						visited[road.get(city).get(i-1)] = true;
//						q.offer(road.get(city).get(i-1));
////						System.out.println("Offered "+i);
//					}
//				}
//			}
//			distance++;
//			if(distance==K) {
//				for(int k=0;k<q.size();k++) { // 큐에있는 요소들을 하나씩 꺼낸다. 1이 들어있으면 꺼내고 2,3을 큐에넣어줌
//					list.add(q.poll());
//				}
//				break;
//			}
//		}
//		if(list.size()==0)bw.write("-1");
//		else for(int tmp:list) bw.write(tmp+"\n");
//		bw.flush();
//		bw.close();
//		br.close();
//	}
//
//
//}
