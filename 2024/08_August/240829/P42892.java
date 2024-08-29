/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42892
 * 소요 시간 : 27분 27초
 */
import java.util.*;
class Solution {
    private class Node implements Comparable<Node>{
        int x, y, num;
        Node left, right;
        
        Node(int x, int y, int num){
            this.x = x; this.y = y;
            this.num = num;
            left = right = null;
        }
        
        void setLeft(Node node){ this.left = node; }
        void setRight(Node node){ this.right = node; }
        Node getLeft() { return this.left; }
        Node getRight() { return this.right; }
        int getNum() { return this.num; }
        
        @Override
        public int compareTo(Node o){
            if(this.y == o.y)   return this.x - o.x;
            return o.y - this.y;
        }
    }
    
    List<Integer> preOrderList, postOrderList;
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        Queue<Node> queue = setPriorityQueue(nodeinfo);
        
        Node root = queue.poll();
        while(!queue.isEmpty()) setTree(root, queue.poll());
        
        preOrderList = new ArrayList<>();
        preOrder(root);
        
        postOrderList = new ArrayList<>();
        postOrder(root);
        
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
    
    private Queue<Node> setPriorityQueue(int[][] nodeinfo){
        Queue<Node> queue = new PriorityQueue<>();
        
        for(int i = 0; i < nodeinfo.length; i++)
            queue.offer(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        
        return queue;
    }
    
    private void setTree(Node current, Node target){
        if(current.x < target.x) {  // right
            if(current.getRight() != null)    setTree(current.getRight(), target);
            else current.setRight(target);
        }
        else { // left
            if(current.getLeft() != null)   setTree(current.getLeft(), target);
            else current.setLeft(target);
        }
    }
    
    private void preOrder(Node node) {
        if(node == null)    return;
        preOrderList.add(node.getNum());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }
    
    private void postOrder(Node node) {
        if(node == null)    return;
        postOrder(node.getLeft());
        postOrder(node.getRight());
        postOrderList.add(node.getNum());
    }
}