import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class 그리디_모험가길드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 전체 모험가의 수 N
		int n = Integer.parseInt(br.readLine());
		
		// 각 모험가의 공포도
		st = new StringTokenizer(br.readLine());
		int[] fear = new int[n];
		for(int i = 0; i < n; i++) {
			fear[i] = Integer.parseInt(st.nextToken());
		}
		
		// 공포도 오름차순 정렬
		Arrays.sort(fear);
		
		// 공포도 X => X명 이상으로 구성한 그룹
		int result = 0; // 최종 출력 : 여행을 떠날 수 있는 그룹 수의 최댓값
		List<Integer> list = new ArrayList<>(); // 여행을 떠나는 그룹
		for(int i = 0; i < n; i++) {
			list.add(fear[i]); // 그룹에 fear[i] 모험가 추가
			
			// 그룹의 크기가 공포도 이상이면(한 명씩 추가해서 그냥 등호 사용)
			// 1개의 그룹 결성 완료 ~ result + 1
			// list.claer()를 해서 다음 그룹 준비
			if(list.size() == fear[i]) {
				result++;
				list.clear();
			}
		}
		
		System.out.println(result);
	}

}
