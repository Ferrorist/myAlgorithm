/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/17679
 */
class Solution {
    private char[][] map;
    private final int[][] searchDir = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    private final char EMPTY = '.';
    private boolean[][] hasMoved;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        initMap(m, n, board);
        while(true) {
            hasMoved = new boolean[m][n];   
            int count = removeCount(hasMoved);
            if (count > 0) {
                answer += count;
                removeBlock(hasMoved);
                updateMap();
            } else {
                break;
            }
        }
        return answer;
    }
    
    private void initMap(int m, int n, String[] board) {
        map = new char[m][n];
        
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                map[y][x] = board[y].charAt(x);
            }
        }
    }
    
    private int removeCount(boolean[][] hasMoved) {
        int count = 0;
        for (int y = 0; y < map.length - 1; y++) {
            for (int x = 0; x < map[0].length - 1; x++) {
                if (map[y][x] == EMPTY) {
                   continue; 
                }  else if (check(y, x)) {
                    for (int d = 0; d < searchDir.length; d++) {
                        int dy = y + searchDir[d][0];
                        int dx = x + searchDir[d][1];
                        
                        if (!hasMoved[dy][dx]) {
                            hasMoved[dy][dx] = true;
                            count++;
                        }
                    }
                }
            }
        }
        
        return count;
    }
    
    private boolean check(int y, int x) {
        return map[y][x] == map[y][x+1] && map[y][x] == map[y+1][x] && map[y][x] == map[y+1][x+1];
    }
    
    private void removeBlock(boolean[][] hasMoved) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (hasMoved[y][x]) {
                    map[y][x] = EMPTY;
                }
            }
        }
    }
    
    private void updateMap() {
        String[] board = getBoardStringArray();
        
        for (int x = 0; x < map[0].length; x++) {
            for (int y = map.length - 1; y >= 0; y--) {
                map[y][x] = board[x].charAt((map.length - 1) - y);
            }
        }
    }
    
    private String[] getBoardStringArray() {
        String[] board = new String[map[0].length];
        
        for (int x = 0; x < map[0].length; x++) {
            StringBuilder sb = new StringBuilder();
            int EMPTY_COUNT = 0;
            for (int y = map.length - 1; y >= 0; y--) {
                if(map[y][x] == EMPTY) {
                    EMPTY_COUNT++;
                } else {
                    sb.append(map[y][x]);
                }
            }
            
            for (int i = 0; i < EMPTY_COUNT; i++) {
                sb.append(EMPTY);
            }
            
            board[x] = sb.toString();
        }
        
        return board;
    }
}