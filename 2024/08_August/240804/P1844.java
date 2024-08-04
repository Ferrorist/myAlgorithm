/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 * 소요 시간 : 11분 57초
 */
import java.util.*;
class Solution {
    
    class location {
        int y, x;
        int distance;
        
        public location(int y, int x){
            this.y = y;
            this.x = x;
            this.distance = 1;
        }
        
        public location(int y, int x, int distance){
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
        
    }
    
    // 1 <= maps.length <= 100
    // 1 <= maps[0].length <= 100
    int EMPTY = 1, WALL = 0;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우, 하, 좌, 상
    public int solution(int[][] maps) {
        int answer = -1;
        location finish = new location(maps.length - 1, maps[0].length - 1);
        
        Queue<location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        queue.offer(new location(0, 0));
        visited[0][0] = true;
    
        while(!queue.isEmpty()){
            location current = queue.poll();
            if(current.y == finish.y && current.x == finish.x)
                return current.distance;
            
            for(int d = 0; d < dir.length; d++){
                int dy = current.y + dir[d][0];
                int dx = current.x + dir[d][1];
                
                // 범위를 벗어남
                if(dy < 0 || dy >= maps.length || dx < 0 || dx >= maps[0].length)   continue;
                
                // 이미 방문하거나 방문할 수 없는 곳(벽)인 경우
                if(visited[dy][dx] || maps[dy][dx] == WALL) continue;
                
                visited[dy][dx] = true;
                queue.offer(new location(dy, dx, current.distance + 1));
            }
        }
        
        return answer;
    }
}