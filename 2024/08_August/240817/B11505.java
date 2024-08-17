/*
 * https://www.acmicpc.net/problem/11505
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.util.*;
public class B11505 {
    static final int INF = 1_000_000_007;
    static long[] Tree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 수 개수 ( 1 <= N <= 1_000_000 )
        int N = Integer.parseInt(st.nextToken());

        // M : 수 변경 횟수, K : 구간 곱 구하는 횟수
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        for(int i = 1; i <= N; i++)  arr[i] = Integer.parseInt(in.readLine());

        Tree = new long[N * 4];

        init(arr, 1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(in.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            // update
            if(cmd == 1){
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                update(index, 1, N, 1, value);
            }
            // segment
            else {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                sb.append(segment(1, N, start, end, 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 세그먼트 트리 초기화
    private static long init(int[] arr, int left, int right, int index) {
        if(left == right)   {
            return Tree[index] = arr[left];
        }

        int mid = (left + right) / 2;
        return Tree[index] = 
            (init(arr, left, mid, index * 2)) * 
            (init(arr, mid+1, right, index*2 + 1)) % INF;
    }

    // 세그먼트 트리 값 변경
    private static long update(int index, int left, int right, int node, int value) {
        if(index < left || right < index) return Tree[node];

        if(left == right)   return Tree[node] = value;
        
        int mid = (left + right) / 2;

        return Tree[node] = 
        (update(index, left, mid, node * 2, value)) * 
        (update(index, mid + 1, right, node * 2 + 1, value)) % INF;
    }

    // 구간 곱 구하기
    private static long segment(int left, int right, int start, int end, int node) {
        if(start > end) return segment(left, right, end, start, node);

        if(right < start || end < left){
            return 1;
        }

        if(start <= left && right <= end)   return Tree[node];

        int mid = (left + right) / 2;

        return 
            (segment(left, mid, start, end, node * 2)) *
            (segment(mid + 1, right, start, end, node * 2 + 1)) % INF;

    }
}
