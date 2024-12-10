/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/77485
 */
import java.util.*;

class Solution {
    private int[][] map;
    private final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int[] solution(int rows, int columns, int[][] queries) {
        initMap(rows, columns);
        int[] answer = new int[queries.length];
        
        int idx = 0;
        for (int[] rotate : queries) {
            int minValue = rotateMap(rotate);
            answer[idx++] = minValue;
        }
        
        return answer;
    
    }
    
    private void initMap(int rows, int columns) {
        map = new int[rows+1][columns+1];
        int number = 1;
        
        for (int y = 1; y <= rows; y++) {
            for (int x = 1; x <= columns; x++) {
                map[y][x] = number++;
            }
        }
    }
    
    private int rotateMap(int[] rotate) {
        int minValue = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        
        int cy = rotate[0], cx = rotate[1];
        for (int d = 0; d < dir.length; d++) {
            int dy = cy + dir[d][0];
            int dx = cx + dir[d][1];
            while(checkRange(dy, dx, rotate)) {
                deque.offerLast(map[cy][cx]);
                minValue = Math.min(minValue, map[cy][cx]);
                cy = dy;    cx = dx;
                dy += dir[d][0];    dx += dir[d][1];
            }
        }
        
        deque.offerFirst(deque.pollLast());
        
        process(deque, rotate);
        return minValue;
    }
    
    private void process(Deque<Integer> deque, int[] rotate) {
        int cy = rotate[0], cx = rotate[1];
        for (int d = 0; d < dir.length; d++) {
            int dy = cy + dir[d][0];
            int dx = cx + dir[d][1];
            while(checkRange(dy, dx, rotate)) {
                map[cy][cx] = deque.pollFirst();
                cy = dy;    cx = dx;
                dy += dir[d][0];    dx += dir[d][1];
            }
        }
    }
    
    private boolean checkRange(int y, int x, int[] rotate) {
        return y >= rotate[0] && x >= rotate[1] && y <= rotate[2] && x <= rotate[3];
    }
}