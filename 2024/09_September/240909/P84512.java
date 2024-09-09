/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/84512
 * 소요 시간 : 13분 8초
 */
import java.util.*;
class Solution {
    final String[] vowels = {"A", "E", "I", "O", "U"};
    final int MAX_LENGTH = 5; // 사전에 있는 최대 문자열 길이
    final List<String> dictionary = new ArrayList<>();
    public int solution(String word) {
        if(dictionary.size() <= 0)  initDictionary("");
        
        int idx = dictionary.indexOf(word);
        
        if(idx < 0) return -1;
        else return idx;
    }
    
    private void initDictionary(String current) {
        dictionary.add(current);
        if(current.length() == MAX_LENGTH)    return;
        
        for(int i = 0; i < vowels.length; i++){
            initDictionary(current + vowels[i]);
        }
    }
}