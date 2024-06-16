/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12981
 * 소요 시간 : 12분 05초 09
 */
import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        HashMap<String, Integer> map = new HashMap<>();
        map.put(words[0], 0);
        
        for(int i = 1; i < words.length; i++){
            if(map.containsKey(words[i]) || words[i-1].charAt(words[i-1].length() - 1) != words[i].charAt(0)){
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
            map.put(words[i], i);
        }
        return answer;
    }
}