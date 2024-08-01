/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12941
 * 소요 시간 : 16분 03초 99
 */
import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int length = A.length;
        int index_A = length - 1, index_B = 0;
        for(int i = 0; i < length; i++){
            answer += A[index_A--] * B[index_B++];
        }

        return answer;
    }
}