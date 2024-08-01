/*
 * 문제 링크 : https://www.acmicpc.net/problem/10868
 * 소요 시간 : 약 23분 (제출시간 포함)
 * 세그먼트 트리 복습 (1)
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class B10868{
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = in.readLine().split(" ");

        int N = Integer.parseInt(input[0]); // 정수 개수
        int M = Integer.parseInt(input[1]); // 쌍

        int[] arr = new int[N+1];
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(in.readLine());

        int[] tree = new int[N*4];

        init(arr, tree, 1, 1, N);

        for(int couple = 0; couple < M; couple++){
            input = in.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            if(start == end)    sb.append(arr[start]).append("\n");
            else sb.append(getMin(tree, 1, 1, N, start, end)).append("\n");
        }

        System.out.println(sb);
    }

    static int init(int[] arr, int[] tree, int node, int start, int end){
        if(start == end) return tree[node] = arr[start];
        return tree[node] = Math.min(init(arr, tree, node*2, start, (start+end)/2), init(arr, tree, node*2+1, (start+end)/2 + 1, end));
    }

    // start ~ end : node가 지니는 범위
    // left ~ right : 값을 구하고자 하는 범위
    // node : node 번호
    static int getMin(int[] tree, int node, int start, int end, int left, int right){
        if(end < left || right < start) return Integer.MAX_VALUE;

        if (left <= start && end <= right)  return tree[node];

        return Math.min(getMin(tree, node*2, start, (start+end)/2, left, right), getMin(tree, node*2 + 1, (start+end)/2 + 1, end, left, right));
    }
}