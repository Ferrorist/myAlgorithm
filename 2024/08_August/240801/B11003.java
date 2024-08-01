/*
 * https://www.acmicpc.net/problem/11003
 * 소요 시간 : 1시간 초과 (답지 참고함)
 */
import java.io.*;
import java.util.*;
public class B11003 {
    static class Node implements Comparable<Node>{
        int index;
        int value;

        public Node(int index, int value){
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o){
            if(this.value == o.value) return o.index - this.index;
            return this.value - o.value;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        
        // (1 <= L <= N <= 5_000_000)
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);

        StringTokenizer st = new StringTokenizer(in.readLine());
        Deque<Node> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++){
            int min_range = i - L + 1;
            Node node = new Node(i, Integer.parseInt(st.nextToken()));
            
            while(!deque.isEmpty() && deque.peekLast().compareTo(node) > 0) deque.pollLast();

            deque.offerLast(node);

            while(!deque.isEmpty() && deque.peekFirst().index < min_range)  deque.pollFirst();

            sb.append(deque.peekFirst().value).append(" ");
        }
        
        System.out.println(sb);
    }
}
