/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 소요 시간 : 8분 28초
 */
import java.util.*;
class Solution {
    class Program {
        int progress;
        int speed;
        
        public Program(int progress, int speed){
            this.progress = progress;
            this.speed = speed;
        }
    }
    
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Program> queue = new ArrayDeque<>();
        for(int i = 0; i < progresses.length; i++){
            queue.offer(new Program(progresses[i], speeds[i]));
        }
        
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            boolean published = false;
            int size = queue.size();
            Program peek = queue.peek();
            
            if(peek.progress + peek.speed >= 100)   published = true;
            int count = 0; // 배포 가능한 program 개수
            for(int i = 0; i < size; i++){
                Program program = queue.poll();
                program.progress += program.speed;
                if(published){
                    if(program.progress >= 100) count++;
                    else {
                        published = false;
                        queue.offer(program);
                    }
                }
                else queue.offer(program);
            }
            
            if(count > 0)   list.add(count);
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        
        return answer;
    }
}