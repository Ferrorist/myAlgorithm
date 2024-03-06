import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B14503 {
	// 상(북), 우(동), 하(남), 좌(서)
	static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static final int NOT_CLEAN = 0, WALL = 1, CLEAN = 2;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		int N = Integer.parseInt(input.split(" ")[0]), M = Integer.parseInt(input.split(" ")[1]);
		input = in.readLine();
		int cur_y = Integer.parseInt(input.split(" ")[0]), cur_x = Integer.parseInt(input.split(" ")[1]);
		int cur_dir = Integer.parseInt(input.split(" ")[2]);

		int[][] map = new int[N][M];
		int[] total = new int[N];
		for(int y = 0; y < N; y++){
			String[] rowInput = in.readLine().split(" ");
			for(int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(rowInput[x]);
				total[y] += map[y][x];
			}
		}

		Process:
		while(true){
			map[cur_y][cur_x] = CLEAN;

			for(int i = 0; i < 4; i++){
				int dy = cur_y + dir[i][0];
				int dx = cur_x + dir[i][1];
				if(map[dy][dx] == NOT_CLEAN) {
					cur_dir = (cur_dir == 0) ? 3 : cur_dir - 1;
					dy = cur_y + dir[cur_dir][0];
					dx = cur_x + dir[cur_dir][1];
					if(map[dy][dx] == NOT_CLEAN){
						cur_y = dy; cur_x = dx;
					}
					continue Process;
				}
			}

			int behind_cur = (cur_dir + 2) % 4;
			int by = cur_y + dir[behind_cur][0];
			int bx = cur_x + dir[behind_cur][1];

			if(map[by][bx] != WALL){
				cur_y = by; cur_x = bx;
			}
			else break;
		}
		int answer = 0;
		for(int y = 0; y < N; y++){
			int sum = 0;
			for(int x = 0; x < M; x++) sum += map[y][x];
			answer += (sum - total[y]) / 2;
		}

		System.out.println(answer);
	}
}
