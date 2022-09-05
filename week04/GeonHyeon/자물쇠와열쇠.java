package com.kim.algoStudy;


//제출은 Solution으로
public class 자물쇠와열쇠 {
    static int m;
    static int n;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        m = key.length;
        n = lock.length;

        int len = n+m*2-2;
        int[][] map = new int[len][len];    // 확장시킨 배열

        // 확장시킨 배열에 Lock 표시
        for(int i=m-1; i<m+n-1 ; i++){
            for(int j=m-1; j<m+n-1; j++){
                map[i][j] = lock[i-(m-1)][j-(m-1)];
            }
        }

        //돌려서 4번조사
        for(int pos=0; pos<4; pos++){
            if(check(map, key, n)){
                return true;
            }
            rotate(key); // 시계방향 90도 회전
        }


        return false;
    }

    //들어가는지 쳌
    public static boolean check(int[][] map, int[][] key, int lockLen){
        int keyLen = key.length;
        int mapLen = map.length;
        for(int i=0; i<mapLen-keyLen+1; i++){
            for(int j=0; j<mapLen-keyLen+1; j++){

                //합연산
                for(int k=0; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        map[i+k][j+l] += key[k][l];
                    }
                }

                // 전부 1이면 성공
                boolean flag = true;
                for(int k=keyLen-1; k<keyLen+lockLen-1; k++){
                    for(int l=keyLen-1; l<keyLen+lockLen-1; l++){
                        if(map[k][l] != 1){ // 1이 아니면 홈이 안 맞는 것임
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }

                if(flag) return true;   // 전부 1이였다면 true

                //복구
                for(int k=0; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        map[i+k][j+l] -= key[k][l];
                    }
                }

            }
        }

        return false;
    }

    //90도 돌리기
    public static void rotate(int[][] key){
        int len = key.length;
        int[][] temp = new int[len][len];

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                temp[i][j] = key[len-j-1][i];
            }
        }

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                key[i][j] = temp[i][j];
            }
        }

    }

}
