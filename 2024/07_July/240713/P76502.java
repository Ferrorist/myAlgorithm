import java.util.*;
class Solution {
    // 1 <= s.length() <= 1,000
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++){
            String sentence = s.substring(i) + s.substring(0, i);
            Stack<Character> stack = new Stack<>();
            boolean isRight = true;
            for(int j = 0; j < sentence.length(); j++){
                if(!isRight) break;
                char c = sentence.charAt(j);
                if(stack.isEmpty() || c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                }
                else {
                    char peek = stack.peek();
                    if((peek == '[' && c == ']') || (peek == '{' && c == '}') || (peek == '(' && c == ')')) {
                        stack.pop();
                    }
                    else isRight = false;
                }
            }
            if(!stack.isEmpty()) isRight = false;
            if(isRight) answer++;
        }
        return answer;
    }
}