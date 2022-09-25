package com.ssafy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BJ_15686_치킨배달_김건현 {
	static int MIN = Integer.MAX_VALUE; //치킨거리 최소값
	static int[][] map;
	static int M;
	static int N;
	static boolean[][] visited;
	static List<Point> chickens = new ArrayList<>();
	static List<Point> homes = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		//초기화
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==2) { 
					chickens.add(new Point(i,j));
				}
				else if(map[i][j] == 1) {
					homes.add(new Point(i,j));
				}
			}
		}
		
		combination(0,0);
		System.out.println(MIN);
	}
	
	//조합된 것들로 계산하는 함수
	static void distance() {
		int distanceSum = 0;
		//각각의 집의 최단거리 계산
		int min;
		for(Point home:homes) {
			min = Integer.MAX_VALUE;
			for(Point chicken: chickens) {
				if(visited[chicken.row][chicken.col]) {
					int distance = Math.abs(home.row - chicken.row)+Math.abs(home.col - chicken.col);
					min = Math.min(min, distance);
				}
			}
			distanceSum+=min;
			if(distanceSum>=MIN) break;
		}
		
		if(MIN>distanceSum) {
			MIN = distanceSum;
		}
	}
	
	//조합하는 함수
	static void combination(int count,int idx) {
		//M개를 골랐다면 최단거리계산
		if(count == M) {
			distance();
		}
		for(int i=idx;i<chickens.size();i++) {
			if(!visited[chickens.get(i).row][chickens.get(i).col]) { //방문하지 않았다면
				visited[chickens.get(i).row][chickens.get(i).col] = true;
				combination(count+1,i+1);
				visited[chickens.get(i).row][chickens.get(i).col] = false;
				combination(count,i+1);
			}
		}
	}
	
	static class Point{
		public int row;
		public int col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
}
