/*
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/43165
 * 소요 시간 : 4분 47초 (제출시간 포함)
 */

 import java.util.*;
 class Solution {
     public int solution(int[] numbers, int target) {
         int answer = 0;
         Queue<Integer> queue = new ArrayDeque<>();
         queue.offer(0);
         
         for(int i = 0; i < numbers.length; i++){
             int size = queue.size();
             for(int q = 0; q < size; q++){
                 int value = queue.poll();
                 queue.offer(value + numbers[i]);
                 queue.offer(value - numbers[i]);
             }
         }
         
         while(!queue.isEmpty()){
             int value = queue.poll();
             if(value == target) answer++;
         }
         
         return answer;
     }
 }