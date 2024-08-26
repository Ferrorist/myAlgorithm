/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17677
 * 소요 시간 : 52분 58초
 */
import java.util.*;
class Solution {
    static int MUL = 65536;
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toUpperCase();  str2 = str2.toUpperCase();
        
        Map<String, Integer> str1_hashMap = createHashMap(str1);
        Map<String, Integer> str2_hashMap = createHashMap(str2);
        
        if(str1_hashMap.isEmpty() && str2_hashMap.isEmpty())    return MUL;
        
        int 합집합_count = 합집합(str1_hashMap, str2_hashMap);
        int 교집합_count = 교집합(str1_hashMap, str2_hashMap);
        
        answer = (int)((double) 교집합_count / 합집합_count * MUL);
        return answer;
    }
    
    static int 합집합(Map<String, Integer> str1Map, Map<String, Integer> str2Map){
        Map<String, Integer> mixMap = new HashMap<>();
        int count = 0;
        
        for(String key : str1Map.keySet()){
            mixMap.put(key, Math.max(mixMap.getOrDefault(key, 0), str1Map.get(key)));
        }
        
        for(String key : str2Map.keySet()){
            mixMap.put(key, Math.max(mixMap.getOrDefault(key, 0), str2Map.get(key)));
        }
        
        for(String key : mixMap.keySet()){
            count += mixMap.get(key);
        }
        return count;
    }
    
    static int 교집합(Map<String, Integer> str1Map, Map<String, Integer> str2Map){
        if(str1Map.size() < str2Map.size()) return 교집합(str2Map, str1Map);
        
        int count = 0;
        for(String key : str1Map.keySet()){
            if(str2Map.containsKey(key)){
                count += Math.min(str1Map.get(key), str2Map.get(key));
            }
        }
        
        return count;
    }
    
    static Map<String, Integer> createHashMap(String str){
        Map<String, Integer> map = new HashMap<>();
        String current = "";
        int idx = 0;
        
        while(true) {
            if(isAlpha(str.charAt(idx))){
                current += str.charAt(idx++);
                break;
            }
            else idx++;
        }
        
        for(int i = idx; i < str.length(); i++){
            current += str.charAt(i);
            if(isAlpha(current.charAt(0)) && isAlpha(current.charAt(1)))
                map.put(current, map.getOrDefault(current, 0) + 1);
            current = current.substring(1);
        }
        
        return map;
    }
    
    static boolean isAlpha(char c){
        return 'A' <= c && c <= 'Z';
    }
}