import java.util.Arrays;

class Solution {
	static class Info implements Comparable<Info> {
		int stageNum;
		double failure;
		
		public Info(int stageNum, double failure) {
			this.stageNum = stageNum;
			this.failure = failure;
		}
		
		@Override
		public int compareTo(Info o) {
			int comp = Double.compare(o.failure, failure); // 실패율 내림차순
			if (comp == 0) {
				return Integer.compare(stageNum, o.stageNum); // 번호 오름차순
			}
			return comp;
		}
	}
	
    public int[] solution(int N, int[] stages) {
    	int[] answer = new int[N];
    	int len = stages.length;
    	
    	Arrays.sort(stages);
    	
    	int idx = 0;
    	Info[] info = new Info[N];
    	
    	for (int i = 1; i <= N; i++) {
    		while (idx < len && stages[idx] < i) ++idx;
    		int tries = len - idx; // 스테이지에 도착한 사람의 수 
    		
    		idx = 0;
    		int clear = 0; // 스테이지를 클리어하지 못한 사람의 수
    		while (idx < len && stages[idx] <= i) {
    			if (stages[idx] >= i) ++clear;
    			idx++;
    		}

    		if (tries == 0) info[i - 1] = new Info(i, 0);
    		else info[i - 1] = new Info(i, (double)clear / tries);
    	}
    	Arrays.sort(info);
    	
    	for (int i = 0; i < N; i++) {
    		answer[i] = info[i].stageNum;
    	}
    	
        return answer;
    }
}

public class Q25_실패율 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		
		System.out.println(Arrays.toString(sol.solution(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3})));
		System.out.println(Arrays.toString(sol.solution(4, new int[] {4, 4, 4, 4, 4})));
	}
	
}
