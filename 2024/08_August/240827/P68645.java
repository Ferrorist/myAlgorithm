/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/68645
 * 소요 시간 : 10분 19초
 */
class Solution {
    final int[][] dir = {{1, 0}, {0, 1}, {-1, -1}};
    public int[] solution(int n) {
        int final_value = n * (n+1) / 2;
        int[] answer = new int[final_value];
        
        int[][] board = new int[n + 1][n + 1];
        
        int cy = 0, cx = 1, cdir = 0;
        int current_value = 1;
        while(current_value <= final_value){
            int dy = cy + dir[cdir][0];
            int dx = cx + dir[cdir][1];
            
            // out of range
            if(dy < 1 || dy > n || dx < 1 || dx > n || board[dy][dx] != 0) {
                cdir = (cdir + 1) % dir.length;
            }
            else {
                board[dy][dx] = current_value++;
                cy = dy;    cx = dx;
            }
        }
        
        int idx = 0;
        for(int y = 1; y <= n; y++){
            for(int x = 1; x <= y; x++){
                answer[idx++] = board[y][x];
            }
        }
        
        return answer;
    }
}