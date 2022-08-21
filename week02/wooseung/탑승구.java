package codingtestproblem.p3.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 탑승구 {

    static int g, p;
    static int[] gate;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        gate = new int[g + 1];

        for(int i = 1; i <= g; i++) {
            gate[i] = i;
        }

        int ans = 0;
        for(int i = 0; i < p; i++) {

            int num = Integer.parseInt(br.readLine());
            int root = findGate(num);

            if(root == 0) { // 도킹할 수 있는 탑승구가 없으며로 종료
                break;
            }

            unionGate(root, root - 1);   // 도킹할 수 있다면 union연산 실행    
            ans++;  // 비행기가 도킹했으므로 값 증가
        }
        System.out.println(ans);
    }

    static int findGate(int x) {
        if(x == gate[x]) {
            return x;
        }
        return gate[x] = findGate(gate[x]);
    }

    static void unionGate(int a, int b) {   
        a = findGate(a);
        b = findGate(b);
        if(a < b) {
            gate[b] = a;
        }else {
            gate[a] = b;
        }
    }

}
