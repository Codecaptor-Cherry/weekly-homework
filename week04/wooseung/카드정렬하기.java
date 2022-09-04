package codingtestproblem.p3.day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 우선순위큐 생성
        int N = Integer.parseInt(br.readLine());    // 카드 묶음 개수
        int ans = 0;    // 최소 비교 횟수

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            pq.add(num);
        }
        // 최소 비교 횟수를 구하기 위해서 큐에 가장 작은 값 두 개를 더 해야한다.
        // 우선순위 큐에서 두개를 추출한뒤 더 한 값을 최소 비교 횟수에 더 해주고
        // 더 한 값을 우선순위 큐에 추가한다.
        // 0부터 N - 1개 까지 한다면 모든 묶음을 합쳤기 때문에 ans값을 출력한다.
        for(int i = 0; i < N - 1; i++){
            int a = pq.poll();
            int b = pq.poll();

            ans += a + b;
            pq.add(a+b);
        }
        System.out.println(ans);
    }

}
