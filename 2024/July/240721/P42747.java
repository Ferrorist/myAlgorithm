/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 */
import java.util.*;
class Solution {
    // n 편중, h번 이상 인용된 논문이 h편,
    // 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 H-Index 
    public int solution(int[] citations) {
        int size = citations.length;
        Arrays.sort(citations);
        int max_value = citations[size-1];
        
        int answer = 0;
        
        for(int i = 0; i <= max_value; i++){
            int low = lowerbound(citations, i);
            int count = size - low;
            if(i <= count) answer = Math.max(i, answer);
        }
        
        return answer;
    }
    
    // target 번 이상 인용된 논문의 개수
    int lowerbound(int[] arr, int target){
        int start = 0, end = arr.length;
        
        while(start < end){
            int mid = (start + end) >>> 1;
            
            if(arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        
        return end;
    }
}