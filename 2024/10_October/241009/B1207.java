/*
 * https://www.acmicpc.net/problem/1207
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.util.*;
class B1207 {
    private static class Block {
        int idx;
        int col, row;
        char[][] sharp;

        Block(int idx, int col, int row) {
            this.idx = idx;
            this.col = col;
            this.row = row;
            sharp = new char[col][row];
        }

        void setSharp(char[][] block) {
            for(int i = 0; i < this.col; i++){
                System.arraycopy(block[i], 0, sharp[i], 0, this.row);
            }
        }
    }

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static Block[] blockList;
    private static final char EMPTY = '.', BLOCK = '#';
    private static final int BLOCK_COUNT = 5;
    private static String solveAnswer = null;
    public static void main(String[] args) throws Exception {
        initArguments();
        solve();
        System.out.println((solveAnswer == null) ? "gg" : solveAnswer);
    }

    private static void initArguments() throws Exception {
        int length = Integer.parseInt(in.readLine());
        map = new int[length][length];
        blockList = new Block[BLOCK_COUNT];
        int count = 0;
        StringTokenizer st;
        for(int i = 0; i < BLOCK_COUNT; i++) {
            st = new StringTokenizer(in.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            blockList[i] = new Block(i+1, col, row);

            char[][] blockSharp = new char[col][row];
            
            for(int y = 0; y < col; y++) {
                String str = in.readLine();
                for(int x = 0; x < row; x++) {
                    blockSharp[y][x] = str.charAt(x);
                    if(blockSharp[y][x] == BLOCK)   count++;
                }
            }

            blockList[i].setSharp(blockSharp);
        }

        if(count != length * length) {
            System.out.println("gg");   System.exit(0);
        }
        
        solveAnswer = null;
    }

    
    private static void solve() {
        boolean[][] visited = new boolean[map.length][map.length];
        DFS(visited, 0);
    }

    private static void DFS(boolean[][] visited, int block_idx) {
        if(block_idx >= BLOCK_COUNT) {
            if(checkMap()){
                String currentMap = convertMaptoString();
                if(compareAnswer(currentMap)) {
                    solveAnswer = new String(currentMap);
                }
            }
            return;
        }

        for(int cy = 0; cy < map.length; cy++) {
            for(int cx = 0; cx < map.length; cx++) {
                if(visited[cy][cx]) continue;
                Block block = blockList[block_idx];
                int dy = cy + block.col - 1;
                int dx = cx + block.row - 1;
                if(checkRange(dy, dx) && checkBlock(cy, cx, block)){
                    releaseBlock(cy, cx, visited, block);
                    DFS(visited, block_idx + 1);
                    collectBlock(cy, cx, visited, block);
                }
            }
        }
    }

    private static boolean checkBlock(int y, int x, Block block) {
        for(int cy = 0; cy < block.col; cy++) {
            for(int cx = 0; cx < block.row; cx++) {
                int dy = cy + y, dx = cx + x;
                if(block.sharp[cy][cx] == BLOCK && map[dy][dx] != 0)    return false;
            }
        }
        return true;
    }

    private static void releaseBlock(int y, int x, boolean[][] visited, Block block) {
        for(int cy = 0; cy < block.col; cy++) {
            for(int cx = 0; cx < block.row; cx++) {
                int dy = cy + y, dx = cx + x;
                if(block.sharp[cy][cx] != EMPTY) {
                    visited[dy][dx] = true;
                    map[dy][dx] = block.idx;
                }
            }
        }
    }

    private static boolean checkMap() {
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++) {
                if(map[y][x] == 0) return false;
            }
        }
        return true;
    }

    private static void collectBlock(int y, int x, boolean[][] visited, Block block) {
        for(int cy = 0; cy < block.col; cy++) {
            for(int cx = 0; cx < block.row; cx++) {
                int dy = cy + y, dx = cx + x;
                if(block.sharp[cy][cx] != EMPTY) {
                    visited[dy][dx] = false;
                    map[dy][dx] = 0;
                }
            }
        }       
    }

    private static String convertMaptoString() {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map.length; x++){
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static boolean compareAnswer(String answer) {
        if(solveAnswer == null) return true;
        else return solveAnswer.compareTo(answer) >= 0;
    }

    private static boolean checkRange(int y, int x) {
        return y >= 0 && y < map.length && x >= 0 && x < map.length;
    }
}