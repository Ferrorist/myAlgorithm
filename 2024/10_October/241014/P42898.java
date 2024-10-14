/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42898
 */
import java.util.*;
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int PUDDLE = 3;
    private static int[][] map;
    private static final int[][] dir = {{0, -1}, {-1, 0}};
    public int solution(int m, int n, int[][] puddles) {
        initMap(m, n, puddles);
        int[][] DP = new int[m+1][n+1];
        DP[1][1] = 1;
        for(int y = 1; y <= m; y++){
            for(int x = 1; x <= n; x++){
                if(map[y][x] == PUDDLE) continue;
                for(int d = 0; d < dir.length; d++) {
                    int dy = y + dir[d][0];
                    int dx = x + dir[d][1];
                    if(dy <= 0 || dy > m || dx <= 0 || dx > n) continue;
                    DP[y][x] += DP[dy][dx] % MOD;
                }
            }
        }
        
        return DP[m][n] % MOD;
    }
    
    private static void initMap(int m, int n, int[][] puddles){
        map = new int[m+1][n+1];
        for(int i = 0; i < puddles.length; i++){
            int puddleY = puddles[i][0];
            int puddleX = puddles[i][1];
            map[puddleY][puddleX] = PUDDLE;
        }
    }
}