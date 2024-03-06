import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B14499{
    // 제자리, 동, 서, 북, 남
    static final int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] map = new int[N][M];
        int cy = Integer.parseInt(input[2]), cx = Integer.parseInt(input[3]);

        int command_count = Integer.parseInt(input[4]);
        
        for(int y = 0; y < N; y++){
            input = in.readLine().split(" ");
            for(int x = 0; x < M; x++)  map[y][x] = Integer.parseInt(input[x]);
        }

        input = in.readLine().split(" ");

        int[] dice = new int[7];
        int current = 1;
        int[] dice_dir = {0, 3, 4, 2, 5}; // 현재 주사위의 바닥면을 기준으로 (temp), 동, 서, 북, 남에 있는 면의 번호
        

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < command_count; i++){
            int cmd = Integer.parseInt(input[i]);
            int dy = cy + dir[cmd][0];
            int dx = cx + dir[cmd][1];
            if(dy < 0 || dx < 0 || dy >= N || dx >= M)  continue;
            int floor = dice_dir[cmd];
            switch(cmd){
                case 1:
                case 2:
                    dice_dir[3 - cmd] = current;
                    dice_dir[cmd] = 7 - current;
                    break;
                case 3:
                case 4:
                    dice_dir[7 - cmd] = current;
                    dice_dir[cmd] = 7 - current;
                    break;
            }
            current = floor;
            if(map[dy][dx] == 0) map[dy][dx] = dice[current];
            else {
                dice[current] = map[dy][dx];
                map[dy][dx] = 0;
            }
            cy = dy;    cx = dx;
            sb.append(dice[7-current]).append("\n");
        }

        System.out.println(sb);
    }
}