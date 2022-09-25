import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q13_치킨배달 {
	
	static int N, M; // NxN 크기의 도시, 남길 치킨 집의 수
	static ArrayList<int[]> House, Chicken; // 집과 치킨집의 좌표
	static int[][] Distance; // 집과 치킨집 사이의 거리를 저장할 배열

	static int ans = Integer.MAX_VALUE; // 정답
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		House = new ArrayList<>();
		Chicken = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				char ch = st.nextToken().charAt(0);
				if (ch == '1') House.add(new int[] {i, j}); // 집 위치 저장
				if (ch == '2') Chicken.add(new int[] {i, j}); // 치킨집 위치 저장
			}
		}
		
		getDistance();
		comb(0, 0, 0);
		System.out.println(ans);
	}

	// 1. 각 집의 치킨 거리를 구한다.
	public static void getDistance() {
		int h = House.size();
		int c = Chicken.size();
		
		Distance = new int[h][c];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < c; j++) {
				Distance[i][j] = Math.abs(House.get(i)[0] - Chicken.get(j)[0])
								+ Math.abs(House.get(i)[1] - Chicken.get(j)[1]);
			}
		}
	}

	// 2. nCr -> n: 치킨집의 수, r: m
	public static void comb(int cnt, int start, int flag) {
		if (cnt == M) {
			getAnswer(flag); // 치킨거리의 최솟값 갱신
			return ;
		}

		for (int i = start; i < Chicken.size(); i++) {
			comb(cnt + 1, i + 1, flag | 1 << i); // 조합
		}
	}
	
	// 치킨거리 구하기
	public static void getAnswer(int flag) {
		int sum = 0; // 도시의 치킨거리
		
		for (int i = 0; i < House.size(); i++) {
			int min = Integer.MAX_VALUE; // 각 집의 치킨 거리
			for (int j = 0; j < Chicken.size(); j++) {
				if ((flag & 1 << j) == 0) continue ;
				min = Math.min(min, Distance[i][j]);
			}
			sum += min;
			if (sum > ans) return ;
		}

		ans = sum;
	}
	
}
