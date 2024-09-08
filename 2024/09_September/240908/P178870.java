/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/178870
 * 소요 시간 : 31분 4초
 */
import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];      
        if(Arrays.binarySearch(sequence, k) >= 0) {
            int idx = lowerBound(sequence, k);
            answer[0] = answer[1] = idx;
            return answer;
        }
        
        int[] DP = getSum(sequence);
        int start = 0, end = 1, length = Integer.MAX_VALUE;
        while(start < DP.length && end < DP.length){
            int sum = DP[end] - DP[start];
            if(sum == k && length > end - start){
                length = end - start;
                answer[0] = start;  answer[1] = end - 1;
            }

            if(sum <= k) end++;
            else start++;
        }
        
        return answer;
    }
    
    private int lowerBound(int[] sequence, int k){
        int start = 0, end = sequence.length;
        while(start < end){
            int mid = (start + end) >>> 1;
            
            if(sequence[mid] >= k)  end = mid;
            else start = mid+1;
        }
        
        return end;
    }
    
    
    private int[] getSum(int[] sequence){
        int[] arr = new int[sequence.length + 1];
        for(int i = 0; i < sequence.length; i++)    arr[i+1] = arr[i] + sequence[i];
        return arr;
    }
}