package com.ssafy.cfj.greedy;

import java.util.*;

public class LiveShow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	class Solution {
	    public class Food implements Comparable<Food> {
	        int number;
	        int volume;
	        
	        public Food(int number, int volume) {
	            this.number = number;
	            this.volume = volume;
	        }
	        
	        @Override
	        public int compareTo(Food other) {
	            return Integer.compare(this.volume, other.volume);
	        }
	    }
	    
	    public int solution(int[] food_times, long k) {
	        PriorityQueue<Food> pq = new PriorityQueue<Food>();
	        
	        long total = 0;
	        for (int i = 0; i < food_times.length; i++) {
	            pq.offer(new Food(i + 1, food_times[i]));
	            total += food_times[i];
	        }
	        
	        if (total <= k) return -1;
	        
	        long previous = 0;
	        long summary = 0;
	        while (true) {
	            Food food = pq.peek();
	            int len = pq.size();
	            int volume = food.volume;
	            
	            if (summary + (volume - previous) * len <= k) {
	                pq.poll();
	                summary += (volume - previous) * len;
	                previous = volume;
	            } else {
	                break;
	            }
	        }    
	        
	        ArrayList<Food> array = new ArrayList<>();
	        while(!pq.isEmpty()) {
	            array.add(pq.poll());
	        }
	        
	        Collections.sort(array, new Comparator<Food>() {
	            @Override
	            public int compare(Food o1, Food o2) {
	                return Integer.compare(o1.number, o2.number);
	            }
	        });
	        
	        int answer = array.get((int)((k - summary) % array.size())).number;
	        return answer;
	    }
	}

}

