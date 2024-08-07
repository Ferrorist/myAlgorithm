/*
 * https://codeforces.com/contest/1999/problem/B 
 */
import java.io.*;
public class problemB {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int Testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(Testcase-- > 0){
            String[] input = in.readLine().split(" ");

            int[] Suneet = new int[2];
            int[] Slavic = new int[2];

            for(int i = 0; i < 2; i++)  Suneet[i] = Integer.parseInt(input[i]);
            for(int i = 2; i < 4; i++)  Slavic[i-2] = Integer.parseInt(input[i]);

            int answer = 0;

            if((Suneet[0] > Slavic[0] && Suneet[1] > Slavic[1]) || (Suneet[0] == Slavic[0] && Suneet[1] > Slavic[1]) || (Suneet[0] > Slavic[0] && Suneet[1] == Slavic[1]))  answer += 2;
            if((Suneet[0] > Slavic[1] && Suneet[1] > Slavic[0]) || (Suneet[0] == Slavic[1] && Suneet[1] > Slavic[0]) || (Suneet[0] > Slavic[1] && Suneet[1] == Slavic[0]))  answer += 2;

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
