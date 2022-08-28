package codingtestproblem.p3.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int ans = 0;

        int[] home = new int[N];

        for(int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        int low = 1;    // 최소 거리값
        int high = home[N - 1] - home[0] + 1;   // 최대 거리값
        int dist = 0;

        while(low <= high) {

            int mid = (high + low)/2;   // 중간값 생성
            int start  = home[0];   // 처음 집에 설치
            int cnt = 1;    //  처음 집에 설치 했기 때문에 1 저장
            for(int i = 1; i < N; i++) {    // 순서대로 집을 방문하면서 설치
                dist = home[i] - start;
                if(dist >= mid) {   // 거리안에 들어오는지 체크
                    cnt++;  // 공유기 설치
                    start = home[i];    // 시작점을 공유기 새로 설치한 지점으로 변경
                }
            }

            if(cnt >= C) {
                ans = mid;
                low = mid + 1;  // 더 큰 거리에서 가능하지 확인
            }else {
                high = mid - 1; // 더 작은 거리에서 가능하지 확인
            }

        }

        System.out.println(ans);

    }

}
