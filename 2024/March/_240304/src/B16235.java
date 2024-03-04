import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 봄
// 나무 -> 본인 나이 만큼 양분 먹고, 나이 += 1
// 하나의 칸에 여러 개 나무가 있다면 나이가 어린 나무부터 양분 먹음.
// 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무 -> 즉시 죽음

// 여름
// 봄에 죽은 나무가 양분이 됨. -> 죽은 나무 / 2 한 값. (소수점 아래 버림)

// 가을
// 나무가 번식함. (번식 조건: 나이가 5의 배수)
// 인접한 8개의 칸에 나이가 1인 나무 생성.

// 겨울
// 로봇이 땅을 돌아다니면서 땅에 양분을 추가함.

// K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하자.
public class B16235 {
	static final int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, { 1, -1}, {1, 0}, {1, 1}};
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]), K = Integer.parseInt(input[2]);

		int[][] A = new int[N][N];
		int[][] Matrix = new int[N][N];
		List<Integer>[][] listMatrix = new ArrayList[N][N];
		Deque<Integer>[][] dequeMatrix = new ArrayDeque[N][N];

		for(int y = 0; y < N; y++){
			input = in.readLine().split(" ");
			Arrays.fill(Matrix[y], 5);
			for(int x = 0; x < N; x++){
				A[y][x] = Integer.parseInt(input[x]);
				listMatrix[y][x] = new ArrayList<>();
			}
		}

		for(int i = 0; i < M; i++){
			input = in.readLine().split(" ");
			int y = Integer.parseInt(input[0]), x = Integer.parseInt(input[1]);
			int age = Integer.parseInt(input[2]);
			listMatrix[y-1][x-1].add(age);
		}

		for(int y = 0; y < N; y++){
			for(int x = 0; x < N; x++){
				Collections.sort(listMatrix[y][x]);
				dequeMatrix[y][x] = new ArrayDeque<>(listMatrix[y][x]);
			}
		}


		for(int year = 0; year < K; year++){
			// 봄, 여름, 겨울
			for(int y = 0; y < N; y++){
				for(int x = 0; x < N; x++){
					int size = dequeMatrix[y][x].size();
					if(size == 0) continue;
					int add = 0;
					for(int t = 0; t < size; t++){
						int age = dequeMatrix[y][x].pollFirst();
						if(age < Matrix[y][x]) {
							Matrix[y][x] -= age++;
							dequeMatrix[y][x].offerLast(age);
						}
						else {
							add += age / 2;
						}
					}
					Matrix[y][x] += (A[y][x] + add);

					// List<Integer> list = new ArrayList<>();
					// int add = 0;
					//
					// int size = listMatrix[y][x].size();
					// Collections.sort(listMatrix[y][x]);
					// for(int t = 0; t < size; t++){
					// 	int age = listMatrix[y][x].get(t);
					// 	if(Matrix[y][x] < age) add += age / 2;
					// 	else{
					// 		Matrix[y][x] -= age++;
					// 		list.add(age);
					// 	}
					// }
					// Matrix[y][x] += (A[y][x] + add);
					// listMatrix[y][x] = new ArrayList<>(list);
				}
			}
			// 가을
			for(int y = 0; y < N; y++){
				for(int x = 0; x < N; x++){
					int size = dequeMatrix[y][x].size();
					for(int t = 0; t < size; t++) {
						int age = dequeMatrix[y][x].pollFirst();
						if (age < 5) {
							dequeMatrix[y][x].offerLast(age);
							continue;
						}
						if(age % 5 == 0){
							for(int i = 0; i < 8; i++){
								int dy = y + dir[i][0], dx = x + dir[i][1];
								if(dy >= 0 && dx >= 0 && dy < N && dx < N){
									dequeMatrix[dy][dx].offerFirst(1);
								}
							}
						}
					}
					// Collections.sort(listMatrix[y][x], Collections.reverseOrder());
					// int size = listMatrix[y][x].size();
					// for(int t = 0; t < size; t++){
					// 	// int age = listMatrix[y][x].get(t).age;
					// 	int age = listMatrix[y][x].get(t);
					// 	if(age < 5) break;
					//
					// 	if(age % 5 == 0){
					// 		for(int i = 0; i < 8; i++){
					// 			int dy = y + dir[i][0], dx = x + dir[i][1];
					// 			if(dy >= 0 && dx >= 0 && dy < N && dx < N){
					// 				listMatrix[dy][dx].add(1);
					// 			}
					// 		}
					// 	}
					// }
				}
			}
		}

		int answer = 0;
		for(int y = 0; y < N; y++){
			for(int x = 0; x < N; x++)
				// answer += listMatrix[y][x].size();
				answer += dequeMatrix[y][x].size();
		}


		System.out.println(answer);
	}
}
