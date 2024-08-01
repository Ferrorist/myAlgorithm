/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/142085
 * 소요 시간 : 1시간 초과
 */
import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        if(k >= enemy.length)   return enemy.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        int sum = 0;
        
        for(int i = 0; i < enemy.length; i++){
            if(k > queue.size()){
                queue.offer(enemy[i]);  continue;
            }
            queue.offer(enemy[i]);
            sum += queue.poll();
            if(sum > n){
                return i;
            }
        }
        
        return enemy.length;
    }
}