/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17680
 * 소요 시간 : 20분 40초
 */
import java.util.*;
class Solution {
    final int HIT = 1, MISS = 5;
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize <= 0)  return MISS * cities.length;
        
        int answer = 0;
        LinkedList<String> list = new LinkedList<>();
        for(String city : cities){
            city = city.toUpperCase();
            if(list.contains(city)) {
                list.remove(city);  answer += HIT;
            }
            else answer += MISS;
            
            list.add(city);
            if(list.size() > cacheSize) list.removeFirst();
        }
        
        
        return answer;
    }
}