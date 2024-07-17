/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/87390
 * 소요 시간 : 13분 15초
 */
import java.util.*;
class Solution {
    // 1 <= n <= 10_000_000
    // 0 <= left <= right => 100_000_000_000_000 (100조)
    // right - left < 100_000
    public int[] solution(int n, long left, long right) {
        int size = (int)(right - left) + 1;
        int[] answer = new int[size];
        for(int i = 0; i < size; i++){
            long current = left + i;
            answer[i] = (int)Math.max( current / n, current % n) + 1;
        }
        
        return answer;
    }
}