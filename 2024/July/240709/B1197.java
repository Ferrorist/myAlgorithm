/*
 * https://www.acmicpc.net/problem/1197
 * 공부 겸 알고리즘 풀이.
 * 최소 스패닝 트리 (MST)의 경우
 * 크루스칼 알고리즘 또는 프림 알고리즘으로 해결할 수 있다.
 * 해당 코드에서는 크루스칼 알고리즘으로 해결하였다.
 */
import java.io.*;
import java.util.*;

class B1197 {
    static class Edge implements Comparable<Edge> {
        int A, B;
        int weight;

        public Edge(int A, int B, int weight){
            this.A = A;
            this.B = B;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");
        int V = Integer.parseInt(input[0]); // 정점 개수
        int E = Integer.parseInt(input[1]); // 간선 개수

        parents = new int[V+1];
        for(int i = 0; i <= V; i++) parents[i] = i;
        
        Queue<Edge> queue = new PriorityQueue<>();
        for(int i = 0; i < E; i++){
            input = in.readLine().split(" ");

            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            Edge edge = new Edge(A, B, weight);
            queue.offer(edge);
        }

        long sum = 0;   int selected = 0;
        while(!queue.isEmpty() && selected < V - 1){
            Edge current = queue.poll();
            if(find(current.A) != find(current.B)){
                union(current.A, current.B);
                sum += (long)current.weight;
                selected++;
            }
        }

        System.out.println(sum);
    }

    static int find(int a){
        if(parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }

    static void union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x != y)  parents[y] = x;
    }
}