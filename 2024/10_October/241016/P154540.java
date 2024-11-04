/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/154540
 */
import java.util.*;
class Solution {
    private final char SEA = 'X';
    private List<Integer> list = new ArrayList<>();
    private char[][] myMap;
    private final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[] solution(String[] maps) {
        myMap = new char[maps.length][maps[0].length()];
        initMap(maps);
        
        searchMap();
        int[] answer;
        
        if(list.size() == 0)    answer = new int[] {-1};
        else {
            answer = new int[list.size()];
            for(int i = 0; i < answer.length; i++) {
                answer[i] = list.get(i);
            }
            Arrays.sort(answer);
        }
        
        return answer;
    }
    
    private void initMap(String[] maps) {
        for(int y = 0; y < maps.length; y++) {
            for(int x = 0; x < maps[0].length(); x++) {
                myMap[y][x] = maps[y].charAt(x);
            }
        }
    }
    
    private void searchMap() {
        boolean[][] visited = new boolean[myMap.length][myMap[0].length];
        
        for(int y = 0; y < myMap.length; y++) {
            for(int x = 0; x < myMap[0].length; x++) {
                if(myMap[y][x] == SEA || visited[y][x]) continue;
                int sum = 0;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {y, x});
                while(!queue.isEmpty()) {
                    int cy = queue.peek()[0];
                    int cx = queue.poll()[1];
                    if(visited[cy][cx]) continue;
                    
                    visited[cy][cx] = true;
                    sum += (int)(myMap[cy][cx] - '0');
                    
                    for(int d = 0; d < dir.length; d++) {
                        int dy = cy + dir[d][0];
                        int dx = cx + dir[d][1];
                        
                        if(checkRange(dy, dx) && myMap[dy][dx] != SEA && !visited[dy][dx]) {
                            queue.offer(new int[] {dy, dx});
                        }
                    }
                }
                
                list.add(sum);
            }
        }
    }
    
    private boolean checkRange(int y, int x) {
        return y >= 0 && y < myMap.length && x >= 0 && x < myMap[0].length;
    }
}