/*
 * 문제 링크 : https://www.acmicpc.net/problem/2042
 * 소요 시간 : 53분 51초 (제출시간 포함)
 * 세그먼트 트리를 공부하기 위해 이 문제를 풀었다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class B2042 {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = in.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 수의 개수
        int M = Integer.parseInt(input[1]); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(input[2]); // 구간의 합을 구하는 횟수

        BigInteger[] arr = new BigInteger[N+1];

        for(int i = 1; i <= N; i++){
            arr[i] = BigInteger.valueOf(Long.parseLong(in.readLine()));
        }

        BigInteger[] tree = new BigInteger[N*4];

        init(arr, tree, 1, 1, N);

        int cmd_count = M + K;
        for(int i = 0; i < cmd_count; i++){
            input = in.readLine().split(" ");
            int cmd = Integer.parseInt(input[0]);
            if(cmd == 1){
                int idx = Integer.parseInt(input[1]);
                BigInteger value = BigInteger.valueOf(Long.parseLong(input[2]));
                BigInteger diff = value.subtract(arr[idx]);
                arr[idx] = value;
                update(tree, 1, idx, 1, N, diff);
            }
            else {
                int left = Integer.parseInt(input[1]);
                int right = Integer.parseInt(input[2]);
                sb.append(sum(tree, 1, 1, N, left, right).toString()).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    static BigInteger init(BigInteger[] arr, BigInteger[] tree, int node, int start, int end){
        if(start == end) return tree[node] = arr[start];
        else return tree[node] = init(arr, tree, node*2, start, (start+end)/2).add(init(arr,tree, node*2 + 1, (start+end)/2 + 1, end));
    }

    // start ~ end : node가 지니는 범위
    // left ~ right : 값을 구하고자 하는 범위
    // node : node 번호
    static BigInteger sum(BigInteger[] tree, int node, int start, int end, int left, int right){
        if(end < left || right < start) return BigInteger.ZERO;
        
        if(start >= left && right >= end)  return tree[node];
        
        return sum(tree, node*2, start, (start+end)/2, left, right).add(sum(tree, node*2+1, (start+end)/2 + 1, end, left, right));
    }

    static void update(BigInteger[] tree, int node, int idx, int start, int end, BigInteger diff){
        if(end < idx || idx < start) return;
        tree[node] = tree[node].add(diff);
        if(start != end){
            update(tree, node*2, idx, start, (start+end)/2, diff);
            update(tree, node*2+1, idx, (start+end)/2 + 1, end, diff);
        }
    }
}
