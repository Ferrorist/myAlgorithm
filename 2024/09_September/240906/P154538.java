/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/154538
 */
import java.util.*;
class Solution {
    class Node {
        int value, count;
        
        Node(int value, int count){
            this.value = value;
            this.count = count;
        }
    }
    public int solution(int x, int y, int n) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[y+1];
        queue.offer(new Node(x, 0));    visited[x] = true;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int count = node.count;
            if(node.value == y) return count;
            else {
                int value = node.value;
                if(value + n <= y && !visited[value+n]){
                    queue.offer(new Node(value + n, count+1));
                    visited[value+n] = true;
                }  
                if(value * 2 <= y && !visited[value*2]){
                    queue.offer(new Node(value * 2, count+1));
                    visited[value*2] = true;
                }
                if(value * 3 <= y && !visited[value*3]){
                    queue.offer(new Node(value * 3, count+1));
                    visited[value*3] = true;
                }
            }
        }
        
        return -1;
    }
    
    
}