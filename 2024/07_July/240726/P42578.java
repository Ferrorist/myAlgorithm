/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 * 소요 시간 : 13분 48초
 */
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] arr : clothes){
            String clothes_name = arr[0];
            String clothes_type = arr[1];
            
            map.put(clothes_type, map.getOrDefault(clothes_type, 0) + 1);
        }
        
        List<String> list = new ArrayList<>(map.keySet());
        for(String key : list){
            int count = map.get(key);
            answer *= (count+1);
        }
        
        answer -= 1;
        return answer;
    }
}