/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42577
 * 소요 시간 : 53분 57초 (힌트 확인 함)
 */
import java.util.*;
class Solution {
    // 1 <= phone_book.length <= 1_000_000 
    // 1 <= phone_book[i].length() <= 20
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        for(int i = 0; i < phone_book.length - 1; i++){
            String str1 = phone_book[i];
            
            if(phone_book[i+1].startsWith(str1))  return false;
        }
        
        return true;
    }
}