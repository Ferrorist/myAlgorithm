/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/1845
 * 소요 시간 : 3분 18초
 */
import java.util.*;
class Solution {
    // 연구실 N 마리 중 N/2마리 가져가도 좋음.
    public int solution(int[] nums) {
        int answer = nums.length / 2;
        
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
        
        answer = Math.min(set.size(), answer);
        return answer;
    }
}