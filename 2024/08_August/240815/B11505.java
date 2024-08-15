/*
 * https://www.acmicpc.net/problem/11505
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
import java.math.*;
public class B11505 {
    static final int INF = 1_000_000_007;
    static int[] arr, Tree;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");

        /*
         * N : 수 개수, M : 수 변경 발생 횟수, K : 구간 곱을 구하는 횟수
         * (1 <= N <= 1_000_000), (1 <= M <= 10_000)
         * (1 <= K <= 10_000)
         */
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        // N개의 수 입력받음
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }

        Tree = new int[getTreeSize(N)];
        initTree(0, N-1, 1);

        for(int i = 0; i < M + K; i++){
            input = in.readLine().split(" ");

            int cmd = Integer.parseInt(input[0]);

            // cmd == 1 : b번째 수를 c로 바꿈.
            if(cmd == 1){
                int index = Integer.parseInt(input[1]) - 1;
                int value = Integer.parseInt(input[2]);

                update(index, 0, N-1, 1, value);
            } 
            // cmd == 2 : b부터 c까지 곱을 구함.
            else {
                int start = Integer.parseInt(input[1]) - 1;
                int end = Integer.parseInt(input[2]) - 1;

                int value = getValue(0, N-1, start, end, 1);
                System.out.println(value);
            }
        }
    }

    private static int getTreeSize(int N){
        int h = (int)Math.ceil(Math.log(N)/Math.log(2));
        return (int)Math.pow(2,h+1);
    }

    private static int getCalcResult(int left, int right){
        return ((left % INF) * (right % INF)) % INF;
    }

    private static void initTree(int left, int right, int index){
        if(left == right)   {
            Tree[index] = arr[left];
            return;
        }

        int mid = (left + right) / 2;
        initTree(left, mid, index * 2);
        initTree(mid+1, right, index * 2 + 1);
        Tree[index] = getCalcResult(Tree[index * 2], Tree[index * 2 + 1]);
    }

    private static int update(int arr_index, int left, int right, int index, int value){
        if(arr_index < left || arr_index > right)   return Tree[index];

        if(left == right)   return Tree[index] = value;

        int mid = (left + right) / 2;
        return Tree[index] = 
        getCalcResult(
            update(arr_index, left, mid, index * 2, value),
            update(arr_index, mid+1, right, index*2 + 1, value)
        );
    }

    // left ~ right : 세그먼트 트리의 node가 지니는 구간
    // start ~ end : 검색 범위
    private static int getValue(int left, int right, int start, int end, int index){
        // 탐색 범위가 node 밖에 있으면 곱셈의 항등원인 1을 반환.
        if(end < start || right < start)    return 1;

        // 탐색 범위가 node 안에 있음.
        if(start <= left && right <= end)   return Tree[index];

        int mid = (left + right) / 2;        
        return getCalcResult(
                getValue(left, mid, start, end, index * 2), 
                getValue(mid+1, right, start, end, index*2 + 1)
            );
    }
}