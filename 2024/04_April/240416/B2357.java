/*
 * 문제 링크 : https://www.acmicpc.net/problem/2357
 * 소요 시간 : 20분 18초 (제출시간 포함)
 * 세그먼트 트리 공부 목적으로 풀이
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class B2357 {
    static class Node{
        int min, max;

        public Node(int num){
            this.min = this.max = num;
        }

        public Node(int min, int max){
            this.min = min;
            this.max = max;
        }

        int compareMin(Node n){
            return Math.min(this.min, n.min);
        }

        int compareMax(Node n){
            return Math.max(this.max, n.max);
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = in.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 정수 개수
        int M = Integer.parseInt(input[1]); // 쌍 개수

        int[] arr = new int[N+1];
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(in.readLine());

        Node[] tree = new Node[N*4];

        init(arr, tree, 1, 1, N);

        for(int i = 0; i < M; i++){
            input = in.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            Node node = getNode(tree, 1, 1, N, start, end);
            sb.append(node.min).append(" ").append(node.max).append("\n");
        }

        System.out.println(sb);
    }

    static Node init(int[] arr, Node[] tree, int node, int start, int end){
        if(start == end)
            return tree[node] = new Node(arr[start]);

        Node left = init(arr, tree, node*2, start, (start+end)/2);
        Node right = init(arr, tree, node*2+1, (start+end)/2 + 1, end);
        return tree[node] = new Node(left.compareMin(right), left.compareMax(right));
    }

    // start ~ end : node가 지니는 범위
    // left ~ right : 탐색 범위
    static Node getNode(Node[] tree, int node, int start, int end, int left, int right){
        if(end < left || right < start) return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
        
        if(left <= start && end <= right)   return tree[node];

        Node leftNode = getNode(tree, node*2, start, (start+end)/2, left, right);
        Node rightNode = getNode(tree, node*2 + 1, (start+end)/2 + 1, end, left, right);

        return new Node(leftNode.compareMin(rightNode), leftNode.compareMax(rightNode));
    }
}
