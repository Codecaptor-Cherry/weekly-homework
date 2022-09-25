package com.kim.algoStudy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class 무지의먹방라이브 {
    public static int solution(int[] food_times, long k) {
        long food_sum = 0;  // 모든 음식 먹는데 걸리는 총 시간
        for (int i = 0; i < food_times.length; i++) {
            food_sum += food_times[i];
        }

        if (food_sum <= k) return -1; //정답 못구할때
        PriorityQueue<Food> foods = new PriorityQueue<>(left); //먹는데 적게 걸리는 순으로 집어넣음
        for (int i = 0; i < food_times.length; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        long total = 0; // 먹기 위해 사용한 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length; // 남은 음식 개수

        while (total + ((foods.peek().time - previous) * length) <= k) { //(foods.peek().time - previous)직사각형 모양
            int now = foods.poll().time;
            total += (now - previous) * length;
            length -= 1;
            previous = now;
        }

        //직사각형 범위안이면. 이제 인덱스로 정렬해서 음식을 찾아낸다.
        ArrayList<Food> result = new ArrayList<>(foods);
        result.sort(idx);

        return result.get((int) ((k - total) % length)).idx;

    }

    static class Food {
        int idx;
        int time;

        Food(int idx, int quan) {
            this.idx = idx;
            this.time = quan;
        }
    }

    //처음에 쓸거
    static Comparator<Food> left = new Comparator<Food>() {
        @Override
        public int compare(Food o1, Food o2) {
            return o1.time - o2.time;
        }
    };

    //나중에. 인덱스별로 정렬후 몇번째 음식 먹는지 체크
    static Comparator<Food> idx = new Comparator<Food>() {
        @Override
        public int compare(Food o1, Food o2) {
            return o1.idx - o2.idx;
        }
    };
}