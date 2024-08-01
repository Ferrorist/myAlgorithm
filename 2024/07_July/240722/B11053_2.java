/*
 * https://www.acmicpc.net/problem/11053
 * 이진 탐색을 이용한 LIS 문제 풀이
 */
import java.io.*;
import java.util.*;
public class B11053_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        String[] input = in.readLine().split(" ");
        List<Integer> LIS = new ArrayList<>();

        for(String str : input){
            int value = Integer.parseInt(str);
            if(LIS.isEmpty())   {
                LIS.add(value); continue;
            }
            int idx = Collections.binarySearch(LIS, value);
            if(idx < 0) idx = (idx + 1) * (-1);
            if( idx >= LIS.size() ) LIS.add(value);
            else if( LIS.get(idx) >= value ) LIS.set(idx, value);
        }

        System.out.println(LIS.size());
    }
}
