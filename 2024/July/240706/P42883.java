/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 * 소요 시간 : 24분 43초 (힌트 봤음)
 */
import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < number.length(); i++){
            int n = number.charAt(i) - '0';
            while(!stack.isEmpty() && stack.peek() < n && k > 0) {
                stack.pop(); k--;
            }
            stack.push(n);
        }
        
        while(k > 0){
            stack.pop(); k--;
        }

        while(!stack.isEmpty()) sb.append(stack.pop());
        
        return sb.reverse().toString();
    }
}