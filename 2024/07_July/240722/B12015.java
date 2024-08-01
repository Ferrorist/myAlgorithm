import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class B12015 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // 수열의 크기 ( 1 <= N <= 1_000_000)
        int N = Integer.parseInt(in.readLine());

        String[] input = in.readLine().split(" ");

        /*
         * 수열의 크기가 최대 1_000_000,
         * LIS 문제를 DP를 이용한다면 시간 복잡도가 O(n^2) 이므로,
         * 1_000_000 * 1_000_000 = 1_000_000_000_000
         * 즉, 시간 초과.
         */

        ArrayList<Integer> LIS = new ArrayList<>();
        for(String str : input){
            // Ai ( 1 <= Ai <= 1_000_000)
            int value = Integer.parseInt(str);
            
            int idx = Collections.binarySearch(LIS, value);
            if(idx < 0) idx = (idx + 1) * (-1);
            if(idx >= LIS.size()) LIS.add(value);
            else if( LIS.get(idx) > value ) LIS.set(idx, value);
        }

        System.out.println(LIS.size());
    }
}
