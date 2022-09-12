package 구현;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 사과를 먹으면 뱀의 길이가 늘어남
 * 벽 또는 자기 자신의 몸과 부딪히면 게임 오버
 * NxN 정사각 보드에 몇몇 칸에는 사과 존재. 보드의 상하좌우 끝에는 벽 존재
 * 게임을 시작할 때 뱀은 (0, 0)위치. 길이는 1. 방향은 오른쪽
 * 1. 몸길이를 늘려 머리를 다음 칸에 위치
 * 2. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않음
 * 3. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌. 즉, 몸길이는 변하지 않음
 * 사과의 위치와 뱀의 이동 경로가 주어질 때, 이 게임이 몇 초에 끝나는지 계산하는 프로그램
 */

class Direct{
	int t;
	char d;
	
	public Direct(int t, char d) {
		this.t = t;
		this.d = d;
	}
}
public class BJ_3190_뱀_문희주 {
	static int N, L, result = 0;
	static int[][] map, dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우하좌상
	static Direct[] directions;
	static Queue<Point> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 보드의 크기 N
		int K = Integer.parseInt(br.readLine()); // 사과의 개수 K
		
		map = new int[N][N];
		// 사과의 위치 정보 입력
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 2;
		}
		
		L = Integer.parseInt(br.readLine()); // 뱀의 방향변환 횟수
		directions = new Direct[L];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine()); 
			// t초에 d방향으로 전환
			directions[i] = new Direct(Integer.parseInt(st.nextToken()),  st.nextToken().charAt(0));
		}
		
		q = new LinkedList<Point>();
		q.offer(new Point(0, 0));
		Dummy(0, new Point(0, 0), 0, 0);
		
		System.out.println(result);
	}
	
	public static void Dummy(int time, Point head, int d, int cnt) {
		// 방향 전환 확인
		if(cnt < directions.length && directions[cnt].t == time) {
			d += Direct(directions[cnt].d);
			
			if (d < 0) d = 3;
			else d %= 4;
			
			cnt++;
		}
		
		// 머리가 이동할 좌표
		int nx = head.x + dir[d][0];
		int ny = head.y + dir[d][1];
		
		// 종료 조건 1. 벽에 부딪힘
		if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
			result = time+1;
			return;
		}
		
		// 종료 조건 2. 자신의 몸과 부딪힘
		if(map[nx][ny] == 1) {
			result = time+1;
			return;
		}
		
		// 해당 칸에 사과가 존재하는 경우, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않음
		if(map[nx][ny] == 2) {
			map[nx][ny] = 1;
			q.offer(new Point(nx, ny));
			Dummy(time+1, new Point(nx, ny), d, cnt);
		}
		else { // 빈 칸인 경우, 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌. 즉, 몸길이는 변하지 않음
			map[nx][ny] = 1;
			Point tail = q.poll();
			map[tail.x][tail.y] = 0; 
			q.offer(new Point(nx, ny));
			Dummy(time+1, new Point(nx, ny), d, cnt);
		}
	}
	
	// L : 반시계 90도
	// D : 시계 90도
	public static int Direct(char c) {
		if(c == 'L') { // L
			return -1;
		}
		
		return 1; // D
		
	}
}
