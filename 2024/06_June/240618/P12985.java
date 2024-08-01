/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12985
 * 소요 시간 : 23분 03초
 */
import java.util.*;
class Solution
{
    class Node {
        int idx;
        int min, max;
        public Node(int idx, int min, int max){
            this.idx = idx;
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
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(1, 1, n));
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int idx = node.idx;
            if(node.contains(a) && node.contains(b)){
                answer = node.idx;
                int min = node.min, max = node.max;
                Node left = new Node(idx*2, min, (min + max) / 2);
                Node right = new Node(idx*2 + 1, (min + max) / 2 + 1, max);
                queue.offer(left);
                queue.offer(right);
            }
        }
        
        return max_height - (int)log2(answer);
    }
    
    double log2(int n){
        return Math.log(n) / Math.log(2);
    }
}