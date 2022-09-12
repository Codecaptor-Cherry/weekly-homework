package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//연산자 개수만큼 DFS돌리기. 끝에 도달하면 -> MAX MIN 체크
public class 연산자끼워넣기 {
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] operators = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());

        //숫자 입력받기
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 개수 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        int n = nums[0];
        dfs(1,nums,operators,n);
        System.out.println(MAX);
        System.out.println(MIN);




    }

    static void dfs(int idx, int[] nums, int[] operators,int sum) {
        if(idx == N){
            MAX = Math.max(sum, MAX);
            MIN = Math.min(sum, MIN);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operators[i] != 0) {
                int[] tmp = operators.clone();
                tmp[i]--;
                if (i == 0) {
                    dfs(idx + 1, nums, tmp, sum + nums[idx]);
                } else if (i == 1) {
                    dfs(idx + 1, nums, tmp, sum - nums[idx]);
                }else if (i == 2) {
                    dfs(idx + 1, nums, tmp, sum * nums[idx]);
                }else {
                    dfs(idx + 1, nums, tmp, sum / Math.abs(nums[idx]));
                }
            }
        }


    }
}
