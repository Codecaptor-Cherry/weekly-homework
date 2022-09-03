package com.ssafy.cfj.implemantation;

class LockAndKey {
    public boolean solution(int[][] key, int[][] lock) {        
        int lenOfKey = key.length;
        int lenOfLock = lock.length;
        
        int[][] newLock = new int[lenOfLock * 3][lenOfLock * 3];
        for (int row = 0; row < lenOfLock; row++) {
            for (int col = 0; col < lenOfLock; col++) {
                newLock[row + lenOfLock][col + lenOfLock] = lock[row][col];
            }
        } 
        
        int[][] rotatedKey = key;
        for (int d = 0; d < 4; d++) {
            // rotate key
            rotatedKey = rotate(rotatedKey);
            
            // search
            for (int startRow = 0; startRow < lenOfLock * 2; startRow++) {
                for (int startCol = 0; startCol < lenOfLock * 2; startCol++) {
                    
                    // check
                    for (int row = 0; row < lenOfKey; row++) {
                        for (int col = 0; col < lenOfKey; col++) {
                            newLock[startRow + row][startCol + col] += rotatedKey[row][col];
                        }
                    }
                    
                    // check if find
                    if (checkFind(newLock)) return true;
                    
                    // uncheck
                    for (int row = 0; row < lenOfKey; row++) {
                        for (int col = 0; col < lenOfKey; col++) {
                            newLock[startRow + row][startCol + col] -= rotatedKey[row][col];
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean checkFind(int[][] newLock) {
        int len = newLock.length;
        for (int row = len / 3; row < len * 2 / 3; row++) {
            for (int col = len / 3; col < len * 2 / 3; col++) {
                if (newLock[row][col] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public int[][] rotate(int[][] key) {
        int len = key.length;
        int[][] copy = new int[len][len];

        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                copy[col][len - row - 1] = key[row][col];
            }
        }
        
        return copy;
    }
    
    public static void print(int[][] key) {
        int len = key.length;
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                System.out.print(key[row][col] + " ");
            }
            System.out.println();
        }
    }
}


