/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/131701
 * 소요 시간 : 약 14분
 */
import java.util.*;
class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        int[] DP = new int[elements.length * 2 + 1];
        
        // DP 생성
        for(int i = 1; i < DP.length; i++)
            DP[i] = DP[i-1] + elements[(i-1) % elements.length];
        
        // 연속 부분 수열 합 구하기
        for(int i = 1; i < elements.length; i++){
            for(int j = 0; j < elements.length; j++){
                int value = DP[i + j] - DP[j];
                set.add(value);
            }
        }
        set.add(DP[elements.length]);
        
        return set.size();
    }
}