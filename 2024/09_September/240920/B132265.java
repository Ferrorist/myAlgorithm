/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/132265
 * 소요 시간 : 21분 27초
 */
import java.util.*;
class Solution {
    Map<Integer, Integer> mapA, mapB;
    HashSet<Integer> setA, setB;
    public int solution(int[] topping) {        
        initArguments();
        Setting(mapA, setA, topping, 0, 2);
        Setting(mapB, setB, topping, 2, topping.length);
        
        return solve(topping);
    }
    
    private void initArguments() {
        mapA = new HashMap<Integer, Integer>();
        mapB = new HashMap<Integer, Integer>();
        
        setA = new HashSet<Integer>();
        setB = new HashSet<Integer>();
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
            if(setA.size() == setB.size())  answer++;
            if(idx < topping.length){
                int current = topping[idx++];
                mapA.put(current, mapA.getOrDefault(current, 0) + 1);
                setA.add(current);
                
                mapB.put(current, mapB.get(current) - 1);
                if(mapB.get(current) <= 0)   setB.remove(current);
            }
            else break;
        }
        
        return answer;
    }
}