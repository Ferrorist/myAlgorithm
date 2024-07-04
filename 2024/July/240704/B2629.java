/*
 * https://www.acmicpc.net/problem/2629
 * 소요 시간 : 1시간 초과
 */
import java.io.*;
public class B2629 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int chu_count = Integer.parseInt(in.readLine()); // 추 개수

        int[] weights = new int[chu_count+1];
        String[] input = in.readLine().split(" ");
        for(int i = 1; i <= chu_count; i++)
            weights[i] = Integer.parseInt(input[i-1]);
        
        
        int[][] DP = new int[chu_count+1][40000 + 1]; 
        DP[0][0] = 1;
        int sum = 0;
        for(int i = 1; i <= chu_count; i++){
            int weight = weights[i];
            for(int j = 0; j <= sum; j++){
                if(DP[i-1][j] > 0){
                    DP[i][j] = 1;
                    DP[i][Math.abs(weight - j)] = 1;
                    DP[i][weight + j] = 1;
                }
            }
            sum += weight;
        }

        int marble_count = Integer.parseInt(in.readLine());
        input = in.readLine().split(" ");
        for(int i = 0; i < marble_count; i++){
            int marble = Integer.parseInt(input[i]);
            if(DP[chu_count][marble] > 0)   sb.append("Y");
            else sb.append("N");
            sb.append(" ");
        }

        System.out.println(sb);
    }
}