/*
 * https://softeer.ai/practice/6293
 * 소요 시간 : 13분 50초
 */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[] DP = new int[N];
        int[] arr = new int[N];
        String[] input = in.readLine().split(" ");
        
        arr[0] = Integer.parseInt(input[0]);
        Arrays.fill(DP, 1);

        for(int i = 1; i < N; i++){
            arr[i] = Integer.parseInt(input[i]);
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]) DP[i] = Math.max(DP[i], DP[j]+1);
            }
        }

        Arrays.sort(DP);
        System.out.println(DP[N-1]);
        
    }
}