/*
 * https://www.acmicpc.net/problem/4781
 * 소요 시간 : 20분 54초
 */
import java.io.*;
import java.util.Arrays;
public class B4781 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input;

        while(true){
            input = in.readLine().split(" ");
            int candy_count = Integer.parseInt(input[0]);
            int money = (int)(Double.parseDouble(input[1]) * 100.0 + 0.5);
            if(candy_count == 0 && money == 0)  break;

            int[] DP = new int[money + 1];
            for(int i = 0; i < candy_count; i++){
                input = in.readLine().split(" ");
                int kcal = Integer.parseInt(input[0]);
                int cost = (int)(Double.parseDouble(input[1]) * 100.0 + 0.5);
                for(int j = cost; j <= money; j++){
                    DP[j] = Math.max(DP[j], DP[j - cost] + kcal);
                }
            }
            Arrays.sort(DP);
            sb.append(DP[money]).append("\n");
        }

        System.out.println(sb);
    }
}
