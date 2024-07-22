/*
 * https://www.acmicpc.net/problem/1365
 * 소요 시간 : 19분
 */
import java.io.*;
import java.util.*;
public class B1365 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 전봇대의 개수 N (1 <= N <= 100_000)
        int N = Integer.parseInt(in.readLine());
        
        String[] input = in.readLine().split(" ");
        List<Integer> LIS = new ArrayList<>(); // 최장 증가 수열

        for(String str : input){
            int value = Integer.parseInt(str);

            int idx = Collections.binarySearch(LIS, value);
            if(idx < 0) idx = (idx + 1) * (-1);

            if( idx >= LIS.size() ) LIS.add(value);
            else if( LIS.get(idx) > value ) LIS.set(idx, value);
        }
        System.out.println(input.length - LIS.size());
    }
}
