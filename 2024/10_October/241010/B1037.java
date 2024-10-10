/*
 * https://www.acmicpc.net/problem/1037
 */
import java.io.*;
import java.util.Arrays;
public class B1037 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int[] arr;
    public static void main(String[] args) throws Exception {
        initArguments();
        long answer = solve();
        System.out.println(answer);
    }

    private static void initArguments() throws Exception {
        int count = Integer.parseInt(in.readLine());
        arr = new int[count];
        String[] input = in.readLine().split(" ");

        for(int i = 0; i < count; i++)  arr[i] = Integer.parseInt(input[i]);
    }

    private static long solve() {
        Arrays.sort(arr);
        return (long)arr[0] * (long)arr[arr.length - 1];
    }
}
