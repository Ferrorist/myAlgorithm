import java.io.*;
import java.util.*;
public class B12865 {
	
	static class myObject{
		int cost;
		int weight;
		
		public myObject(int weight, int cost) {
			this.cost = cost;
			this.weight = weight;
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]), K = Integer.parseInt(input[1]);
		
		int[] DP = new int[K+1];
		Arrays.fill(DP, 0);
		
		Queue<myObject> queue = new ArrayDeque<>(N);
		
		for(int i = 0; i < N; i++) {
			input = in.readLine().split(" ");
			int weight = Integer.parseInt(input[0]);
			int cost = Integer.parseInt(input[1]);
			queue.offer(new myObject(weight, cost));
		}
		
		while(!queue.isEmpty()) {
			int cost = queue.peek().cost;
			int weight = queue.poll().weight;

			for(int i = K; i >= weight; i--) {
				DP[i] = Math.max(DP[i], DP[i - weight] + cost);
			}
			
		}
		System.out.println(DP[K]);
	}
}
