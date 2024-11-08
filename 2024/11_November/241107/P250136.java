/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/250136
 * 소요 시간 : 22분 20초
 */
import java.util.*;
class Solution {

    private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0, OIL = 1;

    private int[][] oilMaps;
    private List<Integer> oilCounts;

    public int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        initOilMaps(land);
        
        for(int x = 0; x < land[0].length; x++) {
            answer = Math.max(answer, searchOils(x));
        }
        
        return answer;
    }
    
    private void initOilMaps(int[][] land) {
        oilCounts = new ArrayList<>();
        oilMaps = new int[land.length][land[0].length];

        for (int y = 0; y < oilMaps.length; y++) {
            Arrays.fill(oilMaps[y], -1);
        }

        setOils(land);
    }
    
    private void setOils(int[][] land) {
        int oilCount = 0;
        boolean[][] visited = new boolean[land.length][land[0].length];

        for (int y = 0; y < land.length; y++) {
            for (int x = 0; x < land[0].length; x++) {
                if (visited[y][x] || land[y][x] == EMPTY)    continue;
                else BFS(land, visited, y, x, oilCount++);
            }
        }
    }
    
    private void BFS(int[][] land, boolean[][] visited, int y, int x, int oilCount) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        
        while(!queue.isEmpty()) {
            int cy = queue.peek()[0], cx = queue.poll()[1];
            if(visited[cy][cx]) continue;
            
            visited[cy][cx] = true;
            oilMaps[cy][cx] = oilCount;
            count++;
            
            for(int d = 0; d < dir.length; d++) {
                int dy = cy + dir[d][0];
                int dx = cx + dir[d][1];
                if(checkRange(land, dy, dx) && !visited[dy][dx] && land[dy][dx] == OIL) {
                    queue.offer(new int[]{dy, dx});
                }
            }
        }
        
        oilCounts.add(count);
    }
    
    private boolean checkRange (int[][] land, int y, int x) {
        return y >= 0 && y < land.length && x >= 0 && x < land[0].length;
    }
    
    private int searchOils(int x) {
        int count = 0;
        boolean[] visitedOils = new boolean[oilCounts.size()];

        for(int y = 0; y < oilMaps.length; y++) {
            if(oilMaps[y][x] == -1 || visitedOils[oilMaps[y][x]]) continue;

            count += oilCounts.get(oilMaps[y][x]);
            visitedOils[oilMaps[y][x]] = true;
        }
        
        return count;
    }
}