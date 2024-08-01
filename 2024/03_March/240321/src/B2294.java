import java.io.*;
import java.util.*;
public class B2294 {
	public static void main(String args[]) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		
		Queue<Integer> queue = new PriorityQueue<Integer>();
		for(int i = 0; i < n; i++)	queue.offer(Integer.parseInt(in.readLine()));
		
		int[] DP = new int[k+1];
		Arrays.fill(DP, Integer.MAX_VALUE);
		DP[0] = 0;
		
		while(!queue.isEmpty()) {
			int cost = queue.poll();
			
			for(int i = cost; i <= k; i++) {
				if(DP[i-cost] == Integer.MAX_VALUE)	continue;
				DP[i] = Math.min(DP[i], DP[i-cost] + 1);
			}
			
		}
		
		if(DP[k] == Integer.MAX_VALUE)	System.out.println(-1);
		else System.out.println(DP[k]);
	}
}
