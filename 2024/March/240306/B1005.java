// 현재 소요 시간: 58분 48초
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B1005 {

    static class Node implements Comparable<Node>{
        int num;
        List<Integer> linked;

        public Node(int num, List<Integer> linked){
            this.num = num;
            this.linked = new ArrayList<>(linked);
        }

        @Override
        public int compareTo(Node o) {
            return this.linked.size() - o.linked.size();
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(testcase-- > 0){
            String[] input = in.readLine().split(" ");
            int buildings_count = Integer.parseInt(input[0]);
            int build_case = Integer.parseInt(input[1]);

            int[] build_cost = new int[buildings_count+1];
            input = in.readLine().split(" ");

            for(int i = 0; i < buildings_count; i++)
                build_cost[i+1] = Integer.parseInt(input[i]);

            int[] DP = new int[buildings_count+1];
            boolean[] visited = new boolean[buildings_count + 1];
            
            List<Integer>[] lists = new ArrayList[buildings_count+1];
            for(int i = 0; i <= buildings_count; i++)   lists[i] = new ArrayList<>();
            
            for(int i = 0; i < build_case; i++){
                input = in.readLine().split(" ");
                int start = Integer.parseInt(input[0]);
                int end = Integer.parseInt(input[1]);
                
                lists[end].add(start);
            }
            Queue<Node> queue = new PriorityQueue<>();
            for(int i = 1; i <= buildings_count; i++){
                queue.offer(new Node(i, lists[i]));
            }

            int target = Integer.parseInt(in.readLine());

            while(!queue.isEmpty()){
                Node current = queue.poll();
                int currentNum = current.num;
                if(visited[currentNum]) continue;

                int size = current.linked.size();
                for(int i = 0; i < size; i++){
                    int next = current.linked.get(i);
                    DP[next] = Math.max(DP[currentNum], DP[next]);
                    lists[next].remove(Integer.valueOf(next));
                    queue.offer(new Node(next, lists[next]));
                }

                DP[currentNum] += build_cost[currentNum];
                if(currentNum == target) break;
            }

            sb.append(DP[target]).append("\n");

            // Queue<Integer> queue = new ArrayDeque<>();
            // queue.offer(target);

            // int answer = 0;
            // while(!queue.isEmpty()){ 
            //     int current = queue.poll();
            //     if(visited[current]) continue;
            //     visited[current] = true;
            //     DP[current] += build_cost[current];
            //     answer = Math.max(DP[current], answer);

            //     for(int i = 0; i < lists[current].size(); i++){
            //         int next = lists[current].get(i);
            //         DP[next] = Math.max(DP[next], DP[current]);
            //         queue.offer(next);
            //     }
            // }
            
            // sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
