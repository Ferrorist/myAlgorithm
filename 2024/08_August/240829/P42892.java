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
        initList();
        Queue<Node> queue = setPriorityQueue(nodeinfo);
        
        Node root = queue.poll();
        while(!queue.isEmpty()) setTree(root, queue.poll());
        
        setOrder(root, false, preOrderList);
        setOrder(root, true, postOrderList);
        
        answer[0] = convertList(preOrderList);
        answer[1] = convertList(postOrderList);
        
        return answer;
    }

    private void initList() {
        preOrderList = new ArrayList<>();
        postOrderList = new ArrayList<>();
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
    
    private void setOrder(Node node, boolean isPost, List<Integer> list){
        if(node == null)    return;

        if(isPost){ // postOrder
            setOrder(node.getLeft(), isPost, list);
            setOrder(node.getRight(), isPost, list);
            list.add(node.getNum());
        }
        else { // preOrder
            list.add(node.getNum());
            setOrder(node.getLeft(), isPost, list);
            setOrder(node.getRight(), isPost, list);
        }
    }

    private int[] convertList(List<Integer> list){
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}