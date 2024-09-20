/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/132265
 * 소요 시간 : 21분 27초
 */
import java.util.*;
class Solution {
    private Map<Integer, Integer>[] map;
    private HashSet<Integer>[] set;

    public int solution(int[] topping) {        
        initArguments();
        Setting(map[0], set[0], topping, 0, 2);
        Setting(map[1], set[1], topping, 2, topping.length);
        
        return solve(topping);
    }
    
    private void initArguments() {
        map = new HashMap[] {new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>()};
        set = new HashSet[] {new HashSet<>(), new HashSet<>()};
    }
    
    private void Setting(Map<Integer, Integer> map, HashSet<Integer> set, int[] topping, int start, int end){
        for(int i = start; i < end; i++){
            map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);
            set.add(topping[i]);
        }
    }
    
    private int solve(int[] topping) {
        int answer = 0;
        int idx = 2;
        
        while(true){
            if(set[0].size() == set[1].size())  answer++;
            if(idx < topping.length){
                int current = topping[idx++];
                map[0].put(current, map[0].getOrDefault(current, 0) + 1);
                set[0].add(current);
                
                map[1].put(current, map[1].get(current) - 1);
                if(map[1].get(current) <= 0)   set[1].remove(current);
            }
            else break;
        }
        
        return answer;
    }
}