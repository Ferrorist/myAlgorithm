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