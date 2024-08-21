/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17680
 * 소요 시간 : 37분 13초
 */
import java.util.*;

class Solution {
    static final int HIT = 1, MISS = 5;
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize <= 0) return MISS * cities.length;

        Map<String, String> cache = new LinkedHashMap<String, String>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };

        int answer = 0;

        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.containsKey(city)) {
                answer += HIT;
            } else {
                answer += MISS;
            }
            cache.put(city, city);
        }

        return answer;
    }
}
