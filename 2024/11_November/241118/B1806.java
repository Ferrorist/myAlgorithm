/*
 * https://www.acmicpc.net/problem/1806
 * 소요 시간 : 9분 54초
 */
import java.io.*;

public class B1806 {
 
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    static int length, target;
    static int[] sums;
    
    public static void main(String[] args) throws Exception {
        inputArguments();
        System.out.println(solve());
    }

    static void inputArguments() throws Exception {
        String[] input = in.readLine().split(" ");
        length = Integer.parseInt(input[0]);
        target = Integer.parseInt(input[1]);

        sums = new int[length + 1];
        input = in.readLine().split(" ");
        for(int i = 0; i < length; i++) {
            sums[i+1] = sums[i] + Integer.parseInt(input[i]);
            
        }
    }

    private static int solve() {
        int start = 0, end = 1;
        int current = 0, min_length = Integer.MAX_VALUE;
        while (end <= length) {
            current = sums[end] - sums[start];
            if(current >= target) {
                min_length = Math.min(min_length, end - start);
                start++;
            }
            else end++;
        }

        return min_length == Integer.MAX_VALUE ? 0 : min_length;
    }
}