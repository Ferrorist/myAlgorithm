import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1062 {
	static int K, answer;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		if(K < 5)	{ // 배운 글자가 없음
			System.out.println(0);	return;
		}
		else if(K == 26) { // 모든 글자를 배움
			System.out.println(N);	return;
		}
		
		// 각 단어마다 사용된 글자를 비트마스킹을 이용하여 저장
		int[] combine = new int[N];
		
		for(int i = 0; i < N; i++) {
			String word = in.readLine();
			int length = word.length(), value = 0;
			for(int strIdx = 0; strIdx < length; strIdx++) {
				int z = word.charAt(strIdx) - 'a';
				value = value | (1 << z);
			}
			combine[i] = value;
		}
		answer = 0;
		boolean[] selected = new boolean[26];
		for(int i = 0; i < 26; i++) {
			solve(combine, selected, 0, 0, i);
		}
		
		System.out.println(answer);
	}
	
	static void solve(int[] words, boolean[] visited, int value, int count, int start_idx) {
		if(count == K) {
			int correct = 0;
			int word_count = words.length;
			for(int i = 0; i < word_count; i++) {
				if((words[i] & value) == words[i])	correct++;
			}
			answer = Math.max(correct, answer);
			return;
		}
		
		for(int i = start_idx; i < 26; i++) {
			if(visited[i])	continue;
			visited[i] = true;
			solve(words, visited, (value | (1 << i)), count+1, i+1);
			visited[i] = false;
		}
	}

}
