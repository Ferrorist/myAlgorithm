/*
 * https://www.acmicpc.net/problem/12865
 * 소요 시간 : 26분 03초
 */
import java.io.*;
import java.util.*;
class B12865 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");

        int N = Integer.parseInt(input[0]); // 물품의 수
        int K = Integer.parseInt(input[1]); // 버틸 수 있는 무게

        int[] DP = new int[K+1];
        for(int i = 0; i < N; i++){
            input = in.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);
            if(value == 0)  continue;
            for(int j = K; j >= weight; j--){
                DP[j] = Math.max(DP[j], DP[j-weight] + value);
            }
        }
        Arrays.sort(DP);
        System.out.println(DP[K]);
    }
}
