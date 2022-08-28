import java.util.*;

class Node implements Comparable<Node> {

    int stage;
    double fail;

    public Node(int stage, double fail) {
        this.stage = stage;
        this.fail = fail;
    }

    @Override
    public int compareTo(Node other) {
        if (this.fail == other.fail) {  // 실패율이 같다면 스테이지번호 오름차순 
            return Integer.compare(this.stage, other.stage);
        }
        return Double.compare(other.fail, this.fail); // 실패율을 기준으로 내림차순
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        ArrayList<Node> arrayList = new ArrayList<>();
        int length = stages.length;

        // 각 스테이지마다 머물러있는 사람의 수 계산
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 0; j < stages.length; j++) {
                if (stages[j] == i) {
                    cnt += 1;
                }
            }

            // 실패율 계산
            double fail = 0;
            if (length >= 1) {
                fail = (double) cnt / length;
            }

            // 리스트에 (스테이지 번호, 실패율) 원소 삽입
            arrayList.add(new Node(i, fail));
            length -= cnt;
        }

        // 실패율을 기준으로 각 스테이지를 내림차순 정렬
        Collections.sort(arrayList);

        // 정렬된 스테이지 번호 반환
        for (int i = 0; i < N; i++) {
            answer[i] = arrayList.get(i).stage;
        }
        return answer;
    }
}
