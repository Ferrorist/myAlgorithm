/*
 * 문제 링크 : https://www.acmicpc.net/problem/6549
 * (풀이 중)
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;
public class B6549 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input;
        while(true){
            input = in.readLine().split(" ");
            int rectangle_count = Integer.parseInt(input[0]);
            if(rectangle_count == 0)    break;

            int[] arr = new int[rectangle_count+1];

            for(int i = 1; i <= rectangle_count; i++)   arr[i] = Integer.parseInt(input[i]);

            int height = (int)Math.ceil(Math.log(rectangle_count)/Math.log(2));
            int[] tree = new int[2 << height];

            init(arr, tree, 1, 1, rectangle_count);
            sb.append(max_area).append("\n");
        }
        System.out.println(sb);
    }

    static int init(int[] arr, int[] tree, int node, int start, int end){
        if(start == end){
            return tree[node] = arr[start];
        }
        
        int leftNode = init(arr, tree, node*2, start, (start+end)/2);
        int rightNode = init(arr, tree, node*2+1, (start+end)/2 + 1, end);

        return tree[node] = Math.min(leftNode, rightNode);
    }

    // start ~ end : node가 지닌 범위
    // left ~ right : 탐색 범위
    static int getMin(int[] tree, int node, int start, int end, int left, int right){
        if(end < left || right < start) return 0;

        if(left <= start && end <= right)   return tree[node];

        int leftNode = getMin(tree, node*2, start, (start+end)/2, left, right);
        int rightNode = getMin(tree, node*2 + 1, (start+end)/2 + 1, end, left, right);
        return (leftNode < rightNode) ? leftNode : rightNode;
    }

    static void getArea(int left, int right){
        
    }
}
