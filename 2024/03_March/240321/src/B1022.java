import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1022 {
	
	static final int length = 10_001;
	static final int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		int start_y = Integer.parseInt(input[0]) + length/2, start_x = Integer.parseInt(input[1]) + length/2;
		int end_y = Integer.parseInt(input[2]) + length/2, end_x = Integer.parseInt(input[3]) + length/2;
		
		int sero = (end_y - start_y) + 1;
		int garo = (end_x - start_x) + 1;
		
		map = new int[sero][garo];
		Progress(start_y, start_x, end_y, end_x, sero*garo);
		
		
		
		int max_length = 0;
		
		for(int y = 0; y < sero; y++) {
			for(int x = 0; x < garo; x++) {
				max_length = Math.max(Integer.toString(map[y][x]).length(), max_length);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int y = 0; y < sero; y++) {
			for(int x = 0; x < garo; x++) {
				String str = String.format("%" + max_length + "d", map[y][x]);
				sb.append(str).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void Progress(int left_y, int left_x, int right_y, int right_x, int size) {
		int start_y, start_x;
		start_y = start_x = length / 2;
		int count = 0;
		int start = 1, moveDir = 0, moveCount = 1;
		
		int sero = right_y - left_y;
		int garo = right_x - left_x;
		
		if(start_y >= left_y && start_x >= left_x && start_y <= right_y && start_x <= right_x) {
			map[start_y-left_y][start_x-left_x] = start;
			count++;
		}
		
		progress:
		while(true) {
			if(count == size)	break;
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < moveCount; j++) {
					start_y += dir[moveDir][0];
					start_x += dir[moveDir][1];
					++start;
					if(start_y >= left_y && start_x >= left_x && start_y <= right_y && start_x <= right_x) {
						map[start_y-left_y][start_x-left_x] = start;
						count++;
					}
					if(count == size) break progress;
				}
				moveDir = (moveDir + 1) % 4;
			}
			moveCount++;
		}
	}
}
