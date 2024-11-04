/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/159993
 */
import java.util.*;
class Solution {
    static final char START = 'S', END = 'E', LEVER = 'L', EMPTY = 'O', WALL = 'X';
    static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[] startPoint, leverPoint, endPoint;
    char[][] map;
    public int solution(String[] maps) {
        initMap(maps);

        int startTolever = search(startPoint, leverPoint);
        int leverToend = search(leverPoint, endPoint);
        
        System.out.println(startTolever + " " + leverToend);
        if(startTolever != -1 && leverToend != -1)  return startTolever + leverToend;
        return -1;
    }
    
    private void initMap(String[] maps) {
        map = new char[maps.length][maps[0].length()];
        
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = maps[y].charAt(x);
                switch(map[y][x]) {
                    case START:
                        startPoint = new int[]{y, x};
                        break;
                    case END:
                        endPoint = new int[]{y, x};
                        break;
                    case LEVER:
                        leverPoint = new int[]{y, x};
                        break;
                }
            }
        }
    }
    
    private int search(int[] start, int[] end) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        boolean arrived = false;
        
        visited[start[0]][start[1]] = true;
        queue.offer(new int[]{start[0], start[1]});
        int result = 0;
        
        Search:
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int cy = queue.peek()[0], cx = queue.poll()[1];
                if(cy == end[0] && cx == end[1]) {
                    arrived = true;
                    break Search;
                }
                
                for(int d = 0; d < dir.length; d++) {
                    int dy = cy + dir[d][0];
                    int dx = cx + dir[d][1];
                    if(checkRange(dy, dx) && !visited[dy][dx] && map[dy][dx] != WALL) {
                        visited[dy][dx] = true;
                        queue.offer(new int[]{dy, dx});
                    }
                }
            }
            result++;
        }
        
        if(arrived) return result;
        else return -1;
    }
    
    private boolean checkRange(int dy, int dx) {
        return dy >= 0 && dy < map.length && dx >= 0 && dx < map[0].length;
    }
}