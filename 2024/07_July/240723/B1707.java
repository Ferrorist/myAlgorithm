/*
 * https://www.acmicpc.net/problem/1707
 * 소요 시간 : 19분 27초
 */
import java.io.*;
import java.util.*;
public class B1707 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 테스트 케이스 ( 2 <= Testcase <= 5)
        int Testcase = Integer.parseInt(in.readLine());
        
        Start_Testcase:
        while(Testcase-- > 0){
            String[] input = in.readLine().split(" ");
            
            // 정점의 개수 ( 1 <= vertex_count <= 20_000 )
            // 간선의 개수 ( 1 <= edge_count <= 200_000 )
            int vertex_count = Integer.parseInt(input[0]);
            int edge_count = Integer.parseInt(input[1]);
            
            List<Integer>[] vertexs = new ArrayList[vertex_count+1];
            for(int i = 0; i <= vertex_count; i++)    vertexs[i] = new ArrayList<Integer>();
            
            
            // 간선 정보 입력받음
            for(int i = 0; i < edge_count; i++){
                input = in.readLine().split(" ");
                
                int vertexA = Integer.parseInt(input[0]);
                int vertexB = Integer.parseInt(input[1]);
                
                vertexs[vertexA].add(vertexB);
                vertexs[vertexB].add(vertexA);
            }

            int[] visited = new int[vertex_count+1];
            Arrays.fill(visited, -1);
            
            
            for(int i = 0; i <= vertex_count; i++){
                if(visited[i] != -1)    continue;
                Queue<Integer> queue = new ArrayDeque<>();
                visited[i] = 0;
                queue.offer(i);
                
                while(!queue.isEmpty()){
                    int size = queue.size();
                    for(int v = 0; v < size; v++){
                        int vertex = queue.poll();
                        for(Integer k : vertexs[vertex]){
                            if(visited[k] == -1){
                                visited[k] = (visited[vertex] + 1) % 2;
                                queue.offer(k);
                            }
                            else {
                                if(visited[vertex] == visited[k]) {
                                    sb.append("NO").append("\n");
                                    continue Start_Testcase;
                                }
                            }
                        }
                    }
                }
            }
            sb.append("YES").append("\n");
        }
        
        System.out.println(sb);
    }
}