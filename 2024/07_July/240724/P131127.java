/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/131127
 * 소요 시간 : 22분 22초
 */
import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        String[] products = new String[want.length];
        System.arraycopy(want, 0, products, 0, want.length);
        Arrays.sort(products);
                
        int[] targets = new int[number.length];
        for(int i = 0; i < want.length; i++){
            int idx = Arrays.binarySearch(products, want[i]);
            targets[idx] = number[i];
        }
        
        String hash = "";
        for(int target : targets)   hash += target + "";
        
        Queue<String> queue = new ArrayDeque<>();
        int[] current = new int[number.length];
        for(String product : discount) {
            queue.offer(product);
            int input_idx = Arrays.binarySearch(products, product);
            if(input_idx >= 0)  current[input_idx]++;
            
            if(queue.size() > 10) {
                String output = queue.poll();
                int output_idx = Arrays.binarySearch(products, output);
                if(output_idx >= 0) current[output_idx] = Math.max(current[output_idx] - 1, 0);
            }
            
            String result = "";
            for(int count : current)    result += count + "";
            if(hash.equals(result)) answer++;
        }
        
        return answer;
    }
}