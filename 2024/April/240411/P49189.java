/*
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/49189
 * 소요 시간 : 13분 36초 (제출시간 포함)
 * 
 * 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하라.
 * 가장 멀리 떨어진 노드? : 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드를 의미.
 * 입력받는 간선의 경우 양방향이며 가중치가 존재하지 않음. -> BFS?
 * 노드 개수 (n) : 2 <= n <= 20_000
 * 간선 개수 (e): 1 <= e <= 50_000
 */

import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 간선 리스트 추가
        List<Integer>[] edgeLists = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) edgeLists[i] = new ArrayList<Integer>();

        for(int i = 0; i < edge.length; i++){
            int start = edge[i][0], end = edge[i][1];
            edgeLists[start].add(end);
            edgeLists[end].add(start);
        }
        
        // 방문 배열
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(1); // 1번 노드에서 시작함.
        
        while(!queue.isEmpty()){
            int size = queue.size();
            answer = size;
            for(int i = 0; i < size; i++){
                int nodeNum = queue.poll();
                for(Integer num : edgeLists[nodeNum]){
                    if(visited[num])    continue;
                    queue.offer(num);
                    visited[num] = true;
                }
            }
        }
        
        return answer;
    }
}