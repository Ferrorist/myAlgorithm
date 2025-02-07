/*
 * https://softeer.ai/practice/6294
 */
import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static int[] DP;
    private static int K;
    
    public static void main(String[] args) throws Exception {
        inputArguments();
        getSums();
        getAverages();
    }

    private static void inputArguments() throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        DP = new int[N+1];
    }

    private static void getSums() throws Exception {
        String[] inputs = in.readLine().split(" ");

        for(int i = 1; i < DP.length; i++) {
            DP[i] = DP[i-1] + Integer.parseInt(inputs[i-1]);
        }
    }

    private static void getAverages() throws Exception {
        StringBuilder out = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int sum = DP[end] - DP[start-1];
            double average = (double)sum / (end-start+1);
            out.append(String.format("%.2f", average)).append("\n");
        }

        System.out.println(out);
    }
}
