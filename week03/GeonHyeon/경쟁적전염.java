package day_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_18405_경쟁적전염_김건현 {
	static int N; //배열 범위
	static int K; //1번부터 K번 바이러스
	static int S;
	static int time; //현재 시간
	static Point[][] area;
	static Queue<Point> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		area = new Point[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				area[i][j] = new Point(i,j,Integer.parseInt(st.nextToken()),0);
				if(area[i][j].type>0) {
					q.offer(area[i][j]);
				}
			}
		}
		st = new StringTokenizer(br.readLine()); // s,x,y
		S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		BFS();
		System.out.println(area[X-1][Y-1].type);
		
		
		
	}
	
	static class Point{
		int row;
		int col;
		int type;
		int time;
		public Point(int type, int time) {
			super();
			this.type = type;
			this.time = time;
		}
		public Point(int row, int col, int type, int time) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
			this.time = time;
		}
		public Point(int row, int col, int type) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
		}
		
	}
	
	//배열 범위 안인지
	static boolean isRange(int row, int col) {
		if(row>=0&&row<N&&col>=0&&col<N) {
			return true;
		}
		return false;
	}
	
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print("|"+area[i][j].type+" "+area[i][j].time+"|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//BFS
	static void BFS() {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while(!q.isEmpty()) {
			if(time == S) {
				break;
			}
			int size = q.size();
			time++;
			//큐에 있는 해당 레벨만큼 반복
			for(int i=0;i<size;i++) {
				//하나 꺼내서
				Point p = q.poll();
				if(p.type != area[p.row][p.col].type) {
					continue;
				}
//				p.time = time;
				//4방향 탐색
				for(int pos=0;pos<4;pos++) {
					int x = p.row+dx[pos];
					int y = p.col+dy[pos];
					//범위안이면 -> 우선 바이러스 인지 비어있는지.
					if(isRange(x,y)) {
						if(area[x][y].type ==0) {
							area[x][y].type = p.type;
							area[x][y].time = time;
							q.offer(new Point(x,y,p.type));
						}
						//안이 비어있지 않은 곳. 경쟁 혹은 시간체크
						else {
//							if(area[x][y].type == p.type) continue;
							//시간이 더 에전거이다 -> 이미 고정이니 넘어감 하지만 시간이 0인 0블록은 위에 조건문에서 처리됨
							if(area[x][y].time!=time) {
								continue;
							}
							//시간이 같다면 -> 경쟁
							else if(area[x][y].time == time) {
								if(area[x][y].type > p.type) {
									area[x][y].type = p.type;
									q.offer(new Point(x,y,p.type));
								}
							}
						}
					}
				}
			}
//			print();
		}
	}
}
