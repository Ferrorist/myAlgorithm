/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12951
 * 소요 시간 : 13분 51초 52
 */
class Solution {
    public String solution(String s) {
        boolean upper = true;
        
        char[] sentence = s.toCharArray();
        int length = s.length();
        int changeValue = 'a' - 'A';    
        
        for(int i = 0; i < length; i++){
            if(sentence[i] == ' ') {
                upper = true;
                continue;
            }
            
            if(sentence[i] >= (int)'A' && sentence[i] <= (int)'z'){
                if(upper && sentence[i] >= 'a'){
                    sentence[i] -= changeValue;
                }
                else if(!upper && sentence[i] >= 'A' && sentence[i] < 'a'){
                    sentence[i] += changeValue;
                }
            }
            upper = false;
        }
        
        return String.valueOf(sentence);
    }
}