/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42587
 */
import java.util.*;
class Solution {
    private class Process {
        int priority;
        int location;
        
        Process(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> priorityQueue = initQueue(priorities);
        Queue<Process> processQueue = initProcessQueue(priorities);
        
        
        while(!processQueue.isEmpty()) {
            Process current = processQueue.poll();
            
            if(priorityQueue.peek() > current.priority) processQueue.offer(current);
            else {
                priorityQueue.poll();
                answer++;
                if(current.location == location) return answer;
            }
        }
        return -1;
    }
    
    private Queue<Integer> initQueue(int[] arr){
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int a : arr)    queue.offer(a);
        
        return queue;
    }
    
    private Queue<Process> initProcessQueue(int[] arr){
        Queue<Process> queue = new ArrayDeque<>();
        for(int i = 0; i < arr.length; i++)
            queue.offer(new Process(arr[i], i));
        
        return queue;
    }
    
    
}