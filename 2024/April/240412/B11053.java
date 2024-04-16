/*
 * 문제 링크 : https://www.acmicpc.net/problem/11053
 * DP를 이용하여 LIS 문제를 해결하였으며,
 * DP를 이용한 방법은 O(n^2)의 시간복잡도를 지닌다.
 */
import java.io.*;
import java.util.*;
public class B11053 {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(in.readLine());
        int[] DP = new int[length+1];

        String[] input = in.readLine().split(" ");
        int[] arr = new int[length];
        for(int i = 0; i < length; i++){
            arr[i] = Integer.parseInt(input[i]);
            int value = 1;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]) value = Math.max(value, DP[j]+1);
            }
            DP[i] = value;
        }

        Arrays.sort(DP);
        System.out.println(DP[length]);
    }
}
