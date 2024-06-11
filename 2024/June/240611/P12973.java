/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12973
 * 소요 시간: 19분 16초 08
 */
import java.util.*;
class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        int index = 0, length = s.length();
        
        while(index < length){
            stack.push(s.charAt(index++));
            while(stack.size() >= 2){
                    char c = stack.pop();
                    if(stack.peek() == c)   stack.pop();
                    else {
                        stack.push(c);
                        break;
                    }
            }
        }
        
        if(stack.isEmpty()) return 1;
        return 0;
    }
}