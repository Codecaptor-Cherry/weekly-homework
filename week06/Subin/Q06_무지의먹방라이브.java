import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] food_times, long k) {
    	long time = 0;
    	PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]); // 음식 시간 기준 오름차순 
    	for (int i = 0; i < food_times.length; i++) {
    		time += food_times[i];
    		pq.add(new int[] {i + 1, food_times[i]}); // 음식 번호, 음식 시간
    	}
    	if (time <= k) return -1;
    	
    	time = 0; // 먹기 위해 사용한 시간
    	long previous = 0; // 직전에 다 먹은 음식 시간
    	long length = food_times.length; // 남은 음식의 개수

    	// (time + (현재 음식 시간 - 이전 음식 시간) * 현재 음식 개수)와 k 비교
    	while (time + (pq.peek()[1] - previous) * length <= k) { // 현재 음식을 다 먹을 수 있다면
    		int now = pq.poll()[1];
    		time += (now - previous) * length; // 전체 시간에 더하고
    		length -= 1; // 음식 제외
    		previous = now; // 직전 시간 갱신
    	}
    	
    	// 남은 음식 중에서 몇 번째 음식인지 확인하여 출력
    	List<int[]> list = new ArrayList<>();
    	while (!pq.isEmpty()) {
    		list.add(pq.poll());
    	}
    	Collections.sort(list, (i1, i2) -> i1[0] - i2[0]); // 음식 번호 기준 오름차순
        // K초 후의 음식 출력
        return list.get((int)((k - time) % length))[0];
    }
}

public class Q06_무지의먹방라이브 {
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		
		int[] food_times = {3, 1, 2};
		int k = 5;
		System.out.println(sol.solution(food_times, k));
		
		food_times = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
		k = 16;
		System.out.println(sol.solution(food_times, k));
		
		k = 27;
		System.out.println(sol.solution(food_times, k));
	}
	
}
