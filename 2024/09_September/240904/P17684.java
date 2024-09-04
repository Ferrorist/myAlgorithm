/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17684
 * 소요 시간 : 1시간 초과
 */
import java.util.*;
class Solution {
    // msg : 영문 대문자만 처리. ( 1 <= msg.length() <= 1_000 )
    public int[] solution(String msg) {
        Map<String, Integer> dict = initDict();
        System.out.println(dict.get("K"));
        List<Integer> answerList = LZW(dict, msg);
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answer.length; i++)  
            answer[i] = answerList.get(i).intValue();
        
        return answer;
    }
    
    public Map<String, Integer> initDict() {
        Map<String, Integer> dict = new HashMap<>();
        
        for(int i = 0; i < 26; i++){
            String key = Character.toString((char)('A' + i));
            dict.put(key, dict.size() + 1);
        }
        
        return dict;
    }
    
    public List<Integer> LZW(Map<String, Integer> dict, String input){
        List<Integer> returnList = new ArrayList<>();
        
        int length = input.length(), cursor = 0;
        while(cursor < length){
            StringBuilder sb = new StringBuilder();
            sb.append(input.charAt(cursor));
            while(cursor + 1 < length && 
                  dict.containsKey(sb.toString() + input.charAt(cursor+1))){
                sb.append(input.charAt(++cursor));
            }
            
            returnList.add(dict.get(sb.toString()));
            if(cursor + 1 < length){
                dict.put(sb.toString() + input.charAt(cursor+1), dict.size() + 1);
            }
            cursor += 1;
        }
    
        return returnList;
    }
}

