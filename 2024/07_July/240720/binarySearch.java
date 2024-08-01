import java.io.*;
import java.util.*;
public class binarySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = {1, 2, 5, 8, 8, 8, 8, 9, 11, 12, 13, 15};

        int input = Integer.parseInt(in.readLine());

        System.out.println(binarySearch(arr, input));
        System.out.println(lowerbound(arr, input));
        System.out.println(upperbound(arr, input));
    }

    static int lowerbound(int[] arr, int target){
        int start = 0, end = arr.length;
        while(start < end){
            int mid = (start + end) >>> 1;

            if(arr[mid] >= target) end = mid;
            else start = mid + 1;
        }

        return start;
    }

    static int upperbound(int[] arr, int target){
        int start = 0, end = arr.length;
        while(start < end){
            int mid = (start + end) >>> 1;

            if(arr[mid] <= target) start = mid + 1;
            else end = mid;
        }

        return start;
    }

    static int binarySearch(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int mid = 0;
        while(start <= end){
            mid = (start + end) >>> 1;

            if(arr[mid] == target) return mid; // 찾으려는 값을 발견하였다면, 인덱스의 값을 return.

            /*
             * 찾으려는 값보다 작다면, 중간 기준 오른쪽 범위를 탐색.
             * 크다면, 중간 기준 왼쪽 범위를 탐색하도록 한다.
             */
            if(arr[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        /*
         * target을 찾지 못하였을 경우, 음수를 반환하되,
         * target 값이 배열에 있을 경우 존재해야 할 index의 값 * (-1)을 반환한다.
         */
        return start * (-1); 
    }
}