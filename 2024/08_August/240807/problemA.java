/*
 * https://codeforces.com/contest/1999/problem/A
 */
import java.io.*;
public class problemA {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int Testcase = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(Testcase-- > 0){
            // (10 <= n <= 99)
            int n = Integer.parseInt(in.readLine());

            int value = (n / 10) + (n % 10);
            sb.append(value).append("\n");
        }

        System.out.println(sb);
    }
}