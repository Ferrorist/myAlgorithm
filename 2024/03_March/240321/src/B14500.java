import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B14500 {
	static int answer;
	static int[][] map;
	static boolean[][] visited;
	static final int[][][] uniqueBlock = 
		{
			{{0, 0}, {0, -1}, {0, 1}, {-1, 0}}, // ㅗ
			{{0, 0}, {1, 0}, {0, 1}, {-1, 0}}, // ㅏ
			{{0, 0}, {0, -1}, {1, 0}, {-1, 0}}, // ㅓ
			{{0, 0}, {0, -1}, {0, 1}, {1, 0}}, // ㅜ
		};
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		map = new int[Integer.parseInt(input[0])][Integer.parseInt(input[1])];
		
		for(int y = 0; y < map.length; y++) {
			input = in.readLine().split(" ");
			for(int x = 0; x < map[0].length; x++)	map[y][x] = Integer.parseInt(input[x]);
		}
		
		visited = new boolean[map.length][map[0].length];
		
		answer = Integer.MIN_VALUE;
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				useUniqueBlock(y, x);
				solve(y, x, 0, 0);
			}
		}
		
		System.out.println(answer);
	}
	
	// map
	static void solve(int cy, int cx, int current, int count) {
		if(count == 4) {
			answer = Math.max(current, answer);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int dy = cy + dir[i][0], dx = cx + dir[i][1];
			if(dy < 0 || dx < 0 || dy >= map.length || dx >= map[0].length) continue;
			if(visited[dy][dx]) continue;
			visited[dy][dx] = true;
			solve(dy, dx, current + map[dy][dx], count+1);
			visited[dy][dx] = false;
		}
	}
	
	static void useUniqueBlock(int cy, int cx) {
		for(int blockIdx = 0; blockIdx < 4; blockIdx++) {
			int value = 0;
			boolean canUse = true;
			for(int pos = 0; pos < 4; pos++) {
				int dy = cy + uniqueBlock[blockIdx][pos][0];
				int dx = cx + uniqueBlock[blockIdx][pos][1];
				if(dy < 0 || dx < 0 || dy >= map.length || dx >= map[0].length) {
					canUse = false;	break;
				}
				value += map[dy][dx];
			}
			if(canUse)	answer = Math.max(value, answer);
		}
	}
}
