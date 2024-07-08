/*
 * https://www.acmicpc.net/problem/1106
 * 소요 시간 : 14분 06초
 */
import java.io.*;
import java.util.Arrays;
public class B1106 {
    static int INF = 999_999_999;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");

        int target = Integer.parseInt(input[0]); // 목표 고객 수
        int N = Integer.parseInt(input[1]); // 도시 수

        int[] DP = new int[target+101];
        Arrays.fill(DP, INF);  DP[0] = 0;
        for(int i = 0; i < N; i++){
            input = in.readLine().split(" ");
            int cost = Integer.parseInt(input[0]);
            int people = Integer.parseInt(input[1]);
            for(int p = people; p < DP.length; p++){
                DP[p] = Math.min(DP[p], DP[p - people] + cost);
            }
            System.out.println(Arrays.toString(DP));
        }

        int result = Integer.MAX_VALUE;
        for(int i = target; i < DP.length; i++) result = Math.min(result, DP[i]);

        System.out.println(result);
        System.out.println(Arrays.toString(DP));
    }
}
