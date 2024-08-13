/*
 * https://www.acmicpc.net/problem/11003
 * 소요 시간 : 33분 50초
 */
import java.io.*;
import java.util.*;
public class B11003_3 {
    static class Node implements Comparable<Node> {
        int index, value;

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
        
        // N : 정수 개수, L : 범위 ( 1 <= L <= N <= 5_000_000 )
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);

        StringTokenizer st = new StringTokenizer(in.readLine());
        Deque<Node> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        
        int idx = 1;
        while(st.hasMoreTokens()){
            int value = Integer.parseInt(st.nextToken());
            Node current = new Node(idx, value);

            while(!deque.isEmpty() && deque.peekLast().compareTo(current) > 0)
                deque.pollLast();
            
            deque.offerLast(current);

            while(!deque.isEmpty() && deque.peekFirst().index <= idx - L) 
                deque.pollFirst();

            idx++;
            sb.append(deque.peekFirst().value).append(" ");
        }

        System.out.println(sb.toString());
    }
}
