/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/43105
 * 소요 시간 : 11분 44초
 */
import java.util.Arrays;
class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        
        int[][] DP = new int[height][height];
        DP[0][0] = triangle[0][0];
        
        for(int i = 1; i < height; i++){
            DP[i][0] = DP[i-1][0] + triangle[i][0];
            for(int j = 1; j <= i; j++){
                DP[i][j] = Math.max(DP[i-1][j-1], DP[i-1][j]) + triangle[i][j];
            }
        }
        Arrays.sort(DP[height-1]);
        return DP[height-1][height-1];
    }
}