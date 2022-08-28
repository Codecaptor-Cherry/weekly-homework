package 정렬;

import java.io.IOException;
import java.util.Arrays;

// 실패율 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어의 수
// N : 전체 스테이지의 개수
// int[] stages : 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열
// 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열 return
// 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 한다.
// 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0으로 정의

class Stage implements Comparable<Stage>{
	int stage, user, stay;
	double fail;
	
	public Stage(int stage, int user, int stay) {
		this.stage = stage; // 현재 스테이지 번호
		this.user = user; // 스테이지에 도달한 플레이어의 수
		this.stay = stay; // 아직 클리어하지 못한 플레이어의 수
	}
	
	public void getFail() {
		// 예외 처리 : i번째 스테이지에 한 명도 도달하지 못한 경우
		// user = 0 ~ divided by zero 발생 ~ this.fail = 0으로 설정
		if(this.user == 0) this.fail = 0;
		else this.fail = 1.0 * stay / user;
	}
	
	// 1. 실패율 내림차순
	// 2. 동일 실패율이면 스테이지 번호 오름차순
	@Override
	public int compareTo(Stage o) {
		if(this.fail == o.fail) return this.stage - o.stage;
		return (this.fail - o.fail < 0 ? 1 : -1);
	}
	
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int p = stages.length; // 사용자의 수
        
        // index번째 스테이지에 머물고 있는 플레이어의 수
        // N+1번은 마지막 스테이지까지 클리어한 사용자
        int[] persons = new int[N+1];
        for(int i = 0; i < p; i++) {
        	persons[stages[i]-1]++;
        }
        
        // index번째 스테이지에 도달한 플레이어의 수(클리어한 사용자 포함)
        // dp[i] = dp[i-1] - persons[i-1];
        // 이전 스테이지에 도달한 사용자 - 실패한 사용자 = 클리어한 사용자 = 다음 스테이지로 이동
        int[] dp = new int[N+1];
        dp[0] = p;
        for(int i = 1; i < N+1; i++) {
        	dp[i] = dp[i-1] - persons[i-1];
        }
        
        // 사용자 수 입력 및 실패율 구하기
        Stage[] array = new Stage[N];
        for(int i = 0; i < N+1; i++) {
        	array[i] = new Stage(i, dp[i], persons[i]);
        	array[i].getFail();
        }
        
        Arrays.sort(array); // Comparable을 통해 정렬
        for(int i = 0; i < N; i++) {
        	answer[i] = array[i].stage+1; // answer 배열 입력
        }
      
        
        return answer;
    }
}

// 테스트 ----------------------------------------------------------------------
public class PM_42889_실패율_문희주 {

	public static void main(String[] args) throws IOException {
		int N = 5;
		int[] answer = new int[N];
		int[] stages = {3,3,3,3,3};
		
		int p = stages.length; // 사용자의 수
        Arrays.sort(stages); // 사용자 스테이지 오름차순 정렬
        
        // index번째 스테이지에 머물고 있는 플레이어의 수
        // N+1번은 마지막 스테이지까지 클리어한 사용자
        int[] persons = new int[N+1];
        for(int i = 0; i < p; i++) {
        	persons[stages[i]-1]++;
        }
        
        int[] dp = new int[N+1];
        dp[0] = p;
        for(int i = 1; i < N+1; i++) {
        	dp[i] = dp[i-1] - persons[i-1];
        }

        Stage[] array = new Stage[N];
        for(int i = 0; i < N; i++) {
        	array[i] = new Stage(i, dp[i], persons[i]);
        	array[i].getFail();
        }
        for(int i = 0; i< array.length; i++) {
        	System.out.println(array[i].stage + " : " + array[i].fail + " ");
        }
        Arrays.sort(array);
//        for(int i = 0; i< array.length; i++) {
//        	System.out.println(array[i].stage + " : " + array[i].fail + " ");
//        }
        for(int i = 0; i < N; i++) {
        	answer[i] = array[i].stage+1;
        }
        
        System.out.println(Arrays.toString(answer));
	}
}
