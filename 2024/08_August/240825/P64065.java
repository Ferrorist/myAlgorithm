/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/64065
 * 소요 시간 : 15분 12초
 */
import java.util.*;
class Solution {
    public int[] solution(String s) {              
        String[] input = s.substring(1, s.length() - 1).split("},");
        Map<Integer, Integer> map = new HashMap<>();
        
        for(String str : input) {    
            String[] nums = str.replace("{", "").replace("}", "").split(",");    
            for(int i = 0; i < nums.length; i++)    {
                int value = Integer.parseInt(nums[i]);
                map.put(value, map.getOrDefault(value, 0) + 1);   
            } 
        }
        
        int[] answer = new int[map.size()];
        List<Integer> keySet = new ArrayList<>(map.keySet());
        
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        int idx = 0;
        for(Integer i : keySet){
            answer[idx++] = i;
        }
    
        return answer;
    }
}