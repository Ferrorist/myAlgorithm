import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works){
            queue.offer(work);
        }        
        while(n > 0 && !queue.isEmpty()){
            int work = queue.poll();
            if(work <= 0)   continue;
            queue.offer(work - 1);  n--;
        }
        
        while(!queue.isEmpty()){
            long work = queue.poll();
            answer += work * work;
        }
        
        return answer;
    }
}