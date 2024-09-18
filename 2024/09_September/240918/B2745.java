/*
 * https://www.acmicpc.net/problem/2745
 */
import java.io.*;
import java.util.StringTokenizer;
public class B2745 {
    static BufferedReader in;
    static String N;
    static int B;
    public static void main(String[] args) throws Exception {
        initReader();
        inputArguments();
        solve();
    }   

    private static void initReader() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void inputArguments() throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = st.nextToken();
        B = Integer.parseInt(st.nextToken());
    }

    private static void solve() {
        int answer = 0, multiple = 0;

        for(int i = N.length() - 1; i >= 0; i--){
            int num = 0;
            char current = N.charAt(i);
            if(Character.isDigit(current)) num = (int)(current - '0');
            else num = (int)(current - 'A') + 10;

            answer += num * (int)Math.pow(B, multiple++);
        }

        System.out.println(answer);
    }
}
