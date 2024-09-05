/*
 * https://www.acmicpc.net/problem/25314
 */
import java.io.*;
class B25314 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // (4 <= N <= 1_000, N은 4의 배수)
        int N = Integer.parseInt(in.readLine());
        int long_count = N / 4;

        for(int i = 0; i < long_count; i++) sb.append("long ");
        sb.append("int");
        
        System.out.println(sb.toString());
    }
}