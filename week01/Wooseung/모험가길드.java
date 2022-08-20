package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 모험가길드 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        
        int cnt = 0;    // 그룹에 참여 인원
        int group = 0;  // 그룹 개수

        for(int i = 0; i < N; i++) {    // 그룹 수의 최대값을 구해야 하기 때문에 공포도가 낮은 것부터 확인해야 한다.
            cnt++;  // 그룹 인원 추가
            if(cnt >= arr[i]){  // 그룹의 인원이 공포도 이상이면 그룹 생성
                group++;    // 그룹 수 증가
                cnt = 0;    // 그룹 인원 초기화
            }

        }
        System.out.println(group);

    }

}
