/*
 * https://www.acmicpc.net/problem/1238
 * 소요 시간 : 29분 54초
 */
import java.io.*;
import java.util.*;
public class B1238 {
    static class Node implements Comparable<Node> {
        int index, cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    static int INF = 1_000_001;
    static List<Node>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");

        // 마을의 수(=학생의 수, 1 <= N <= 1_000)
        int N = Integer.parseInt(input[0]);

        // 도로 개수 ( 1 <= M <= 10_000 )
        int M = Integer.parseInt(input[1]);

        list = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) list[i] = new ArrayList<>();
        // for(List<Node> li : list) li = new ArrayList<Node>();

        // 파티를 여는 마을의 번호
        int target = Integer.parseInt(input[2]);

        // 도로 정보 입력받음
        for(int i = 0; i < M; i++){
            input = in.readLine().split(" ");

            // 도로의 시작점, 끝점, 필요한 소요시간
            // ( 1 <= time <= 100 )
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int time = Integer.parseInt(input[2]);

            list[start].add(new Node(end, time));
        }

        int answer = -1;
        // 모든 학생들은 target에 갈 수 있으며, 다시 집으로 돌아올 수 있으므로,
        // 그에 대한 예외 처리는 하지 않는다.
        for(int i = 1; i <= N; i++){
            if(i == target) continue;
            
            int cost = Djikstra(i, target) + Djikstra(target, i);
            answer = Math.max(answer, cost);
        }
        
        System.out.println(answer);
    }

    static int Djikstra(int start, int end){
        Queue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[list.length];    
        queue.offer(new Node(start, 0));

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(visited[current.index])  continue;
            if(current.index == end)    return current.cost;

            visited[current.index] = true;
            for(Node rode : list[current.index]){
                queue.offer(new Node(rode.index, current.cost + rode.cost));
            }
        }
        
        return 0;
    }
}