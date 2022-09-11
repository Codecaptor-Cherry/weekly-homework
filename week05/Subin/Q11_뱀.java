import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11_뱀 {

	static int N, K, L, head[], tail[], map[][]/*0~3: 뱀 방향, 4: 사과*/, move[][];
	
	static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(map[i], -1);
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			map[r][c] = 4; // 사과
		}
		
		head = new int[] {0, 0};
		tail = new int[] {0, 0};
		map[0][0] = 1; // 뱀은 맨위 맨좌측에 위치하고 오른쪽을 향함

		L = Integer.parseInt(br.readLine());
		move = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			
			move[i][0] = t;
			move[i][1] = d == 'L' ? 3 : 1;
		}
		
		System.out.println(moveSnake());
	}

	private static int moveSnake() {
		int time = 0; // 게임 시간
		int d = 1; // 뱀의 이동 방향
		int i = 0; // move index
		
		while (true) {
			// 뱀은 몸길이를 늘려 머리를 다음 칸에 위치시킴
			int nr = head[0] + dir[d][0];
			int nc = head[1] + dir[d][1];
			
			// 벽 또는 자기자신과 부딪히면 게임 종료
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || (map[nr][nc] >= 0 && map[nr][nc] < 4)) break ;

			// 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌
			if (map[nr][nc] != 4) {
				int tailDir = map[tail[0]][tail[1]];
				map[tail[0]][tail[1]] = -1;
				
				// 꼬리 이동
				tail[0] += dir[tailDir][0];
				tail[1] += dir[tailDir][1];
			}
			map[nr][nc] = d; // 맵에 뱀 표시

			// 뱀의 머리 위치 저장
			head[0] = nr;
			head[1] = nc;
			
			++time;
			// 특정 시간마다 뱀 방향 변경
			if (i < L && time == move[i][0]) {
				d += move[i++][1];
				d %= 4;
				map[head[0]][head[1]] = d;
			}
		}
		
		return time + 1;
	}
	
}
