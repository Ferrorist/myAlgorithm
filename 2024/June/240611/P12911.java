/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12911
 * 소요 시간 : 약 2분
 */
class Solution {
    public int solution(int n) {
        int answer = n;
        int bitCount = Integer.bitCount(n);
        while(true){
            answer++;
            if(bitCount == Integer.bitCount(answer))    break;
        }
        
        return answer;
    }
}