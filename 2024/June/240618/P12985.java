/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12985
 * 소요 시간 : 23분 03초
 */
import java.util.*;
class Solution
{
    class Node {
        int min, max;
        
        public Node(int min, int max){
            this.min = min;
            this.max = max;
        }
        
        boolean contains(int n){
            return min <= n && n <= max;
        }       
    }
    
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int max_height = (int)log2(n);
        Node[] tree = new Node[n * 4 + 1];
        tree[1] = new Node(1, n);
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        
        while(!queue.isEmpty()){
            int idx = queue.poll();
            Node node = tree[idx];
            if(node.contains(a) && node.contains(b)){
                answer = idx;
                int min = node.min, max = node.max;
                queue.offer(idx * 2);   queue.offer(idx * 2 + 1);
                tree[idx*2] = new Node(min, (min + max) / 2);
                tree[idx*2 + 1] = new Node((min + max) / 2 + 1, max);
            }
        }
        
        return max_height - (int)log2(answer);
    }
    
    double log2(int n){
        return Math.log(n) / Math.log(2);
    }
}