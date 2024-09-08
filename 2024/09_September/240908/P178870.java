/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/178870
 * 소요 시간 : 31분 4초
 * 괜히 BinarySearch lowerbound 한다고 뻘짓한 문제;;
 */
import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
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
    
    private int[] getSum(int[] sequence){
        int[] arr = new int[sequence.length + 1];
        for(int i = 0; i < sequence.length; i++)    arr[i+1] = arr[i] + sequence[i];
        return arr;
    }
}