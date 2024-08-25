import java.util.*;
class Solution {
    public int[] solution(String s) {              
        s = s.substring(1, s.length() - 1);
        String[] input = s.split("},");
        Map<Integer, Integer> map = new HashMap<>();
        
        int max_length = 0;
        for(String str : input) {    
            String[] nums = str.replace("{", "").replace("}", "").split(",");    
            for(int i = 0; i < nums.length; i++)    {
                int value = Integer.parseInt(nums[i]);
                map.put(value, map.getOrDefault(value, 0) + 1);   
            } 
            max_length = Math.max(max_length, nums.length);
        }
        
        int[] answer = new int[max_length];
        List<Integer> keySet = new ArrayList<>(map.keySet());
        
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        int idx = 0;
        for(Integer i : keySet){
            answer[idx++] = i;
        }
        
        
        return answer;
    }
}