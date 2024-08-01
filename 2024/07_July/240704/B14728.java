/*
 * https://www.acmicpc.net/problem/14728
 * 소요 시간 : 9분
 */
import java.io.*;
public class B14728 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");

        int lesson_count = Integer.parseInt(input[0]); // 단원 개수
        int total_hour = Integer.parseInt(input[1]); // 공부할 수 있는 총 시간

        int[] DP = new int[total_hour + 1];
        for(int i = 0; i < lesson_count; i++){
            input = in.readLine().split(" ");
            int hour = Integer.parseInt(input[0]);  // 단원 별 예상 공부 시간
            int score = Integer.parseInt(input[1]); // 해당 단원 문제의 배점

            for(int n = total_hour; n >= hour; n--){
                DP[n] = Math.max(DP[n], DP[n - hour] + score);
            }
        }

        System.out.println(DP[total_hour]);
    }
}
