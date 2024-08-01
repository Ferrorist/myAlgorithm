/*
 * Baekjoon 1987 : 알파벳
 * https://www.acmicpc.net/problem/1987
 * 
 * 풀이 소요 시간 : 41분 15초
 * 사용 알고리즘 : DFS, 백트래킹
 * 메모리 : 295408KB
 * 시간 : 3212ms
 */
import java.io.*;
class B1987 {
    // 우, 하, 좌, 상
    static final int[][] dir = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    static int max_value = 1;
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] boardSize = in.readLine().split(" ");
        int col = Integer.parseInt(boardSize[0]);
        int row = Integer.parseInt(boardSize[1]);
        
        char[][] board = new char[col][row];
        boolean[][] visited = new boolean[col][row];
        for(int y = 0; y < col; y++){
            String input = in.readLine();
            for(int x = 0; x < row; x++)
                board[y][x] = input.charAt(x);
        }
        
        visited[0][0] = true;
        solve(board, visited, 0, 0, new String());

        System.out.println(max_value);
    }

    /*
     * board : 보드
     * visited : 방문 배열
     * y, x : 현재 탐색하는 칸의 y좌표, x좌표
     * str : 현재까지의 탐색 경로
     */
    static void solve(char[][] board, boolean[][] visited, int y, int x, String str){
        if(str.indexOf(String.valueOf(board[y][x])) != -1){
            return;
        }

        str = str.concat(String.valueOf(board[y][x]));
        max_value = Math.max(max_value, str.length());
        if(max_value >= 26){
            System.out.println(max_value);  System.exit(0);
        }


        for(int i = 0; i < dir.length; i++){
            int cy = y + dir[i][0];
            int cx = x + dir[i][1];
            
            if(cy < 0 || cy >= board.length || cx < 0 || cx >= board[0].length) 
                continue;

            if(visited[cy][cx]) continue;

            visited[cy][cx] = true;
            solve(board, visited, cy, cx, str);
            visited[cy][cx] = false;
        }
    }
}