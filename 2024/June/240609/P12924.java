/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12924
 * 소요 시간 : 8분 40초
 */
import java.util.*;
class Solution {
    public int solution(int n) {
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        int sum = 0;
        int current = 1;
        while(current <= n){
            if(sum < n){
                sum += current;
                queue.offer(current++);
            }
            else{
                if(sum == n)    answer++;
                sum -= queue.poll();
            }
        }
                
        return answer + 1;
    }
}