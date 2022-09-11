import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q20_감시피하기 {

	static int N;
	static ArrayList<int[]> teachers; // 선생님 좌표 리스트
	static char[][] map;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		teachers = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				
				if (map[i][j] == 'T') teachers.add(new int[] {i, j}); 
			}
		}
		
		if (dfs(0, 0, 0)) System.out.println("YES");
		else System.out.println("NO");
	}

	private static boolean dfs(int cnt, int r, int c) {
		// 벽이 3개가 세워지면 감시를 피할 수 있는지 확인
		if (cnt == 3) return check();
		
		// 장애물 설치하기
		int j = c;
		for (int i = r; i < N; i++) {
			for (; j < N; j++) {
				if (map[i][j] == 'X') {
					map[i][j] = 'O';
					if (dfs(cnt + 1, i, j + 1)) return true;
					map[i][j] = 'X';
				}
			}
			j = 0;
		}
		return false;
	}
	
	private static boolean check() {
		int s = 0; // 발각된 학생 수
		
		for (int[] t: teachers) {
			for (int i = 0; i < 4; i++) {
				int r = t[0];
				int c = t[1];
				
				while (true) {
					r += dir[i][0];
					c += dir[i][1];
					
					// 범위를 벗어나거나 장애물일 경우 break
					if (r < 0 || r >= N || c < 0 || c >= N ||
							map[r][c] == 'O') break;
					
					if (map[r][c] == 'S') {
						++s;
					}
				}
			}
		}
		
		return s == 0;
	}
}
