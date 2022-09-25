package 그리디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * N개의 음식 : 1번 ~ N번
 * 각 음식을 섭취하는데 일정 시간 소요
 * 규칙1) 1번 음식부터 먹기 시작. 회전판은 번호가 증가하는 순서대로 음식을 무지 앞으로 !
 * 규칙2) 마지막 번호의 음식을 섭취한 후에는 다시 1번 음식
 * 규칙3) 1초 섭취 후 남은 음식은 그대로 두고, 다음 음식 섭취
 * 회전판이 오는데 걸리는 시간 xxx
 * 시작 K초 후에 방송 중지
 * 다시 이어갈 때, 몇 번 음식부터 섭취해야 하는지 구하는 함수
 * 만약 더 섭취해야 할 음식이 없다면 -1 반환
 */

class Food implements Comparable<Food>{
	int idx; // 음식 순서
	int time; // 걸리는 시간
	
	public Food(int idx, int time) {
		this.idx = idx;
		this.time = time;
	}

	@Override
	public int compareTo(Food o) {
		return this.time - o.time;
	}
	
	
}
public class PM_42891_무지의먹방라이브_문희주 {

	public static void main(String[] args) {
		// result : 1
		int[] food_times = new int[] {3,1,2}; // 각 음식을 모두 먹는데 필요한 시간
		long k = 5; // 방송이 중단된 시간
		int answer = 0;
		
		long sum = 0;  // 모든 음식 먹는데 걸리는 총 시간
        for (int i = 0; i < food_times.length; i++) {
            sum += food_times[i];
        }

       // if (sum <= k) return -1; // 문제 조건 : 더 먹을 음식이 없으면 -1 리턴
        if (sum <= k) answer = -1;

        // (음식 시간, 순서) 쌍으로 우선순위 큐에 저장
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(i + 1, food_times[i]));
        }

        long total = 0; // 지금까지 먹기 위해 사용한 총 시간 ~ 사이클 더해주기
        long previous = 0; // 직전에 다 먹은 음식 시간
        long len = food_times.length; // 남은 음식 개수
        
        // 사이클 : 최소양 * 음식 종류
        while (total + ((pq.peek().time - previous) * len) <= k) {
            int now = pq.poll().time;
            total += (now - previous) * len;
            len -= 1;
            previous = now;
        }

        ArrayList<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        Collections.sort(result, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return Integer.compare(o1.idx, o2.idx);
            }
        });

        //return result.get((int) ((k - total) % len)).idx;
        answer = result.get((int) ((k - total) % len)).idx;
        
        System.out.println(answer);
	}
}


