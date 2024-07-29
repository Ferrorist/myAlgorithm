/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 * 소요 시간 : 16분 49초
 */
import java.util.*;
class Solution {
    class Dungeon {
        int requirement;    // 최소 필요 피로도
        int consume;        // 소모 피로도
        
        public Dungeon(int requirement, int consume) {
            this.requirement = requirement;
            this.consume = consume;
        }
    }
    
    boolean[] visited;
    int answer = 0;
    public int solution(int k, int[][] dungeons) {
        List<Dungeon> list = new ArrayList<>();
        for(int[] dungeon : dungeons){
            list.add(new Dungeon(dungeon[0], dungeon[1]));
        }
        
        for(int i = 0; i < list.size(); i++){
            visited = new boolean[list.size()];
            visited[i] = true;
            solve(i, list, 1, k);
        }
        
        return answer;
    }
    
    public void solve(int index, List<Dungeon> list, int count, int leftover){
        if(list.get(index).requirement > leftover){
            return;
        }
        
        answer = Math.max(answer, count);
        int consume = list.get(index).consume;
        
        for(int i = 0; i < list.size(); i++){
            if(visited[i])  continue;
            visited[i] = true;
            solve(i, list, count+1, leftover - consume);
            visited[i] = false;
        }
    }
}