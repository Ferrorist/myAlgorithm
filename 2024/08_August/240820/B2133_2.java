/*
 * https://www.acmicpc.net/problem/2133
 * 소요 시간 : 19분 2초
 */
import java.io.*;
public class B2133_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        int[] dp = new int[N+1];
        dp[0] = 1;

        for(int i = 2; i <= N; i += 2) {
            dp[i] = dp[i-2] * 3;
            for(int j = i - 4; j >= 0; j -= 2){
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }   
}
