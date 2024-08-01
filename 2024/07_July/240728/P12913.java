/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12913
 * 소요 시간 : 6분 40초
 */
class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int[][] DP = new int[land.length + 1][land[0].length];
        for(int i = 0; i < land[0].length; i++) DP[1][i] = land[0][i];
        
        for(int i = 2; i < land.length + 1; i++){
            for(int j = 0; j < land[0].length; j++){
                DP[i][j] = 
                    land[i-1][j] + 
                    Math.max(DP[i-1][(j+1) % 4], Math.max(DP[i-1][(j+2) % 4], DP[i-1][(j+3) % 4]));
            }
        }
        
        for(int i = 0; i < land[0].length; i++) answer = Math.max(answer, DP[land.length][i]);
        return answer;
    }
}