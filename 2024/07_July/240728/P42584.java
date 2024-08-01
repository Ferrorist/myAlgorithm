/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42584
 * 소요 시간 : 7분 20초
 */
import java.util.*;
class Solution {
    class Finance {
        int second;
        int price;
        
        public Finance(int second, int price){
            this.second = second;
            this.price = price;
        }
    }
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Finance> stack = new Stack<>();
        
        for(int i = 0; i < prices.length; i++){
            while(!stack.isEmpty() && stack.peek().price > prices[i]){
                Finance finance = stack.pop();
                answer[finance.second] = i - finance.second;
            }
            stack.push(new Finance(i, prices[i]));
        }
        
        while(!stack.isEmpty()){
            Finance finance = stack.pop();
            answer[finance.second] = prices.length - finance.second - 1;
        }
        
        return answer;
    }
}