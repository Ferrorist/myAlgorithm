/*
 * https://www.acmicpc.net/problem/7579
 * 소요 시간 : 1시간 10분 50초
 */
import java.io.*;
import java.util.*;
public class B7579 {
    static int MAX = 10_001; // 앱 개수 100, 각 앱의 비용 최대 100
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");;
        int N = Integer.parseInt(input[0]); // 앱의 개수
        int M = Integer.parseInt(input[1]); // 필요한 메모리 M 바이트

        int[] memory = new int[N];
        input = in.readLine().split(" ");
        for(int i = 0; i < N; i++) memory[i] = Integer.parseInt(input[i]);

        int[] DP = new int[MAX];

        input = in.readLine().split(" ");
        for(int i = 0; i < N; i++){
            int cost = Integer.parseInt(input[i]);
            for(int c = MAX - 1; c >= cost; c--){
                DP[c] = Math.max(DP[c], DP[c - cost] + memory[i]);
            }
        }

        for(int i = 0; i < MAX; i++){
            if(DP[i] >= M){
                System.out.println(i);  break;
            }
        }
    }
}
