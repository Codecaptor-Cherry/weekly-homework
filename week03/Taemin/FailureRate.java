package com.ssafy.cfj.sort;

import java.util.ArrayList;
import java.util.Collections;

public class FailureRate {

	public static void main(String[] args) {
		

	}

}


class Stage implements Comparable<Stage> {
    int no;
    double posibility;
    
    public Stage(int no, double posibility) {
        this.no = no;
        this.posibility = posibility;
    }
    
    @Override
    public int compareTo(Stage other) {
        if (this.posibility == other.posibility) {
            return Integer.compare(this.no, other.no);
        }
        
        return Double.compare(other.posibility, this.posibility);
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        ArrayList<Stage> array = new ArrayList<Stage>();
        int[] pass = new int[N + 2];
        int[] complete = new int[N + 2];
        
        for (int i = 0; i < stages.length; i++) {
            int p = stages[i];
            
            for (int j = 1; j < p; j++) {
                pass[j] += 1;
                complete[j] += 1;
            }
            pass[p]++;
        }
        
        for (int i = 1; i <= N; i++) {
            int numOfPass = pass[i];
            int numOfFail = numOfPass - complete[i];
            
            if (numOfPass == 0) {
                array.add(new Stage(i, 0));
                continue;
            }
            
            array.add(new Stage(i, (double) numOfFail / (double) numOfPass));
        }
        
        Collections.sort(array);
        int[] answer = new int[array.size()];
        int idx = 0;
        for (Stage stage : array) {
            answer[idx++] = stage.no;
        }

        return answer;
    }
}