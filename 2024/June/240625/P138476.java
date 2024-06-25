/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/138476
 * 소요 시간 : 약 15분
 */
import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = tangerine.length;
        for(int i = 0; i < length; i++){
            int key = tangerine[i];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        
        int count = 0;
        for(Integer key : keySet){
            int value = map.get(key);
            if(value == 0)  continue;
            count++;
            k -= value;
            if(k <= 0)  break;
        }
        
        return count;
    }
}