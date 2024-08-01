/*
 * https://www.acmicpc.net/problem/9084
 * 소요 시간 : 18분
 */
import java.io.*;
public class B9084 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int Testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(Testcase-- > 0){
            int N = Integer.parseInt(in.readLine()); // 동전 가지 수
            String[] input = in.readLine().split(" ");

            int[] arr = new int[N];
            for(int i = 0; i < N; i++)  arr[i] = Integer.parseInt(input[i]);
            
            // 1 <= target <= 10,000
            int target = Integer.parseInt(in.readLine());
            
            int[] DP = new int[target+1]; DP[0] = 1;
            for(int i = 0; i < N; i++){
                int money = arr[i];
                for(int j = money; j <= target; j++){
                    DP[j] += DP[j-money];
                }
            }
            sb.append(DP[target]).append("\n");
        }
        System.out.println(sb);
    }
}
