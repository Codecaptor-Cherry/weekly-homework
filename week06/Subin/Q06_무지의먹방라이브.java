import java.util.PriorityQueue;

class Solution {
    public long solution(int[] food_times, long k) {
    	
    	long time = 0;
    	PriorityQueue<int[]> fpq = new PriorityQueue<>((i1, i2) -> i1[1] == i2[1] ? i2[0] - i1[0] : i2[1] - i1[1]);
    	for (int i = 0; i < food_times.length; i++) {
    		time += food_times[i];
    		fpq.add(new int[] {i + 1, food_times[i]});
    	}
    	
    	if (time <= k) return -1;
    	
    	int[] cur = {0, 0};
    	while (!fpq.isEmpty()) {
    		cur = fpq.poll();
    		
    		if (--time == k) break ;
    		
    		if (--cur[1] > 0) { 
    			fpq.add(new int[] {cur[0], cur[1]});
    		}
        }
        
        return cur[0];
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
