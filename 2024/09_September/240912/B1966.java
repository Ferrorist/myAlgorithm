/*
 * https://www.acmicpc.net/problem/1966
 * 소요 시간 : 14분 38초
 */
import java.io.*;
import java.util.*;
class B1966 {
    static class Node {
        int index;
        int priority;

        Node(int index, int priority){
            this.index = index;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int Testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;        
        while(Testcase-- > 0){
            st = new StringTokenizer(in.readLine());
            int document = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            Queue<Node> queue = new ArrayDeque<>();
            Queue<Integer> numQueue = new PriorityQueue<>(Collections.reverseOrder());
            st = new StringTokenizer(in.readLine());

            for(int i = 0; i < document; i++){
                int priority = Integer.parseInt(st.nextToken());
                queue.offer(new Node(i, priority));
                numQueue.offer(priority);
            }

            int count = 1;
            while(!queue.isEmpty()){
                Node current = queue.poll();
                if(numQueue.peek() != current.priority) queue.offer(current);
                else {
                    numQueue.poll();
                    if(current.index == target) break;
                    else count++;
                }
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());
    }
}