/*
 * https://www.acmicpc.net/problem/1520
 * 소요 시간 : 43분 17초
 */
import java.io.*;
import java.util.Arrays;
public class B1520 {
    // 우, 하, 좌, 상
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        
        // (1 <= M, N <= 500)
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        int[][] map = new int[M][N];
        int[][] DP = new int[M][N];
        for(int[] arr : DP) Arrays.fill(arr, -1);
        DP[M-1][N-1] = 1;

        for(int y = 0; y < M; y++){
            input = in.readLine().split(" ");
            for(int x = 0; x < N; x++){
                // (1 <= map[y][x] <= 10_000)
                map[y][x] = Integer.parseInt(input[x]);

            }
        }
        DP[0][0] = solve(map, DP, 0, 0);
        System.out.println(DP[0][0]);
    }

    static int solve(int[][] map, int[][] DP, int cy, int cx){
        if(DP[cy][cx] >= 0) return DP[cy][cx];

        int sum = 0;
        for(int d = 0; d < dir.length; d++){
            int y = cy + dir[d][0];
            int x = cx + dir[d][1];

            // 좌표가 map밖을 벗어남
            if(y < 0 || x < 0 || y >= map.length || x >= map[0].length) continue;

            if(map[cy][cx] <= map[y][x])    continue;

            if(DP[y][x] < 0)    DP[y][x] = Math.max(DP[y][x], solve(map, DP, y, x));
            sum += DP[y][x];
        }

        return sum;
    }
}
