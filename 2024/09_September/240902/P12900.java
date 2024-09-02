/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12900
 */
class Solution {
    final int MOD = 1_000_000_007;
    public int solution(int n) {
        int[] DP = new int[n+1];
        DP[0] = 1;  DP[1] = 1;
        
        for(int i = 2; i <= n; i++) DP[i] = (DP[i-2] + DP[i-1] ) % MOD;
        return DP[n];
    }
}