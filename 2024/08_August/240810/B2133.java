/*
 * https://www.acmicpc.net/problem/2133
 * 소요 시간 : 43분 23초 (다른 사람 풀이 참고함...)
 */
import java.io.*;
public class B2133 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // ( 1 <= N <= 30 )
        int N = Integer.parseInt(in.readLine());

        if(N % 2 == 1)  System.out.println(0);
        else {
            N = N >> 1;
            int[] DP = new int[N+1];
            DP[0] = 1;
            for(int i = 1; i < N+1; i++){
                DP[i] += DP[i-1];
                for(int j = 1; j < i; j++){
                    DP[i] += DP[j] * 2;
                }
                DP[i] += 2;
            }
            System.out.println(DP[N]);
        }
        

    }   
}
