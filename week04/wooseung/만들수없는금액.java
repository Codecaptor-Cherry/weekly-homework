package codingtestproblem.p3.day0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 만들수없는금액 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int []coin = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(coin);  // 동전을 오름차순으로 정렬

        int cost = 1;   // 찾아야하는 최솟값을 1로 설정
        for(int i = 0; i < N; i++) {    // 동전을 하나씩 뽑는다
            if(cost < coin[i]) {    // 만약 뽑은 동전의 값이 만들어야하는 최솟값보다 크다면 값을 만들 수 없기 때문에 반복문 종료
                break;
            }
            cost += coin[i];    // 최솟값에 동전의 값을 더 한다.
        }                       // 최솟값 - 1까지 동전으로 만들 수 있는 값이다.
       System.out.println(cost);
    }
}
