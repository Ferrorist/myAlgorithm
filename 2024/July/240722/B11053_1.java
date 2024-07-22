/*
 * https://www.acmicpc.net/problem/11053
 * DP를 이용한 LIS 문제 풀이
 */
import java.io.*;
import java.util.Arrays;
public class B11053_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 수열의 크기 ( 1 <= N <= 1_000)
        int N = Integer.parseInt(in.readLine());
        
        String[] input = in.readLine().split(" ");
        int[] arr = new int[N+1];
        for(int i = 1; i <= N; i++)    arr[i] = Integer.parseInt(input[i-1]);
        
        int[] DP = new int[N+1];
        
        for(int i = 1; i <= N; i++){
            int value = 0;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j])    value = Math.max(value, DP[j] + 1);
            }
            DP[i] = value;
        }
        Arrays.sort(DP);
        System.out.println(DP[N]);
    }
}