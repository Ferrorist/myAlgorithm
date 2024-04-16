/*
 * 문제 링크 : https://www.acmicpc.net/problem/12015
 */
import java.io.*;
import java.util.*;
public class B12015 {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(in.readLine());
        String[] input = in.readLine().split(" ");
        int[] arr = new int[length];
        for(int i = 0; i < length; i++) arr[i] = Integer.parseInt(input[i]);

        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);

        for(int i = 1; i < length; i++){
            if(arr[i] > list.get(list.size()-1)){ // 가장 오른쪽 수(가장 큰 수) 보다 크면 걍 넣음.
                list.add(arr[i]);   continue;
            }

            int start = 0, end = list.size() - 1;

            while(start < end){
                int mid = (start + end) / 2;
                int value = list.get(mid);
                if(arr[i] > value) start = mid + 1;
                else end = mid;
            }

            list.set(end, arr[i]);
        }

        System.out.println(list.size());
    }
}
